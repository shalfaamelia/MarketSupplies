package com.shalfa.marketsupplies

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.shalfa.marketsupplies.adapter.ProductAdapter
import com.shalfa.marketsupplies.databinding.ActivityProductBinding

class ProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductBinding
    private lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val categories = listOf("Makanan", "Minuman", "Kebutuhan Sehari-hari")

        productAdapter = ProductAdapter { category ->
            when (category) {
                "Makanan" -> {
                    startActivity(Intent(this, FoodActivity::class.java))
                }
                "Minuman" -> {
                    startActivity(Intent(this, DrinkActivity::class.java))
                }
                "Kebutuhan Sehari-hari" -> {
                    startActivity(Intent(this, DailyNeedsActivity::class.java))
                }
                else -> {
                    Toast.makeText(this, "Kategori $category belum tersedia", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.recyclerViewProduct.apply {
            adapter = productAdapter
            layoutManager = LinearLayoutManager(this@ProductActivity)
        }

        productAdapter.submitList(categories)
    }
}