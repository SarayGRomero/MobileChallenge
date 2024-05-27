package com.cabify.cabifymobilechallengexml.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CabifyPromotionBo(
    val name: String,
    val description: String,
    val applicableProduct: ProductCode,
    val minimumProductCount: Int,
    val discountAmount: Float
) : Parcelable