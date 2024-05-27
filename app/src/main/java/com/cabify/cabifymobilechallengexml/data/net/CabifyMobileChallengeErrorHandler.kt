package com.auraseguros.auraclientes.data.net

import com.auraseguros.auraclientes.domain.exceptions.validation.InternalServerException
import com.auraseguros.auraclientes.domain.exceptions.validation.UnauthorizedException
import com.auraseguros.auraclientes.domain.exceptions.validation.UnknownException
import okhttp3.Response
import java.net.HttpURLConnection

class AuraErrorHandler : ErrorHandler {
    override fun handleError(response: Response): Throwable =
        when (response.code) {
            HttpURLConnection.HTTP_UNAUTHORIZED -> UnauthorizedException
            HttpURLConnection.HTTP_INTERNAL_ERROR -> InternalServerException
            else -> UnknownException
        }
}
