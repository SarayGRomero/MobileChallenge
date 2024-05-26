package com.cabify.cabifymobilechallengexml.di

import com.cabify.cabifymobilechallengexml.data.CabifyProductWs
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {
    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://gist.githubusercontent.com/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Singleton
    @Provides
    fun provideWs(retrofit: Retrofit): CabifyProductWs {
        return retrofit.create(CabifyProductWs::class.java)
    }

    @Provides
    fun okHttpClientProvider() =
        OkHttpClient.Builder().apply {
            val httpLoggingInterceptor =
                HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            addInterceptor(httpLoggingInterceptor)
        }.build()
}