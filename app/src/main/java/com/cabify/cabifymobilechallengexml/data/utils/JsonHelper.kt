package com.cabify.cabifymobilechallengexml.data.utils

import android.content.Context
import com.cabify.cabifymobilechallengexml.data.models.CabifyPromotionsDto
import com.cabify.cabifymobilechallengexml.data.models.PromotionsResponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader


class JsonHelper {
    private fun readJsonFromResource(context: Context, resourceId: Int): String {
        val stringBuilder = StringBuilder()
        try {
            val resources = context.resources
            val inputStream = resources.openRawResource(resourceId)
            val bufferedReader = BufferedReader(InputStreamReader(inputStream))
            var line: String?
            while (bufferedReader.readLine().also { line = it } != null) {
                stringBuilder.append(line)
            }
            bufferedReader.close()
            inputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return stringBuilder.toString()
    }

    fun getPromotions(context: Context, resourceId: Int): PromotionsResponse {
        val json = readJsonFromResource(context, resourceId)

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val jsonAdapter = moshi.adapter(PromotionsResponse::class.java)

        return jsonAdapter.fromJson(json) ?: PromotionsResponse()
    }
}

