package com.cabify.cabifymobilechallengexml.data

import android.content.Context
import com.cabify.cabifymobilechallengexml.data.models.CabifyProductDto
import com.cabify.cabifymobilechallengexml.data.models.CabifyPromotionsDto

interface ProductsDataSource {
    suspend fun getProducts(): List<CabifyProductDto>?
    suspend fun getPromotions(context: Context): List<CabifyPromotionsDto>?
}