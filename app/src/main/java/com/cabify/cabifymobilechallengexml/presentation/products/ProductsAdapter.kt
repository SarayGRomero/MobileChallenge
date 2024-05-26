package com.cabify.cabifymobilechallengexml.presentation.products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cabify.cabifymobilechallengexml.R
import com.cabify.cabifymobilechallengexml.databinding.ItemProductBinding
import com.cabify.cabifymobilechallengexml.domain.ProductCode
import com.cabify.cabifymobilechallengexml.presentation.models.CabifyProductVo
import com.cabify.cabifymobilechallengexml.presentation.utils.toPriceFormat

class ProductsAdapter(
    private val onProductCountChangeListener: (List<CabifyProductVo>) -> Unit
) : ListAdapter<CabifyProductVo, ProductsAdapter.ProductsViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ProductsViewHolder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CabifyProductVo) = with(binding) {
            when (item.code) {
                ProductCode.VOUCHER -> itemProductIvProductImage.setImageResource(R.drawable.ic_cabify_voucher)
                ProductCode.TSHIRT -> itemProductIvProductImage.setImageResource(R.drawable.ic_cabify_tshirt)
                ProductCode.MUG -> itemProductIvProductImage.setImageResource(R.drawable.ic_cabify_coffee_mug)
                else -> itemProductIvProductImage.setImageResource(R.drawable.ic_default_image)
            }
            itemProductTvProductName.text = item.name
            itemProductTvProductPrice.text = item.price.toPriceFormat()
            itemProductCounterView.setProductCounterListener { count ->
                item.count = count
                onProductCountChangeListener.invoke(currentList)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<CabifyProductVo>() {
        override fun areItemsTheSame(oldItem: CabifyProductVo, newItem: CabifyProductVo): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: CabifyProductVo, newItem: CabifyProductVo): Boolean =
            oldItem.id == newItem.id &&
                    oldItem.code == newItem.code &&
                    oldItem.name == newItem.name &&
                    oldItem.price == newItem.price
    }
}