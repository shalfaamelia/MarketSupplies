package com.shalfa.marketsupplies.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shalfa.marketsupplies.databinding.ItemDailyneedsBinding
import com.shalfa.marketsupplies.databinding.ItemHeaderBinding
import com.shalfa.marketsupplies.entity.KebutuhanEntity
import com.shalfa.marketsupplies.toStockWithItem

class DailyNeedsAdapter(
    private val onEditClick: (KebutuhanEntity) -> Unit,
    private val onDeleteClick: (KebutuhanEntity) -> Unit
) : ListAdapter<Any, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    // Enum untuk menentukan tipe tampilan
    enum class ITEM_VIEW_TYPE {
        HEADER,
        DAILY_NEEDS
    }

    // Class untuk header kategori stok
    data class StockHeader(val title: String)

    // ViewHolder untuk item kebutuhan sehari-hari
    class DailyNeedsViewHolder(val binding: ItemDailyneedsBinding) : RecyclerView.ViewHolder(binding.root)

    // ViewHolder untuk header kategori stok
    class HeaderViewHolder(val binding: ItemHeaderBinding) : RecyclerView.ViewHolder(binding.root)

    // Menentukan tipe tampilan (header atau item kebutuhan)
    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is StockHeader -> ITEM_VIEW_TYPE.HEADER.ordinal
            else -> ITEM_VIEW_TYPE.DAILY_NEEDS.ordinal
        }
    }

    // Membuat ViewHolder berdasarkan tipe tampilan
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE.HEADER.ordinal -> {
                val binding = ItemHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                HeaderViewHolder(binding)
            }
            else -> {
                val binding = ItemDailyneedsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                DailyNeedsViewHolder(binding)
            }
        }
    }

    // Mengikat data pada ViewHolder
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is DailyNeedsViewHolder -> {
                val dailyNeeds = getItem(position) as KebutuhanEntity
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
            is HeaderViewHolder -> {
                val header = getItem(position) as StockHeader
                holder.binding.textViewHeader.text = header.title
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Any>() {
            override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
                return when {
                    oldItem is KebutuhanEntity && newItem is KebutuhanEntity -> oldItem.id == newItem.id
                    oldItem is StockHeader && newItem is StockHeader -> oldItem.title == newItem.title
                    else -> false
                }
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
                return oldItem == newItem
            }
        }
    }

    // Fungsi untuk mengelompokkan kebutuhan berdasarkan kategori stok dan submit data
    fun submitDailyNeedsData(items: List<KebutuhanEntity>) {
        val limitedStock = items.filter { it.jumlahStok < 35 }
        val mediumStock = items.filter { it.jumlahStok in 35..69 }
        val sufficientStock = items.filter { it.jumlahStok >= 70 }

        val list = mutableListOf<Any>()

        if (limitedStock.isNotEmpty()) {
            list.add(StockHeader("Stok Terbatas"))  // Tambahkan header untuk stok terbatas
            list.addAll(limitedStock)
        }

        if (mediumStock.isNotEmpty()) {
            list.add(StockHeader("Stok Cukup"))  // Tambahkan header untuk stok cukup
            list.addAll(mediumStock)
        }

        if (sufficientStock.isNotEmpty()) {
            list.add(StockHeader("Stok Terpenuhi"))  // Tambahkan header untuk stok terpenuhi
            list.addAll(sufficientStock)
        }

        // Submit list yang sudah dikelompokkan
        submitList(list)
    }
}