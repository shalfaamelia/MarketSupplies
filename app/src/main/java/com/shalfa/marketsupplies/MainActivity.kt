package com.shalfa.marketsupplies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shalfa.marketsupplies.entity.KebutuhanEntity
import com.shalfa.marketsupplies.entity.MakananEntity
import com.shalfa.marketsupplies.entity.MinumanEntity

class MainActivity : AppCompatActivity() {
    private val viewModel: MarketViewModel by viewModels()
    private lateinit var adapter: MarketAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = MarketAdapter()
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        viewModel.allMakanan.observe(this, Observer { makanan ->
            val allItems = makanan + viewModel.allMinuman.value.orEmpty() + viewModel.allKebutuhan.value.orEmpty()
            adapter.setData(allItems)
        })

        // Contoh memasukkan data
        viewModel.insertMakanan(MakananEntity(nama = "Beras", berat = 5, stok = 50))
        viewModel.insertMinuman(MinumanEntity(nama = "Air Mineral", berat = 2, stok = 100))
        viewModel.insertKebutuhan(KebutuhanEntity(nama = "Sabun", berat = 1, stok = 30))
    }
}