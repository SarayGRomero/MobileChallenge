package com.cabify.cabifymobilechallengexml.presentation.utils.extensions

import com.cabify.cabifymobilechallengexml.presentation.models.CabifyProductVo
import com.cabify.cabifymobilechallengexml.presentation.utils.constants.DEFAULT_PRICE

fun List<CabifyProductVo>.getPrice(): Float = this.sumOf { it.count * it.price.toDouble() }.toFloat()

fun List<CabifyProductVo>.getDiscountPrice(): Float =
    this.sumOf {
        if (it.appliedPromotion) {
            it.promotion?.let { promotion ->
                it.count * promotion.discountAmount.toDouble()
            } ?: DEFAULT_PRICE
        } else {
            it.count * it.price.toDouble()
        }
    }.toFloat()