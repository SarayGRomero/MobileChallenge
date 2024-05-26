package com.cabify.cabifymobilechallengexml.presentation.models

import com.cabify.cabifymobilechallengexml.domain.ProductCode

data class CabifyProductVo(
    val id: String,
    val code: ProductCode,
    val name: String,
    val price: Float,
    var count: Int
)

data class ProductsVo(
    val products: List<CabifyProductVo>,
    val totalPrice: Float
)