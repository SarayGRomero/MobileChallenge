package com.cabify.cabifymobilechallengexml.presentation.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.cabify.cabifymobilechallengexml.databinding.ViewProductCounterBinding
import com.cabify.cabifymobilechallengexml.presentation.utils.MINIMUM_PRODUCT_COUNT

class ProductCounterView(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {
    private var binding: ViewProductCounterBinding = ViewProductCounterBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    private var count: Int = MINIMUM_PRODUCT_COUNT
    private var listener: OnProductCounterListener? = null

    init {
        setAddClickListener()
        setRemoveClickListener()
        updateUI()
    }

    fun setProductCounterListener(listener: OnProductCounterListener) {
        this.listener = listener
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

    fun setCount(count: Int) {
        this.count = count
        updateUI()
    }

    private fun updateUI() = with(binding) {
        productCounterTvCount.text = count.toString()
        productCounterBtnRemove.apply {
            isEnabled = count > 0
            alpha = if (count > 0) 1f else 0.5f
        }
        listener?.onProductCountChanged(count)
    }
}

fun interface OnProductCounterListener {
    fun onProductCountChanged(count: Int)
}