package com.cabify.cabifymobilechallengexml.domain

import android.content.Context
import com.cabify.cabifymobilechallengexml.data.ProductsDataSource
import com.cabify.cabifymobilechallengexml.domain.mappers.toBo
import com.cabify.cabifymobilechallengexml.domain.models.CabifyProductBo
import com.cabify.cabifymobilechallengexml.domain.models.CabifyPromotionBo
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val productsDataSource: ProductsDataSource
) : ProductsRepository {
    override suspend fun getProducts(): List<CabifyProductBo>? = productsDataSource.getProducts()?.toBo()

    override suspend fun getPromotions(context: Context): List<CabifyPromotionBo>? =
        productsDataSource.getPromotions(context)?.toBo()

}