package com.cabify.cabifymobilechallengexml.presentation.ui.shoppingCart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cabify.cabifymobilechallengexml.R
import com.cabify.cabifymobilechallengexml.databinding.ItemProductSelectedBinding
import com.cabify.cabifymobilechallengexml.domain.models.CabifyPromotionBo
import com.cabify.cabifymobilechallengexml.domain.models.ProductCode
import com.cabify.cabifymobilechallengexml.presentation.models.CabifyProductVo
import com.cabify.cabifymobilechallengexml.presentation.utils.extensions.getCountWithShoppingCartFormat
import com.cabify.cabifymobilechallengexml.presentation.utils.extensions.getPrice
import com.cabify.cabifymobilechallengexml.presentation.utils.extensions.getProductDrawable
import com.cabify.cabifymobilechallengexml.presentation.utils.extensions.getTotalPrice

class SelectedProductsAdapter(
    private val showPromotionInfo: (CabifyPromotionBo) -> Unit
) :
    ListAdapter<CabifyProductVo, SelectedProductsAdapter.ProductsViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val binding = ItemProductSelectedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ProductsViewHolder(private val binding: ItemProductSelectedBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CabifyProductVo) = with(binding) {
            itemProductIvProductImage.setImageResource(item.getProductDrawable())
            itemSelectedProductTvProductName.text = item.name
            itemSelectedProductTvPrice.text = item.getPrice()
            setTotalAmount(item, binding)
            setDiscountInfoViews(binding, item)
        }

        private fun setTotalAmount(item: CabifyProductVo, binding: ItemProductSelectedBinding) {
            val totalPrice = item.price.getTotalPrice(item.count)
            val totalDiscountPrice = item.promotion?.discountAmount?.getTotalPrice(item.count)
            binding.itemSelectedProductTvTotalPrice.text =
                totalPrice.getTotalPrice(totalDiscountPrice, item.appliedPromotion)
        }

        private fun setDiscountInfoViews(binding: ItemProductSelectedBinding, item: CabifyProductVo) = with(binding) {
            itemSelectedProductIvInfo.visibility = if (item.appliedPromotion) View.VISIBLE else View.GONE
            itemSelectedProductIvInfo.setOnClickListener {
                item.promotion?.let { promotion -> showPromotionInfo.invoke(promotion) }
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