package com.cabify.cabifymobilechallengexml.domain.utils

import com.cabify.cabifymobilechallengexml.domain.models.CabifyProductBo
import com.cabify.cabifymobilechallengexml.domain.models.CabifyPromotionBo
import com.cabify.cabifymobilechallengexml.domain.models.ProductCode

fun ProductCode.hasPromotion(promotions: List<CabifyPromotionBo>) = promotions.any { promotion ->
    this == promotion.applicableProduct
}

fun ProductCode.getPromotion(promotions: List<CabifyPromotionBo>) = promotions.firstOrNull { promotion ->
    this == promotion.applicableProduct
}