package com.shalfa.marketsupplies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shalfa.marketsupplies.databinding.ItemDailyneedsBinding
import com.shalfa.marketsupplies.entity.KebutuhanEntity
import com.shalfa.marketsupplies.toStockWithItem

class DailyNeedsAdapter(
    private val onEditClick: (KebutuhanEntity) -> Unit,
    private val onDeleteClick: (KebutuhanEntity) -> Unit
) : ListAdapter<KebutuhanEntity, DailyNeedsAdapter.DailyNeedsViewHolder>(DIFF_CALLBACK) {

    class DailyNeedsViewHolder(val binding: ItemDailyneedsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyNeedsViewHolder {
        val binding = ItemDailyneedsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DailyNeedsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DailyNeedsViewHolder, position: Int) {
        val dailyNeeds = getItem(position)
        holder.binding.apply {
            textViewNamaKebutuhan.text = dailyNeeds.namaKebutuhan
            textViewBeratKebutuhan.text = "${dailyNeeds.beratKebutuhan} gr/ml"
            textViewJumlahStokKebutuhan.text = "Stok: ${dailyNeeds.jumlahStok.toStockWithItem()}"

            btnUbah.setOnClickListener {
                onEditClick(dailyNeeds)
            }

            btnHapus.setOnClickListener {
                onDeleteClick(dailyNeeds)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<KebutuhanEntity>() {
            override fun areItemsTheSame(oldItem: KebutuhanEntity, newItem: KebutuhanEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: KebutuhanEntity, newItem: KebutuhanEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}