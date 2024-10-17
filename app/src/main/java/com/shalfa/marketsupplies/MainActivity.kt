package com.shalfa.marketsupplies

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private val foodViewModel: FoodViewModel by viewModels() // Deklarasi foodViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Setup RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewFood)
        val foodAdapter = FoodAdapter(ArrayList())
        recyclerView.adapter = foodAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Observe data dari ViewModel
        foodViewModel.allFoods.observe(this, Observer { foodList ->
            foodList?.let {
                foodAdapter.updateData(it)
            }
        })

        /// Setup Button untuk menambah data
        val buttonAddFood = findViewById<Button>(R.id.buttonAddFood)
        buttonAddFood.setOnClickListener {
            val intent = Intent(this, AddFoodActivity::class.java)
            addFoodLauncher.launch(intent)
        }
    }

    // Menerima data dari AddFoodActivity
    private val addFoodLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val namaMakanan = result.data?.getStringExtra("EXTRA_NAMA_MAKANAN") ?: ""
            val beratMakanan = result.data?.getIntExtra("EXTRA_BERAT_MAKANAN", 0) ?: 0
            val jumlahStokMakanan = result.data?.getIntExtra("EXTRA_JUMLAH_STOK", 0) ?: 0

            // Insert data ke database, id will be auto-generated
            foodViewModel.insert(FoodEntity(namaMakanan = namaMakanan, beratMakanan = beratMakanan, jumlahStokMakanan = jumlahStokMakanan))
        }
    }
}
