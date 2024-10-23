package com.shalfa.marketsupplies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shalfa.marketsupplies.R
import com.shalfa.marketsupplies.entity.MinumanEntity

class DrinkAdapter(
    private val drinkList: List<MinumanEntity>,
    private val onEditClick: (MinumanEntity) -> Unit,
    private val onDeleteClick: (MinumanEntity) -> Unit
) : RecyclerView.Adapter<DrinkAdapter.DrinkViewHolder>() {

    class DrinkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNamaMinuman: TextView = itemView.findViewById(R.id.textViewNamaMinuman)
        val tvBeratMinuman: TextView = itemView.findViewById(R.id.textViewBeratMinuman)
        val tvJumlahStok: TextView = itemView.findViewById(R.id.textViewJumlahStokMinuman)
        val btnUbah: Button = itemView.findViewById(R.id.btnUbah)
        val btnHapus: Button = itemView.findViewById(R.id.btnHapus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_drink, parent, false)
        return DrinkViewHolder(view)
    }

    override fun onBindViewHolder(holder: DrinkViewHolder, position: Int) {
        val drink = drinkList[position]
        holder.tvNamaMinuman.text = drink.namaMinuman
        holder.tvBeratMinuman.text = "${drink.beratMinuman} ml"
        holder.tvJumlahStok.text = "Stok: ${drink.jumlahStok}"

        holder.btnUbah.setOnClickListener {
            onEditClick(drink)
        }

        holder.btnHapus.setOnClickListener {
            onDeleteClick(drink)
        }
    }

    override fun getItemCount(): Int = drinkList.size
}