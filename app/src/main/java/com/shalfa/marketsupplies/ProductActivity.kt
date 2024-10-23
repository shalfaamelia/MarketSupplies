package com.shalfa.marketsupplies

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shalfa.marketsupplies.adapter.DailyNeedsAdapter
import com.shalfa.marketsupplies.adapter.ProductAdapter
import com.shalfa.marketsupplies.addproduct.AddDailyNeedsActivity
import com.shalfa.marketsupplies.view_model.DailyNeedsViewModel

class ProductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        val categories = listOf("Makanan", "Minuman", "Kebutuhan Sehari-hari")

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewProduct)
        val productAdapter = ProductAdapter(categories) { category ->
            when (category) {
                "Makanan" -> {
                    val intent = Intent(this, FoodActivity::class.java)
                    startActivity(intent)
                }
                "Minuman" -> {
                    val intent = Intent(this, DrinkActivity::class.java)
                    startActivity(intent)
                }
                "Kebutuhan Sehari-hari" -> {
                    val intent = Intent(this, DailyNeedsActivity::class.java)
                    startActivity(intent)
                }
                else -> {
                    Toast.makeText(this, "Kategori $category belum tersedia", Toast.LENGTH_SHORT).show()
                }
            }
        }
        recyclerView.adapter = productAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}