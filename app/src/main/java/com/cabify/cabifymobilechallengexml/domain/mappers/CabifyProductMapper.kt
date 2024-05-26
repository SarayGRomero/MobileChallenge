package com.cabify.cabifymobilechallengexml.domain.mappers

import com.cabify.cabifymobilechallengexml.data.CabifyProductDto
import com.cabify.cabifymobilechallengexml.domain.CabifyProductBo
import com.cabify.cabifymobilechallengexml.domain.ProductCode
import java.util.UUID

fun CabifyProductDto.toBo() = CabifyProductBo(
    id = UUID.randomUUID().toString(),
    code = code?.toProductCode() ?: ProductCode.NONE,
    name = name ?: "",
    price = price ?: 0.0f
)

fun List<CabifyProductDto>.toBo() = map { it.toBo() }

fun String?.toProductCode(): ProductCode = when (this) {
    "VOUCHER" -> ProductCode.VOUCHER
    "TSHIRT" -> ProductCode.TSHIRT
    "MUG" -> ProductCode.MUG
    else -> ProductCode.NONE
}