package com.cabify.cabifymobilechallengexml.presentation.views

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.cabify.cabifymobilechallengexml.databinding.ViewProductCounterBinding
import com.cabify.cabifymobilechallengexml.presentation.utils.MINIMUM_PRODUCT_COUNT

class ProductCounterView(context: Context) : LinearLayout(context) {

    private var binding: ViewProductCounterBinding = ViewProductCounterBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    private var count: Int = MINIMUM_PRODUCT_COUNT

    init {
        setAddClickListener()
        setRemoveClickListener()
        updateUI()
    }

    private fun setAddClickListener() {
        binding.productCounterBtnAdd.setOnClickListener {
            addProduct()
        }
    }

    private fun setRemoveClickListener() {
        binding.productCounterBtnRemove.setOnClickListener {
            removeProduct()
        }
    }

    private fun addProduct() {
        count++
        updateUI()
    }

    private fun removeProduct() {
        if (count > 0) count--
        updateUI()
    }

    private fun updateUI() = with(binding) {
        productCounterTvCount.text = count.toString()
        productCounterBtnRemove.isEnabled = count > 0
    }

    fun getProductCount() = count
}