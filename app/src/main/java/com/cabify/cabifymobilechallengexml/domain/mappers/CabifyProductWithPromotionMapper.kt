package com.cabify.cabifymobilechallengexml.domain.mappers

import com.cabify.cabifymobilechallengexml.domain.models.CabifyProductBo
import com.cabify.cabifymobilechallengexml.domain.models.CabifyProductWithPromotionBo
import com.cabify.cabifymobilechallengexml.domain.models.CabifyPromotionBo
import com.cabify.cabifymobilechallengexml.domain.utils.getPromotion
import com.cabify.cabifymobilechallengexml.domain.utils.hasPromotion

fun CabifyProductBo.toProductWithPromotion(promotions: List<CabifyPromotionBo>) = CabifyProductWithPromotionBo(
    id = id,
    code = code,
    name = name,
    price = price,
    hasPromotion = this.code.hasPromotion(promotions),
    promotion = this.code.getPromotion(promotions)
)