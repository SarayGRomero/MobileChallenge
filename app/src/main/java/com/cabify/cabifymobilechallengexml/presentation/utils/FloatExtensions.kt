package com.cabify.cabifymobilechallengexml.presentation.utils

fun Float.toPriceFormat() = this.toString() + "€"

fun Float.getTotalPrice(count: Int) = (this * count).toPriceFormat()