package com.cabify.cabifymobilechallengexml.di

import com.cabify.cabifymobilechallengexml.data.ProductsDataSource
import com.cabify.cabifymobilechallengexml.domain.ProductsRepository
import com.cabify.cabifymobilechallengexml.domain.ProductsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {
    @Provides
    fun provideRickMortyRepository(
        productsDataSource: ProductsDataSource
    ) = ProductsRepositoryImpl(productsDataSource) as ProductsRepository
}