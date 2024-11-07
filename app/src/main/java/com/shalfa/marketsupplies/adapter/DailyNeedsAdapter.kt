package com.shalfa.marketsupplies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shalfa.marketsupplies.databinding.ItemDailyneedsBinding
import com.shalfa.marketsupplies.entity.KebutuhanEntity

class DailyNeedsAdapter(
    private val dailyNeedsList: List<KebutuhanEntity>,
    private val onEditClick: (KebutuhanEntity) -> Unit,
    private val onDeleteClick: (KebutuhanEntity) -> Unit
) : RecyclerView.Adapter<DailyNeedsAdapter.DailyNeedsViewHolder>() {

    class DailyNeedsViewHolder(val binding: ItemDailyneedsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyNeedsViewHolder {
        val binding = ItemDailyneedsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DailyNeedsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DailyNeedsViewHolder, position: Int) {
        val dailyNeeds = dailyNeedsList[position]
        holder.binding.apply {
            textViewNamaKebutuhan.text = dailyNeeds.namaKebutuhan
            textViewBeratKebutuhan.text = "${dailyNeeds.beratKebutuhan} gr/ml"
            textViewJumlahStokKebutuhan.text = "Stok: ${dailyNeeds.jumlahStok}"

            btnUbah.setOnClickListener {
                onEditClick(dailyNeeds)
            }

            btnHapus.setOnClickListener {
                onDeleteClick(dailyNeeds)
            }
        }
    }

    override fun getItemCount(): Int = dailyNeedsList.size
}
