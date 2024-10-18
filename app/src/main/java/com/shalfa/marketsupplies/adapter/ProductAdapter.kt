package com.shalfa.marketsupplies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shalfa.marketsupplies.R
import com.shalfa.marketsupplies.entity.KebutuhanEntity

class ProductAdapter(
    private val productList: List<KebutuhanEntity>,
    private val onEditClick: (KebutuhanEntity) -> Unit,  // Callback for Edit
    private val onDeleteClick: (KebutuhanEntity) -> Unit   // Callback for Delete
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNamaKebutuhan: TextView = itemView.findViewById(R.id.textViewNamaKebutuhan)
        val tvBeratKebutuhan: TextView = itemView.findViewById(R.id.textViewBeratKebutuhan) // Adjusted to match the correct ID
        val tvJumlahStok: TextView = itemView.findViewById(R.id.textViewJumlahStokKebutuhan) // Adjusted to match the correct ID
        val btnUbah: Button = itemView.findViewById(R.id.btnUbah) // Changed to match FoodAdapter
        val btnHapus: Button = itemView.findViewById(R.id.btnHapus) // Changed to match FoodAdapter
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.tvNamaKebutuhan.text = product.namaKebutuhan
        holder.tvBeratKebutuhan.text = "${product.beratKebutuhan} g" // Assuming there is a property for weight
        holder.tvJumlahStok.text = "Stok: ${product.jumlahStok}" // Assuming there is a property for stock

        holder.btnUbah.setOnClickListener {
            onEditClick(product)  // Call the callback when the Edit button is clicked
        }

        holder.btnHapus.setOnClickListener {
            onDeleteClick(product)  // Call the callback when the Delete button is clicked
        }
    }

    override fun getItemCount(): Int = productList.size
}