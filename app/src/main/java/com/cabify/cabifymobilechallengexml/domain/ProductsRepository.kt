package com.cabify.cabifymobilechallengexml.domain

import android.content.Context
import com.cabify.cabifymobilechallengexml.domain.models.CabifyProductBo
import com.cabify.cabifymobilechallengexml.domain.models.CabifyPromotionBo

interface ProductsRepository {
    suspend fun getProducts(): List<CabifyProductBo>?
    suspend fun getPromotions(context: Context): List<CabifyPromotionBo>?
}