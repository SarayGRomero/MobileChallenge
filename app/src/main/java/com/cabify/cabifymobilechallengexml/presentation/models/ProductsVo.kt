package com.cabify.cabifymobilechallengexml.presentation.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductsVo(
    val products: List<CabifyProductVo> = emptyList(),
    val totalPrice: Float = 0.0f,
    val discountPrice: Float = 0.0f
) : Parcelable