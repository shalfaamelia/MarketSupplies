package com.shalfa.marketsupplies

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.shalfa.marketsupplies.adapter.DrinkAdapter
import com.shalfa.marketsupplies.addproduct.AddDrinkActivity
import com.shalfa.marketsupplies.databinding.FragmentDrinkBinding
import com.shalfa.marketsupplies.view_model.DrinkViewModel

class DrinkFragment : Fragment() {
    private val viewModel: DrinkViewModel by viewModels()
    private lateinit var binding: FragmentDrinkBinding
    private lateinit var drinkAdapter: DrinkAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDrinkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchFromFirebase()

        drinkAdapter = DrinkAdapter(
            onEditClick = { minuman ->
                val intent = Intent(requireContext(), AddDrinkActivity::class.java).apply {
                    putExtra("drink_id", minuman.id)
                    putExtra("drink_name", minuman.namaMinuman)
                    putExtra("drink_weight", minuman.beratMinuman)
                    putExtra("drink_stock", minuman.jumlahStok)
                }
                startActivity(intent)
            },
            onDeleteClick = { minuman ->
                viewModel.deleteMinuman(minuman)
                viewModel.deleteFromFirebase(minuman.id)
                Toast.makeText(requireContext(), "Data Minuman Dihapus", Toast.LENGTH_SHORT).show()
            }
        )

        val CustomLayoutManager = GridLayoutManager(requireContext(), 2)
        CustomLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (drinkAdapter.getItemViewType(position)) {
                    DrinkAdapter.ITEM_VIEW_TYPE.HEADER.ordinal -> 2
                    else -> 1
                }
            }
        }
        binding.recyclerViewDrink.layoutManager = CustomLayoutManager
        binding.recyclerViewDrink.adapter = drinkAdapter

        viewModel.allDrink.observe(viewLifecycleOwner) { drinkList ->
            drinkAdapter.submitDrinkData(drinkList)
        }

        binding.btnAddDrink.setOnClickListener {
            startActivity(Intent(requireContext(), AddDrinkActivity::class.java))
        }
    }
}