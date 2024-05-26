package com.cabify.cabifymobilechallengexml.presentation.mappers

import com.cabify.cabifymobilechallengexml.domain.CabifyProductBo
import com.cabify.cabifymobilechallengexml.presentation.models.CabifyProductVo
import java.util.UUID

fun CabifyProductBo.toVo() = CabifyProductVo(
    id = UUID.randomUUID().toString(),
    code = code,
    name = name,
    price = price,
    count = 0
)