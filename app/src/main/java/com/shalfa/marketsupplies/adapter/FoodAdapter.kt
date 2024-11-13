package com.shalfa.marketsupplies.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shalfa.marketsupplies.databinding.ItemFoodBinding
import com.shalfa.marketsupplies.databinding.ItemHeaderBinding
import com.shalfa.marketsupplies.entity.MakananEntity
import com.shalfa.marketsupplies.toStockWithItem

class FoodAdapter(
    private val onEditClick: (MakananEntity) -> Unit,
    private val onDeleteClick: (MakananEntity) -> Unit
) : ListAdapter<Any, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    // Enum untuk menentukan tipe tampilan
    enum class ITEM_VIEW_TYPE {
        HEADER,
        FOOD
    }

    // Class untuk header kategori stok
    data class StockHeader(val title: String)

    // ViewHolder untuk item makanan
    class FoodViewHolder(val binding: ItemFoodBinding) : RecyclerView.ViewHolder(binding.root)

    // ViewHolder untuk header kategori stok
    class HeaderViewHolder(val binding: ItemHeaderBinding) : RecyclerView.ViewHolder(binding.root)

    // Menentukan tipe tampilan (header atau item makanan)
    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is StockHeader -> ITEM_VIEW_TYPE.HEADER.ordinal
            else -> ITEM_VIEW_TYPE.FOOD.ordinal
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
                val binding = ItemFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                FoodViewHolder(binding)
            }
        }
    }

    // Mengikat data pada ViewHolder
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is FoodViewHolder -> {
                val food = getItem(position) as MakananEntity
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
                    oldItem is MakananEntity && newItem is MakananEntity -> oldItem.id == newItem.id
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

    // Fungsi untuk mengelompokkan makanan berdasarkan kategori stok dan submit data
    fun submitFoodData(items: List<MakananEntity>) {
        val limitedStock = items.filter { it.jumlahStok < 35 }
        val mediumStock = items.filter { it.jumlahStok in 35..70 }
        val sufficientStock = items.filter { it.jumlahStok >= 71 }

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