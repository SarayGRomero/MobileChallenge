package com.cabify.cabifymobilechallengexml.domain.mappers

import com.cabify.cabifymobilechallengexml.data.models.CabifyPromotionsDto
import com.cabify.cabifymobilechallengexml.domain.models.CabifyPromotionBo

fun CabifyPromotionsDto.toBo(): CabifyPromotionBo = CabifyPromotionBo(
    name = name ?: "",
    description = description ?: "",
    applicableProduct = applicableProduct.toProductCode(),
    minimumProductCount = minimumProductCount ?: 0,
    discountAmount = discountAmount ?: 0.0f
)

fun List<CabifyPromotionsDto>.toBo(): List<CabifyPromotionBo> = map { it.toBo() }