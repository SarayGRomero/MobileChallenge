package com.cabify.cabifymobilechallengexml.data

import javax.inject.Inject

class ProductsDataSourceImpl @Inject constructor(
    private val ws: CabifyProductWs
) : ProductsDataSource {
    override suspend fun getProducts(): List<CabifyProductDto>? = ws.getProducts().body()?.products
}