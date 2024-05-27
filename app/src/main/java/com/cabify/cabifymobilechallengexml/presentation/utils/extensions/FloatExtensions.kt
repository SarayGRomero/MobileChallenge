package com.cabify.cabifymobilechallengexml.presentation.utils.extensions

import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StrikethroughSpan
import android.text.style.StyleSpan
import com.cabify.cabifymobilechallengexml.presentation.utils.constants.DEFAULT_PRICE

fun Float?.toPriceFormat() = (this?.toString() ?: DEFAULT_PRICE.toString()) + "€"

fun Float.toDiscountPriceFormat(discountPrice: Float): SpannableString {
    val newPrice = "${this.toPriceFormat()} ${discountPrice.toPriceFormat()}"
    val spannableString = SpannableString(newPrice)

    val spaceIndex = newPrice.indexOf(" ")

    spannableString.apply {
        setSpan(StrikethroughSpan(), 0, spaceIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        setSpan(StyleSpan(Typeface.BOLD), spaceIndex + 1, spannableString.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    }

    return spannableString
}

fun Float.getTotalPrice(count: Int): Float = (this * count)

fun Float.getTotalPrice(discountTotal: Float?, appliedPromotion: Boolean): SpannableString =
    if (appliedPromotion && discountTotal != null) {
        discountTotal.let {
            this.toDiscountPriceFormat(it)
        }
    } else {
        SpannableString(this.toPriceFormat())
    }