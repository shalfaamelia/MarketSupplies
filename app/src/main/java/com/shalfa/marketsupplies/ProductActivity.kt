package com.shalfa.marketsupplies

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shalfa.marketsupplies.adapter.ProductAdapter

class ProductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        // Produk kategori
        val categories = listOf("Makanan", "Minuman", "Kebutuhan Sehari-hari")

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewProduct)
        val productAdapter = ProductAdapter(categories) { category ->
            when (category) {
                "Makanan" -> {
                    // Pindah ke halaman item_food ketika Makanan diklik
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