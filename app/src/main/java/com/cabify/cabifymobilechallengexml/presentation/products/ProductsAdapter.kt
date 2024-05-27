package com.cabify.cabifymobilechallengexml.presentation.products

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
            when (item.code) {
                ProductCode.VOUCHER -> itemProductIvProductImage.setImageResource(R.drawable.ic_cabify_voucher)
                ProductCode.TSHIRT -> itemProductIvProductImage.setImageResource(R.drawable.ic_cabify_tshirt)
                ProductCode.MUG -> itemProductIvProductImage.setImageResource(R.drawable.ic_cabify_coffee_mug)
                else -> itemProductIvProductImage.setImageResource(R.drawable.ic_default_image)
            }
            itemProductTvProductName.text = item.name
            itemProductCounterView.setCount(item.count)
            setPromotion(item, binding)
            itemProductCounterView.setProductCounterListener { count ->
                item.count = count
                item.appliedPromotion = item.appliedPromotion()
                updatePrice(item, binding.itemProductTvProductPrice)
                onProductCountChangeListener.invoke(currentList)
            }
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