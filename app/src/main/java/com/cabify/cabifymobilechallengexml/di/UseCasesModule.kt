package com.cabify.cabifymobilechallengexml.di

import com.cabify.cabifymobilechallengexml.domain.ProductsRepository
import com.cabify.cabifymobilechallengexml.domain.usecases.GetProductsUseCase
import com.cabify.cabifymobilechallengexml.domain.usecases.GetProductsUseCaseImpl
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
}