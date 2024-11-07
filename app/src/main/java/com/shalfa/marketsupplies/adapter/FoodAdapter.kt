package com.shalfa.marketsupplies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shalfa.marketsupplies.databinding.ItemFoodBinding
import com.shalfa.marketsupplies.entity.MakananEntity

class FoodAdapter(
    private val foodList: List<MakananEntity>,
    private val onEditClick: (MakananEntity) -> Unit,
    private val onDeleteClick: (MakananEntity) -> Unit
) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    class FoodViewHolder(val binding: ItemFoodBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val binding = ItemFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val food = foodList[position]
        holder.binding.apply {
            textViewNamaMakanan.text = food.namaMakanan
            textViewBeratMakanan.text = "${food.beratMakanan} gr"
            textViewJumlahStokMakanan.text = "Stok: ${food.jumlahStok}"

            btnUbah.setOnClickListener {
                onEditClick(food)
            }

            btnHapus.setOnClickListener {
                onDeleteClick(food)
            }
        }
    }

    override fun getItemCount(): Int = foodList.size
}
