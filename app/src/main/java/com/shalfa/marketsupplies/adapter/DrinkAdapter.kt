package com.shalfa.marketsupplies.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shalfa.marketsupplies.databinding.ItemDrinkBinding
import com.shalfa.marketsupplies.databinding.ItemHeaderBinding
import com.shalfa.marketsupplies.entity.MinumanEntity
import com.shalfa.marketsupplies.toStockWithItem

class DrinkAdapter(
    private val onEditClick: (MinumanEntity) -> Unit,
    private val onDeleteClick: (MinumanEntity) -> Unit
) : ListAdapter<Any, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    enum class ITEM_VIEW_TYPE {
        HEADER,
        DRINK
    }

    data class StockHeader(val title: String)
    class DrinkViewHolder(val binding: ItemDrinkBinding) : RecyclerView.ViewHolder(binding.root)
    class HeaderViewHolder(val binding: ItemHeaderBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is StockHeader -> ITEM_VIEW_TYPE.HEADER.ordinal
            else -> ITEM_VIEW_TYPE.DRINK.ordinal
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE.HEADER.ordinal -> {
                val binding = ItemHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                HeaderViewHolder(binding)
            }
            else -> {
                val binding = ItemDrinkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                DrinkViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is DrinkViewHolder -> {
                val drink = getItem(position) as MinumanEntity
                holder.binding.apply {
                    textViewNamaMinuman.text = drink.namaMinuman
                    textViewBeratMinuman.text = "${drink.beratMinuman} ml"
                    textViewJumlahStokMinuman.text = "Stok: ${drink.jumlahStok.toStockWithItem()}"

                    btnUbah.setOnClickListener {
                        onEditClick(drink)
                    }

                    btnHapus.setOnClickListener {
                        onDeleteClick(drink)
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
                    oldItem is MinumanEntity && newItem is MinumanEntity -> oldItem.id == newItem.id
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

    fun submitDrinkData(items: List<MinumanEntity>) {
        val limitedStock = items.filter { it.jumlahStok < 35 }
        val mediumStock = items.filter { it.jumlahStok in 35..69 }
        val sufficientStock = items.filter { it.jumlahStok >= 70 }

        val list = mutableListOf<Any>()

        if (limitedStock.isNotEmpty()) {
            list.add(StockHeader("Stok Terbatas"))
            list.addAll(limitedStock)
        }

        if (mediumStock.isNotEmpty()) {
            list.add(StockHeader("Stok Cukup"))
            list.addAll(mediumStock)
        }

        if (sufficientStock.isNotEmpty()) {
            list.add(StockHeader("Stok Terpenuhi"))
            list.addAll(sufficientStock)
        }

        submitList(list)
    }
}