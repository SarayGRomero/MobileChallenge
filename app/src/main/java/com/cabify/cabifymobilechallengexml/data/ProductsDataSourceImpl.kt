package com.cabify.cabifymobilechallengexml.data

import android.content.Context
import com.cabify.cabifymobilechallengexml.R
import com.cabify.cabifymobilechallengexml.data.models.CabifyProductDto
import com.cabify.cabifymobilechallengexml.data.models.CabifyPromotionsDto
import com.cabify.cabifymobilechallengexml.data.net.CabifyProductWs
import com.cabify.cabifymobilechallengexml.data.utils.JsonHelper
import javax.inject.Inject

class ProductsDataSourceImpl @Inject constructor(
    private val ws: CabifyProductWs
) : ProductsDataSource {
    override suspend fun getProducts(): List<CabifyProductDto>? = ws.getProducts().body()?.products
    override suspend fun getPromotions(context: Context): List<CabifyPromotionsDto>? {
        //TODO implement a call to get the list of promotions
        /*
        return ws.getPromotions().body()?.promotions
         */
        return JsonHelper().getPromotions(context, R.raw.promotions).promotions
    }
}