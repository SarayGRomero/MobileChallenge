package com.cabify.cabifymobilechallengexml.di

import com.cabify.cabifymobilechallengexml.domain.ProductsRepository
import com.cabify.cabifymobilechallengexml.domain.usecases.GetProductsUseCase
import com.cabify.cabifymobilechallengexml.domain.usecases.GetProductsUseCaseImpl
import com.cabify.cabifymobilechallengexml.domain.usecases.GetProductsWithPromotionsAppliedUseCase
import com.cabify.cabifymobilechallengexml.domain.usecases.GetProductsWithPromotionsAppliedUseCaseImpl
import com.cabify.cabifymobilechallengexml.domain.usecases.GetPromotionsUseCase
import com.cabify.cabifymobilechallengexml.domain.usecases.GetPromotionsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {
    @Provides
    fun provideGetProductsUseCase(
        productsRepository: ProductsRepository
    ) = GetProductsUseCaseImpl(
        productsRepository = productsRepository
    ) as GetProductsUseCase

    @Provides
    fun provideGetPromotionsUseCase(
        productsRepository: ProductsRepository
    ) = GetPromotionsUseCaseImpl(
        productsRepository = productsRepository
    ) as GetPromotionsUseCase

    @Provides
    fun provideGetProductsWithPromotionsApplied(
        getProductsUseCase: GetProductsUseCase,
        getPromotionsUseCase: GetPromotionsUseCase
    ) = GetProductsWithPromotionsAppliedUseCaseImpl(
        getProductsUseCase = getProductsUseCase,
        getPromotionsUseCase = getPromotionsUseCase
    ) as GetProductsWithPromotionsAppliedUseCase
}