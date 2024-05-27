package com.cabify.cabifymobilechallengexml.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PromotionsResponse(
    @Json(name = "promotions")
    val promotions: List<CabifyPromotionsDto>? = emptyList()
)

@JsonClass(generateAdapter = true)
data class CabifyPromotionsDto(
    @Json(name = "name")
    val name: String?,
    @Json(name = "description")
    val description: String?,
    @Json(name = "applicableProduct")
    val applicableProduct: String?,
    @Json(name = "minimumProductCount")
    val minimumProductCount: Int?,
    @Json(name = "discountAmount")
    val discountAmount: Float?
)