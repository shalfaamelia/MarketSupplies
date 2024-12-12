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
import com.shalfa.marketsupplies.adapter.DailyNeedsAdapter
import com.shalfa.marketsupplies.adapter.DrinkAdapter
import com.shalfa.marketsupplies.addproduct.AddDailyNeedsActivity
import com.shalfa.marketsupplies.databinding.FragmentDailyneedsBinding
import com.shalfa.marketsupplies.view_model.DailyNeedsViewModel

class DailyNeedsFragment : Fragment() {

    private val viewModel: DailyNeedsViewModel by viewModels()
    private lateinit var binding: FragmentDailyneedsBinding
    private lateinit var dailyNeedsAdapter: DailyNeedsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDailyneedsBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchFromFirebase()

        dailyNeedsAdapter = DailyNeedsAdapter(
            onEditClick = { kebutuhan ->
                val intent = Intent(requireContext(), AddDailyNeedsActivity::class.java).apply {
                    putExtra("dailyneeds_id", kebutuhan.id)
                    putExtra("dailyneeds_name", kebutuhan.namaKebutuhan)
                    putExtra("dailyneeds_weight", kebutuhan.beratKebutuhan)
                    putExtra("dailyneeds_stock", kebutuhan.jumlahStok)
                }
                startActivity(intent)
            },
            onDeleteClick = { kebutuhan ->
                viewModel.deleteKebutuhan(kebutuhan)
                viewModel.deleteFromFirebase(kebutuhan.id)
                Toast.makeText(requireContext(), "Data Kebutuhan Dihapus", Toast.LENGTH_SHORT).show()
            }
        )

        val CustomLayoutManager = GridLayoutManager(requireContext(), 2)
        CustomLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (dailyNeedsAdapter.getItemViewType(position)) {
                    DrinkAdapter.ITEM_VIEW_TYPE.HEADER.ordinal -> 2
                    else -> 1
                }
            }
        }
        binding.recyclerViewDailyNeeds.layoutManager = CustomLayoutManager
        binding.recyclerViewDailyNeeds.adapter = dailyNeedsAdapter

        viewModel.allDailyNeeds.observe(viewLifecycleOwner) { dailyNeedsList ->
            dailyNeedsAdapter.submitDailyNeedsData(dailyNeedsList)
        }

        binding.btnAddDailyNeeds.setOnClickListener {
            startActivity(Intent(requireContext(), AddDailyNeedsActivity::class.java))
        }
    }
}