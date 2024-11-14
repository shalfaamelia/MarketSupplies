package com.shalfa.marketsupplies

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.shalfa.marketsupplies.adapter.DrinkAdapter
import com.shalfa.marketsupplies.addproduct.AddDrinkActivity
import com.shalfa.marketsupplies.databinding.ActivityDrinkBinding
import com.shalfa.marketsupplies.view_model.DrinkViewModel

class DrinkActivity : AppCompatActivity() {

    private val viewModel: DrinkViewModel by viewModels()
    private lateinit var binding: ActivityDrinkBinding
    private lateinit var drinkAdapter: DrinkAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDrinkBinding.inflate(layoutInflater)
        setContentView(binding.root)

        drinkAdapter = DrinkAdapter(
            onEditClick = { minuman ->
                val intent = Intent(this, AddDrinkActivity::class.java).apply {
                    putExtra("drink_id", minuman.id)
                    putExtra("drink_name", minuman.namaMinuman)
                    putExtra("drink_weight", minuman.beratMinuman)
                    putExtra("drink_stock", minuman.jumlahStok)
                }
                startActivity(intent)
            },
            onDeleteClick = { minuman ->
                viewModel.deleteMinuman(minuman)
                Toast.makeText(this, "Data Minuman Dihapus", Toast.LENGTH_SHORT).show()
            }
        )

        val CustomLayoutManager = GridLayoutManager(this, 2)
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

        viewModel.allDrink.observe(this) { drinkList ->
            drinkAdapter.submitDrinkData(drinkList)
        }

        binding.btnAddDrink.setOnClickListener {
            startActivity(Intent(this, AddDrinkActivity::class.java))
        }
    }
}