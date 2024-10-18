package com.shalfa.marketsupplies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shalfa.marketsupplies.R
import com.shalfa.marketsupplies.entity.KebutuhanEntity

class DailyNeedsAdapter(
    private val dailyNeedsList: List<KebutuhanEntity>,
    private val onEditClick: (KebutuhanEntity) -> Unit,  // Callback untuk Ubah
    private val onDeleteClick: (KebutuhanEntity) -> Unit
) : RecyclerView.Adapter<DailyNeedsAdapter.DailyNeedsViewHolder>() {

    class DailyNeedsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNamaKebutuhan: TextView = itemView.findViewById(R.id.textViewNamaKebutuhan)
        val tvBeratKebutuhan: TextView = itemView.findViewById(R.id.textViewBeratKebutuhan)
        val tvJumlahStok: TextView = itemView.findViewById(R.id.textViewJumlahStokKebutuhan)
        val btnUbah: Button = itemView.findViewById(R.id.btnUbah)
        val btnHapus: Button = itemView.findViewById(R.id.btnHapus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyNeedsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_dailyneeds, parent, false)
        return DailyNeedsViewHolder(view)
    }

    override fun onBindViewHolder(holder: DailyNeedsViewHolder, position: Int) {
        val dailyNeeds = dailyNeedsList[position]
        holder.tvNamaKebutuhan.text = dailyNeeds.namaKebutuhan
        holder.tvBeratKebutuhan.text = "${dailyNeeds.beratKebutuhan} g"
        holder.tvJumlahStok.text = "Stok: ${dailyNeeds.jumlahStok}"

        holder.btnUbah.setOnClickListener {
            onEditClick(dailyNeeds)  // Panggil callback saat tombol Ubah ditekan
        }

        holder.btnHapus.setOnClickListener {
            onDeleteClick(dailyNeeds)  // Panggil callback saat tombol Hapus ditekan
        }
    }

    override fun getItemCount(): Int = dailyNeedsList.size
}
