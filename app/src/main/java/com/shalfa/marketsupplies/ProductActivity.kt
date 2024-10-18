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
import com.shalfa.marketsupplies.addproduct.AddDailyNeedsActivity
import com.shalfa.marketsupplies.view_model.DailyNeedsViewModel

class ProductActivity : AppCompatActivity() {

    private val viewModel: DailyNeedsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dailyneeds)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewDailyNeeds)
        val btnAddDailyNeeds = findViewById<Button>(R.id.btnAddDailyNeeds) // Updated button ID

        recyclerView.layoutManager = LinearLayoutManager(this)

        // Observe data from ViewModel and update RecyclerView
        viewModel.allDailyNeeds.observe(this) { dailyNeedsList ->
            recyclerView.adapter = DailyNeedsAdapter(
                dailyNeedsList,
                onEditClick = { kebutuhan ->
                    // Navigate to AddDailyNeedsActivity with data to edit
                    val intent = Intent(this, AddDailyNeedsActivity::class.java).apply {
                        putExtra("namaKebutuhan_id", kebutuhan.id)
                        putExtra("dailyneeds_name", kebutuhan.namaKebutuhan)
                        putExtra("dailyneeds_weight", kebutuhan.beratKebutuhan)
                        putExtra("dailyneeds_stock", kebutuhan.jumlahStok)
                    }
                    startActivity(intent)
                },
                onDeleteClick = { kebutuhan -> // Changed parameter name to match the type
                    // Delete the daily needs item
                    viewModel.deleteKebutuhan(kebutuhan)
                    Toast.makeText(this, "Data Kebutuhan Dihapus", Toast.LENGTH_SHORT).show()
                }
            )
        }

        // For adding new daily needs
        btnAddDailyNeeds.setOnClickListener {
            // Navigate to AddDailyNeedsActivity without data (add mode)
            startActivity(Intent(this, AddDailyNeedsActivity::class.java))
        }
    }
}