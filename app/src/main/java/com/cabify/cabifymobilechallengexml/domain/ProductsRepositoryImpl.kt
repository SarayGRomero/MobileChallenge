package com.cabify.cabifymobilechallengexml.domain

import com.cabify.cabifymobilechallengexml.data.ProductsDataSource
import com.cabify.cabifymobilechallengexml.data.ProductsDataSourceImpl
import com.cabify.cabifymobilechallengexml.domain.mappers.toBo
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val productsDataSource: ProductsDataSource
) : ProductsRepository {
    override suspend fun getProducts(): List<CabifyProductBo>? = productsDataSource.getProducts()?.toBo()

}