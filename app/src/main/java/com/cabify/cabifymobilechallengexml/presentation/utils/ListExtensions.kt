package com.cabify.cabifymobilechallengexml.presentation.utils

import com.cabify.cabifymobilechallengexml.presentation.models.CabifyProductVo

fun List<CabifyProductVo>.getPrice(): Float = this.sumOf { it.count * it.price.toDouble() }.toFloat()