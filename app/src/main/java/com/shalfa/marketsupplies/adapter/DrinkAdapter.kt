package com.shalfa.marketsupplies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shalfa.marketsupplies.databinding.ItemDrinkBinding
import com.shalfa.marketsupplies.entity.MinumanEntity
import com.shalfa.marketsupplies.toStockWithItem

class DrinkAdapter(
    private val onEditClick: (MinumanEntity) -> Unit,
    private val onDeleteClick: (MinumanEntity) -> Unit
) : ListAdapter<MinumanEntity, DrinkAdapter.DrinkViewHolder>(DIFF_CALLBACK) {

    class DrinkViewHolder(val binding: ItemDrinkBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkViewHolder {
        val binding = ItemDrinkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DrinkViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DrinkViewHolder, position: Int) {
        val drink = getItem(position)
        holder.binding.apply {
            textViewNamaMinuman.text = drink.namaMinuman
            textViewBeratMinuman.text = "${drink.beratMinuman} ml"
            textViewJumlahStokMinuman.text = "Stok: ${drink.jumlahStok.toStockWithItem()}"

            btnUbah.setOnClickListener {
                onEditClick(drink)
            }

            btnHapus.setOnClickListener {
                onDeleteClick(drink)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MinumanEntity>() {
            override fun areItemsTheSame(oldItem: MinumanEntity, newItem: MinumanEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MinumanEntity, newItem: MinumanEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}