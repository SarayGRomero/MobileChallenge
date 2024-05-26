package com.cabify.cabifymobilechallengexml.domain

interface ProductsRepository {
    suspend fun getProducts(): List<CabifyProductBo>?
}