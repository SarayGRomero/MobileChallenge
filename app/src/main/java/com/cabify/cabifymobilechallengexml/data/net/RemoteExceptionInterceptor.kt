package com.auraseguros.auraclientes.data.net.interceptors

import com.auraseguros.auraclientes.data.net.ErrorHandler
import okhttp3.Interceptor

class RemoteExceptionInterceptor(private val errorHandler: ErrorHandler) : Interceptor {
    override fun intercept(chain: Interceptor.Chain) =
        kotlin.run {
            chain.proceed(chain.request()).also { response ->
                if (response.code >= MIN_HTTP_ERROR_CODE) {
                    throw errorHandler.handleError(response)
                }
            }
        }

    companion object {
        const val MIN_HTTP_ERROR_CODE = 300
    }
}
