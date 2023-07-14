package com.darvdev.ecommerce.ui.result

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.darvdev.ecommerce.databinding.ItemProductBinding
import com.darvdev.ecommerce.domain.ui.ProductUi
import com.darvdev.ecommerce.ui.utils.capitalizeWithLocal
import com.darvdev.ecommerce.ui.utils.load


class ProductListAdapter: ListAdapter<ProductUi, ProductListAdapter.ProductViewHolder>(Companion) {

    companion object : DiffUtil.ItemCallback<ProductUi>() {
        override fun areItemsTheSame(oldItem: ProductUi, newItem: ProductUi): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ProductUi, newItem: ProductUi): Boolean {
            return oldItem == newItem
        }
    }

    inner class ProductViewHolder(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ))
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = currentList[position]

        val priceFormatted = "$${product.price}"
        val brandFormatted = "Marca: ${product.brand.capitalizeWithLocal()}"

        holder.binding.tvName.text = product.name
        holder.binding.tvPrice.text = priceFormatted
        holder.binding.tvBrand.text = brandFormatted

        holder.binding.ivImage.load(product.image)

        holder.binding.clProductParent.setOnClickListener {
            onProductClickListener?.let { click ->
                click(product)
            }
        }
    }

    protected var onProductClickListener : ((ProductUi) -> Unit)? = null

    fun setProductClickListener(listener: (ProductUi) -> Unit){
        onProductClickListener = listener
    }
}