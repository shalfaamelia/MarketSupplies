package com.shalfa.marketsupplies

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shalfa.marketsupplies.adapter.DailyNeedsAdapter
import com.shalfa.marketsupplies.addproduct.AddDailyNeedsActivity
import com.shalfa.marketsupplies.view_model.DailyNeedsViewModel

class DailyNeedsActivity : AppCompatActivity() {

    private val viewModel: DailyNeedsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dailyneeds)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewDailyNeeds)
        val btnAddDailyNeeds = findViewById<Button>(R.id.btnAddDailyNeeds)

        recyclerView.layoutManager = LinearLayoutManager(this)

        // Observe data dari ViewModel dan update RecyclerView
        viewModel.allDailyNeeds.observe(this) { dailyNeedsList ->
            recyclerView.adapter = DailyNeedsAdapter(
                dailyNeedsList,
                onEditClick = { kebutuhan ->
                    // Pindah ke AddFoodActivity dengan data makanan untuk diubah
                    val intent = Intent(this, AddDailyNeedsActivity::class.java).apply {
                        putExtra("dailyneeds_id", kebutuhan.id)
                        putExtra("dailyneeds_name", kebutuhan.namaKebutuhan)  // String
                        putExtra("dailyneeds_weight", kebutuhan.beratKebutuhan)  // Int
                        putExtra("dailyneeds_stock", kebutuhan.jumlahStok)  // Int
                    }

                    startActivity(intent)
                },
                onDeleteClick = { kebutuhan ->
                    // Menghapus data makanan
                    viewModel.deleteKebutuhan(kebutuhan)
                    Toast.makeText(this, "Data Makanan Dihapus", Toast.LENGTH_SHORT).show()
                }
            )
        }

        // Untuk menambah data makanan baru
        btnAddDailyNeeds.setOnClickListener {
            // Pindah ke AddFoodActivity tanpa membawa data (mode tambah)
            startActivity(Intent(this, AddDailyNeedsActivity::class.java))
        }
    }
}