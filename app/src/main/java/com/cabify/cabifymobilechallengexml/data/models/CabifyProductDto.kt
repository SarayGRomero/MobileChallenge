package com.cabify.cabifymobilechallengexml.data.models

import com.squareup.moshi.Json

data class CabifyProductResponse(
    @Json(name = "products")
    val products: List<CabifyProductDto>?
)

data class CabifyProductDto(
    @Json(name = "code")
    val code: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "price")
    val price: Float?
)