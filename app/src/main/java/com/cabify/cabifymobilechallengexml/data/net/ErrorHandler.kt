package com.auraseguros.auraclientes.data.net

import okhttp3.Response

interface ErrorHandler {
    fun handleError(response: Response): Throwable
}
