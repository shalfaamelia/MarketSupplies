package com.shalfa.marketsupplies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shalfa.marketsupplies.R
import com.shalfa.marketsupplies.entity.MakananEntity

class FoodAdapter(
    private val foodList: List<MakananEntity>,
    private val onEditClick: (MakananEntity) -> Unit,  // Callback untuk Ubah
    private val onDeleteClick: (MakananEntity) -> Unit
) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNamaMakanan: TextView = itemView.findViewById(R.id.textViewNamaMakanan)
        val tvBeratMakanan: TextView = itemView.findViewById(R.id.textViewBeratMakanan)
        val tvJumlahStok: TextView = itemView.findViewById(R.id.textViewJumlahStokMakanan)
        val btnUbah: Button = itemView.findViewById(R.id.btnUbah)
        val btnHapus: Button = itemView.findViewById(R.id.btnHapus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_food, parent, false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val food = foodList[position]
        holder.tvNamaMakanan.text = food.namaMakanan
        holder.tvBeratMakanan.text = "${food.beratMakanan} g"
        holder.tvJumlahStok.text = "Stok: ${food.jumlahStok}"

        holder.btnUbah.setOnClickListener {
            onEditClick(food)  // Panggil callback saat tombol Ubah ditekan
        }

        holder.btnHapus.setOnClickListener {
            onDeleteClick(food)  // Panggil callback saat tombol Hapus ditekan
        }
    }

    override fun getItemCount(): Int = foodList.size

}