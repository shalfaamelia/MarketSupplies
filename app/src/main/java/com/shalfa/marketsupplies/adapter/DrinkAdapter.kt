package com.shalfa.marketsupplies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shalfa.marketsupplies.databinding.ItemDrinkBinding
import com.shalfa.marketsupplies.entity.MinumanEntity

class DrinkAdapter(
    private val drinkList: List<MinumanEntity>,
    private val onEditClick: (MinumanEntity) -> Unit,
    private val onDeleteClick: (MinumanEntity) -> Unit
) : RecyclerView.Adapter<DrinkAdapter.DrinkViewHolder>() {

    class DrinkViewHolder(val binding: ItemDrinkBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkViewHolder {
        val binding = ItemDrinkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DrinkViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DrinkViewHolder, position: Int) {
        val drink = drinkList[position]
        holder.binding.apply {
            textViewNamaMinuman.text = drink.namaMinuman
            textViewBeratMinuman.text = "${drink.beratMinuman} ml"
            textViewJumlahStokMinuman.text = "Stok: ${drink.jumlahStok}"

            btnUbah.setOnClickListener {
                onEditClick(drink)
            }

            btnHapus.setOnClickListener {
                onDeleteClick(drink)
            }
        }
    }

    override fun getItemCount(): Int = drinkList.size
}
