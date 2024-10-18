package com.shalfa.marketsupplies

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shalfa.marketsupplies.adapter.DrinkAdapter
import com.shalfa.marketsupplies.addproduct.AddDrinkActivity
import com.shalfa.marketsupplies.view_model.DrinkViewModel

class DrinkActivity : AppCompatActivity() {

    private val viewModel: DrinkViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drink)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewDrink)
        val btnAddDrink = findViewById<Button>(R.id.btnAddDrink)

        recyclerView.layoutManager = LinearLayoutManager(this)

        // Observe data dari ViewModel dan update RecyclerView
        viewModel.allDrink.observe(this) { drinkList ->
            recyclerView.adapter = DrinkAdapter(
                drinkList,
                onEditClick = { minuman ->
                    // Pindah ke AddFoodActivity dengan data makanan untuk diubah
                    val intent = Intent(this, AddDrinkActivity::class.java).apply {
                        putExtra("drink_id", minuman.id)
                        putExtra("drink_name", minuman.namaMinuman)
                        putExtra("drink_weight", minuman.beratMinuman)
                        putExtra("drink_stock", minuman.jumlahStok)
                    }
                    startActivity(intent)
                },
                onDeleteClick = { minuman ->
                    // Menghapus data makanan
                    viewModel.deleteMinuman(minuman)
                    Toast.makeText(this, "Data Makanan Dihapus", Toast.LENGTH_SHORT).show()
                }
            )
        }

        // Untuk menambah data makanan baru
        btnAddDrink.setOnClickListener {
            // Pindah ke AddFoodActivity tanpa membawa data (mode tambah)
            startActivity(Intent(this, AddDrinkActivity::class.java))
        }
    }
}