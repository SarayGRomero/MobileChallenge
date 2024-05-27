package com.cabify.cabifymobilechallengexml.presentation.models

import android.os.Parcelable
import com.cabify.cabifymobilechallengexml.domain.models.CabifyPromotionBo
import com.cabify.cabifymobilechallengexml.domain.models.ProductCode
import kotlinx.parcelize.Parcelize

@Parcelize
data class CabifyProductVo(
    val id: String,
    val code: ProductCode,
    val name: String,
    val price: Float,
    var count: Int,
    val hasPromotion: Boolean,
    val promotion: CabifyPromotionBo?,
    var appliedPromotion: Boolean = false
) : Parcelable