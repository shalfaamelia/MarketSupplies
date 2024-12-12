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
import com.shalfa.marketsupplies.adapter.FoodAdapter
import com.shalfa.marketsupplies.addproduct.AddFoodActivity
import com.shalfa.marketsupplies.databinding.FragmentFoodBinding
import com.shalfa.marketsupplies.view_model.FoodViewModel

class FoodFragment : Fragment() {
    private val viewModel: FoodViewModel by viewModels()
    private lateinit var binding: FragmentFoodBinding
    private lateinit var foodAdapter: FoodAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentFoodBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchFromFirebase()

        foodAdapter = FoodAdapter(
            onEditClick = { makanan ->
                val intent = Intent(requireContext(), AddFoodActivity::class.java).apply {
                    putExtra("food_id", makanan.id)
                    putExtra("food_name", makanan.namaMakanan)
                    putExtra("food_weight", makanan.beratMakanan)
                    putExtra("food_stock", makanan.jumlahStok)
                }
                startActivity(intent)
            },
            onDeleteClick = { makanan ->
                viewModel.deleteMakanan(makanan)
                viewModel.deleteFromFirebase(makanan.id)
                Toast.makeText(requireContext(), "Data Makanan Dihapus", Toast.LENGTH_SHORT).show()
            }
        )

        val CustomLayoutManager = GridLayoutManager(requireContext(), 2)
        CustomLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (foodAdapter.getItemViewType(position)) {
                    FoodAdapter.ITEM_VIEW_TYPE.HEADER.ordinal -> 2
                    else -> 1
                }
            }
        }
        binding.recyclerViewFood.layoutManager = CustomLayoutManager
        binding.recyclerViewFood.adapter = foodAdapter

        viewModel.allFood.observe(viewLifecycleOwner) { foodList ->
            foodAdapter.submitFoodData(foodList)
        }

        binding.btnAddfood.setOnClickListener {
            startActivity(Intent(requireContext(), AddFoodActivity::class.java))
        }
    }
}