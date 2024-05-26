package com.cabify.cabifymobilechallengexml.data

interface ProductsDataSource {
    suspend fun getProducts(): List<CabifyProductDto>?
}