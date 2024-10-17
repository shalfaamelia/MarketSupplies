package com.shalfa.marketsupplies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FoodAdapter(private var foodList: List<FoodEntity>) :
    RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    class FoodViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNamaMakanan: TextView = view.findViewById(R.id.textViewNamaMakanan)
        val tvBeratMakanan: TextView = view.findViewById(R.id.textViewBeratMakanan)
        val tvJumlahStokMakanan: TextView = view.findViewById(R.id.textViewJumlahStokMakanan)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_food, parent, false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val food = foodList[position]
        holder.tvNamaMakanan.text = food.namaMakanan
        holder.tvBeratMakanan.text = "Berat: ${food.beratMakanan} "
        holder.tvJumlahStokMakanan.text = "Stok: ${food.jumlahStokMakanan} pcs"
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    fun updateData(newList: List<FoodEntity>) {
        foodList = newList
        notifyDataSetChanged()
    }
}
