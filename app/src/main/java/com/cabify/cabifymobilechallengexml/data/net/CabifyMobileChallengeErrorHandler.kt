package com.cabify.cabifymobilechallengexml.data.net

import com.cabify.cabifymobilechallengexml.domain.exceptions.InternalServerErrorException
import com.cabify.cabifymobilechallengexml.domain.exceptions.UnauthorizedException
import com.cabify.cabifymobilechallengexml.domain.exceptions.UnknownException
import okhttp3.Response
import java.net.HttpURLConnection

class CabifyMobileChallengeErrorHandler : ErrorHandler {
    override fun handleError(response: Response): Throwable =
        when (response.code) {
            // TODO implement an especific exception for each error
            HttpURLConnection.HTTP_UNAUTHORIZED -> UnauthorizedException(response.message)
            HttpURLConnection.HTTP_INTERNAL_ERROR -> InternalServerErrorException(response.message)
            else -> UnknownException
        }
}
