package com.shalfa.marketsupplies

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shalfa.marketsupplies.adapter.FoodAdapter
import com.shalfa.marketsupplies.addproduct.AddFoodActivity
import com.shalfa.marketsupplies.view_model.FoodViewModel

class FoodActivity : AppCompatActivity() {

    private val viewModel: FoodViewModel by viewModels()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewFood)
        val btnAddFood = findViewById<Button>(R.id.btnAddfood)

        recyclerView.layoutManager = LinearLayoutManager(this)

        // Observe data dari ViewModel dan update RecyclerView
        viewModel.allFood.observe(this) { foodList ->
            recyclerView.adapter = FoodAdapter(
                foodList,
                onEditClick = { makanan ->
                    // Pindah ke AddFoodActivity dengan data makanan untuk diubah
                    val intent = Intent(this, AddFoodActivity::class.java).apply {
                        putExtra("food_id", makanan.id)
                        putExtra("food_name", makanan.namaMakanan)
                        putExtra("food_weight", makanan.beratMakanan)
                        putExtra("food_stock", makanan.jumlahStok)
                    }
                    startActivity(intent)
                },
                onDeleteClick = { makanan ->
                    // Menghapus data makanan
                    viewModel.deleteMakanan(makanan)
                    Toast.makeText(this, "Data Makanan Dihapus", Toast.LENGTH_SHORT).show()
                }
            )
        }

        // Untuk menambah data makanan baru
        btnAddFood.setOnClickListener {
            // Pindah ke AddFoodActivity tanpa membawa data (mode tambah)
            startActivity(Intent(this, AddFoodActivity::class.java))
        }
    }
}