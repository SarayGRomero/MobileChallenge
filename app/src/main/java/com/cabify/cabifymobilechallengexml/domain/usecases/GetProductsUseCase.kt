package com.cabify.cabifymobilechallengexml.domain.usecases

import com.cabify.cabifymobilechallengexml.domain.ProductsRepository
import com.cabify.cabifymobilechallengexml.domain.exceptions.UnknownException
import com.cabify.cabifymobilechallengexml.domain.models.CabifyProductBo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun interface GetProductsUseCase {
    operator fun invoke(): Flow<List<CabifyProductBo>>
}

class GetProductsUseCaseImpl(
    private val productsRepository: ProductsRepository
) : GetProductsUseCase {
    override fun invoke(): Flow<List<CabifyProductBo>> = flow {
        val products = productsRepository.getProducts()
        if (!products.isNullOrEmpty()) {
            emit(products)
        } else {
            throw UnknownException
        }
    }
}