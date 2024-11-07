package com.shalfa.marketsupplies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shalfa.marketsupplies.databinding.ItemFoodBinding
import com.shalfa.marketsupplies.entity.MakananEntity
import com.shalfa.marketsupplies.toStockWithItem

class FoodAdapter(
    private val onEditClick: (MakananEntity) -> Unit,
    private val onDeleteClick: (MakananEntity) -> Unit
) : ListAdapter<MakananEntity, FoodAdapter.FoodViewHolder>(DIFF_CALLBACK) {

    class FoodViewHolder(val binding: ItemFoodBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val binding = ItemFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val food = getItem(position)
        holder.binding.apply {
            textViewNamaMakanan.text = food.namaMakanan
            textViewBeratMakanan.text = "${food.beratMakanan} gr"
            textViewJumlahStokMakanan.text = "Stok: ${food.jumlahStok.toStockWithItem()}"

            btnUbah.setOnClickListener {
                onEditClick(food)
            }

            btnHapus.setOnClickListener {
                onDeleteClick(food)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MakananEntity>() {
            override fun areItemsTheSame(oldItem: MakananEntity, newItem: MakananEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MakananEntity, newItem: MakananEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}