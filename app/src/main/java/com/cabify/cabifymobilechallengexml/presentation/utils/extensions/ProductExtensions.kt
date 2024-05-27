package com.cabify.cabifymobilechallengexml.presentation.utils.extensions

import android.text.SpannableString
import com.cabify.cabifymobilechallengexml.presentation.models.CabifyProductVo

fun CabifyProductVo.appliedPromotion(): Boolean =
    this.hasPromotion && this.promotion != null && this.count >= this.promotion.minimumProductCount

fun CabifyProductVo.getPrice(): SpannableString = if (appliedPromotion()) {
    this.price.toDiscountPriceFormat(this.promotion?.discountAmount ?: 0.0f)
} else {
    SpannableString(this.price.toPriceFormat())
}

fun Float.getTotalPrice(discountTotal: Float?, appliedPromotion: Boolean): SpannableString =
    if (appliedPromotion && discountTotal != null) {
        discountTotal.let {
            this.toDiscountPriceFormat(it)
        }
    } else {
        SpannableString(this.toPriceFormat())
    }