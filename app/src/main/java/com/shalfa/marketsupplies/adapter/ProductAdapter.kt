package com.shalfa.marketsupplies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shalfa.marketsupplies.databinding.ItemProductBinding

class ProductAdapter(
    private val onClick: (String) -> Unit
) : ListAdapter<String, ProductAdapter.ProductViewHolder>(DIFF_CALLBACK) {

    class ProductViewHolder(
        private val binding: ItemProductBinding,
        val onClick: (String) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        private var currentCategory: String? = null

        init {
            binding.root.setOnClickListener {
                currentCategory?.let {
                    onClick(it)
                }
            }
        }

        fun bind(category: String) {
            currentCategory = category
            binding.textViewCategory.text = category
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }
        }
    }
}