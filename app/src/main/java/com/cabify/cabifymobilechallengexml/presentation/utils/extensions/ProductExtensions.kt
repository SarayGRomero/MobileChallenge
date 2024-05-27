package com.cabify.cabifymobilechallengexml.presentation.utils.extensions

import android.text.SpannableString
import com.cabify.cabifymobilechallengexml.R
import com.cabify.cabifymobilechallengexml.domain.models.ProductCode
import com.cabify.cabifymobilechallengexml.presentation.models.CabifyProductVo

fun CabifyProductVo.appliedPromotion(): Boolean =
    this.hasPromotion && this.promotion != null && this.count >= this.promotion.minimumProductCount

fun CabifyProductVo.getPrice(): SpannableString = if (appliedPromotion()) {
    this.price.toDiscountPriceFormat(this.promotion?.discountAmount ?: 0.0f)
} else {
    SpannableString(this.price.toPriceFormat())
}

fun CabifyProductVo.getProductDrawable(): Int =
    when (this.code) {
        ProductCode.VOUCHER -> R.drawable.ic_cabify_voucher
        ProductCode.TSHIRT -> R.drawable.ic_cabify_tshirt
        ProductCode.MUG -> R.drawable.ic_cabify_coffee_mug
        else -> R.drawable.ic_default_image
    }