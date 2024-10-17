package com.shalfa.marketsupplies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shalfa.marketsupplies.entity.MakananEntity

class MarketAdapter : RecyclerView.Adapter<MarketAdapter.MarketViewHolder>() {
    private var items: List<Any> = emptyList()

    fun setData(newItems: List<Any>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarketViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(android.R.layout.simple_list_item_2, parent, false)
        return MarketViewHolder(view)
    }

    override fun onBindViewHolder(holder: MarketViewHolder, position: Int) {
        val item = items[position]
        when (item) {
            is MakananEntity -> holder.bind("${item.nama} - ${item.berat} kg", "Stok: ${item.stok}")
            is MinumanEntity -> holder.bind("${item.nama} - ${item.berat} L", "Stok: ${item.stok}")
            is KebutuhanEntity -> holder.bind("${item.nama} - ${item.berat} kg", "Stok: ${item.stok}")
        }
    }

    override fun getItemCount(): Int = items.size

    class MarketViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val text1: TextView = itemView.findViewById(android.R.id.text1)
        private val text2: TextView = itemView.findViewById(android.R.id.text2)

        fun bind(text1String: String, text2String: String) {
            text1.text = text1String
            text2.text = text2String
        }
    }
}