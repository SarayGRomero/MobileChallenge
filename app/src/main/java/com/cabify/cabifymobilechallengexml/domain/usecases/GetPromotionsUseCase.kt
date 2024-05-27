package com.cabify.cabifymobilechallengexml.domain.usecases

import android.content.Context
import com.cabify.cabifymobilechallengexml.domain.ProductsRepository
import com.cabify.cabifymobilechallengexml.domain.models.CabifyPromotionBo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun interface GetPromotionsUseCase {
    operator fun invoke(context: Context): Flow<List<CabifyPromotionBo>>
}

class GetPromotionsUseCaseImpl(
    private val productsRepository: ProductsRepository
) : GetPromotionsUseCase {
    override fun invoke(context: Context): Flow<List<CabifyPromotionBo>> = flow {
        val promotions = productsRepository.getPromotions(context)
        if (!promotions.isNullOrEmpty()) {
            emit(promotions)
        } else {
            throw Exception("No promotions found")
        }
    }
}