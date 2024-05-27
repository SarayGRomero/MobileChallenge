package com.cabify.cabifymobilechallengexml.domain.usecases

import android.content.Context
import com.cabify.cabifymobilechallengexml.domain.mappers.toProductWithPromotion
import com.cabify.cabifymobilechallengexml.domain.models.CabifyProductBo
import com.cabify.cabifymobilechallengexml.domain.models.CabifyProductWithPromotionBo
import com.cabify.cabifymobilechallengexml.domain.models.CabifyPromotionBo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun interface GetProductsWithPromotionsAppliedUseCase {
    operator fun invoke(context: Context): Flow<List<CabifyProductWithPromotionBo>>
}

class GetProductsWithPromotionsAppliedUseCaseImpl(
    private val getProductsUseCase: GetProductsUseCase,
    private val getPromotionsUseCase: GetPromotionsUseCase
) : GetProductsWithPromotionsAppliedUseCase {
    override fun invoke(context: Context): Flow<List<CabifyProductWithPromotionBo>> = flow {
        var products = emptyList<CabifyProductBo>()
        var promotions = emptyList<CabifyPromotionBo>()
        getProductsUseCase().collect {
            products = it
        }
        getPromotionsUseCase(context).collect {
            promotions = it
        }

        val productsWithPromotions = products.map { product -> product.toProductWithPromotion(promotions) }
        if (productsWithPromotions.isNotEmpty()) {
            emit(productsWithPromotions)
        } else {
            throw Exception("No products with promotions found")
        }
    }
}

