package com.cabify.cabifymobilechallengexml.data

import retrofit2.Response
import retrofit2.http.GET

interface CabifyProductWs {
    @GET("palcalde/6c19259bd32dd6aafa327fa557859c2f/raw/ba51779474a150ee4367cda4f4ffacdcca479887/")
    suspend fun getProducts(): Response<CabifyProductResponse>
}