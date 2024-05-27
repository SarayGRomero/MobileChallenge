package com.cabify.cabifymobilechallengexml.di

import com.cabify.cabifymobilechallengexml.data.net.CabifyProductWs
import com.cabify.cabifymobilechallengexml.data.ProductsDataSource
import com.cabify.cabifymobilechallengexml.data.ProductsDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataSourcesModule {
    @Provides
    fun provideProductDataSource(ws: CabifyProductWs) = ProductsDataSourceImpl(ws) as ProductsDataSource
}