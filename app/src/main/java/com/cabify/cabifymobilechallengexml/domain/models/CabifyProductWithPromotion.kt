package com.cabify.cabifymobilechallengexml.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CabifyProductWithPromotionBo(
    val id: String,
    val code: ProductCode,
    val name: String,
    val price: Float,
    val hasPromotion: Boolean,
    val promotion: CabifyPromotionBo?
) : Parcelable
