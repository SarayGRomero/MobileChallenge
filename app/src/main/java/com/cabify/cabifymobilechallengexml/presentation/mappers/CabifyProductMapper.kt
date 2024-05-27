package com.cabify.cabifymobilechallengexml.presentation.mappers

import com.cabify.cabifymobilechallengexml.domain.models.CabifyProductWithPromotionBo
import com.cabify.cabifymobilechallengexml.presentation.models.CabifyProductVo
import java.util.UUID

fun CabifyProductWithPromotionBo.toVo() = CabifyProductVo(
    id = UUID.randomUUID().toString(),
    code = code,
    name = name,
    price = price,
    count = 0,
    hasPromotion = hasPromotion,
    promotion = promotion
)