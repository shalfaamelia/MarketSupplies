package com.shalfa.marketsupplies

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.shalfa.marketsupplies.adapter.FoodAdapter
import com.shalfa.marketsupplies.addproduct.AddFoodActivity
import com.shalfa.marketsupplies.databinding.ActivityFoodBinding
import com.shalfa.marketsupplies.view_model.FoodViewModel

class FoodActivity : AppCompatActivity() {

    private val viewModel: FoodViewModel by viewModels()
    private lateinit var binding: ActivityFoodBinding
    private lateinit var foodAdapter: FoodAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)


        foodAdapter = FoodAdapter(
            onEditClick = { makanan ->
                val intent = Intent(this, AddFoodActivity::class.java).apply {
                    putExtra("food_id", makanan.id)
                    putExtra("food_name", makanan.namaMakanan)
                    putExtra("food_weight", makanan.beratMakanan)
                    putExtra("food_stock", makanan.jumlahStok)
                }
                startActivity(intent)
            },
            onDeleteClick = { makanan ->
                viewModel.deleteMakanan(makanan)
                Toast.makeText(this, "Data Makanan Dihapus", Toast.LENGTH_SHORT).show()
            }
        )

        val CustomLayoutManager = GridLayoutManager(this, 2)
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

        viewModel.allFood.observe(this) { foodList ->
            foodAdapter.submitFoodData(foodList)
        }

        binding.btnAddfood.setOnClickListener {
            startActivity(Intent(this, AddFoodActivity::class.java))
        }
    }
}