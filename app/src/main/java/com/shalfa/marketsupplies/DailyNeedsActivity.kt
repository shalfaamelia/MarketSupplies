package com.shalfa.marketsupplies

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.shalfa.marketsupplies.adapter.DailyNeedsAdapter
import com.shalfa.marketsupplies.adapter.DrinkAdapter
import com.shalfa.marketsupplies.addproduct.AddDailyNeedsActivity
import com.shalfa.marketsupplies.databinding.ActivityDailyneedsBinding
import com.shalfa.marketsupplies.view_model.DailyNeedsViewModel

class DailyNeedsActivity : AppCompatActivity() {

    private val viewModel: DailyNeedsViewModel by viewModels()
    private lateinit var binding: ActivityDailyneedsBinding
    private lateinit var dailyNeedsAdapter: DailyNeedsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDailyneedsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.fetchFromFirebase()

        dailyNeedsAdapter = DailyNeedsAdapter(
            onEditClick = { kebutuhan ->
                val intent = Intent(this, AddDailyNeedsActivity::class.java).apply {
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
                Toast.makeText(this, "Data Kebutuhan Dihapus", Toast.LENGTH_SHORT).show()
            }
        )

        val CustomLayoutManager = GridLayoutManager(this, 2)
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

        viewModel.allDailyNeeds.observe(this) { dailyNeedsList ->
            dailyNeedsAdapter.submitDailyNeedsData(dailyNeedsList)
        }

        binding.btnAddDailyNeeds.setOnClickListener {
            startActivity(Intent(this, AddDailyNeedsActivity::class.java))
        }
    }
}