package com.cabify.cabifymobilechallengexml.di

import android.content.Context
import com.cabify.cabifymobilechallengexml.CabifyApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideApp(@ApplicationContext context: Context): CabifyApplication = context as CabifyApplication
}