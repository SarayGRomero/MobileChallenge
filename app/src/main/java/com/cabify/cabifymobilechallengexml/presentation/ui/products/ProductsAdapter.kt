package com.cabify.cabifymobilechallengexml.presentation.ui.products

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cabify.cabifymobilechallengexml.R
import com.cabify.cabifymobilechallengexml.databinding.ItemProductBinding
import com.cabify.cabifymobilechallengexml.domain.models.CabifyPromotionBo
import com.cabify.cabifymobilechallengexml.domain.models.ProductCode
import com.cabify.cabifymobilechallengexml.presentation.models.CabifyProductVo
import com.cabify.cabifymobilechallengexml.presentation.utils.extensions.appliedPromotion
import com.cabify.cabifymobilechallengexml.presentation.utils.extensions.getPrice
import com.cabify.cabifymobilechallengexml.presentation.utils.extensions.getProductDrawable

class ProductsAdapter(
    private val onProductCountChangeListener: (List<CabifyProductVo>) -> Unit,
    private val onPromotionClickListener: (CabifyPromotionBo) -> Unit
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
            itemProductIvProductImage.setImageResource(item.getProductDrawable())
            itemProductTvProductName.text = item.name
            itemProductCounterView.setCount(item.count)
            setPromotion(item, binding)
            setCounterViewListener(item, binding)
        }

        private fun setPromotion(item: CabifyProductVo, binding: ItemProductBinding) = with(binding) {
            itemProductTvPromotion.visibility = if (item.hasPromotion) View.VISIBLE else View.GONE
            itemProductTvPromotion.setOnClickListener {
                item.promotion?.let {
                    onPromotionClickListener.invoke(it)
                }
            }
            if (item.hasPromotion) itemProductTvPromotion.text = item.promotion?.name
            updatePrice(item, binding.itemProductTvProductPrice)
        }

        private fun setCounterViewListener(item: CabifyProductVo, binding: ItemProductBinding) {
            binding.itemProductCounterView.setProductCounterListener { count ->
                item.count = count
                item.appliedPromotion = item.appliedPromotion()
                updatePrice(item, binding.itemProductTvProductPrice)
                onProductCountChangeListener.invoke(currentList)
            }
        }

        private fun updatePrice(item: CabifyProductVo, itemProductTvProductPrice: TextView) {
            itemProductTvProductPrice.text = item.getPrice()
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