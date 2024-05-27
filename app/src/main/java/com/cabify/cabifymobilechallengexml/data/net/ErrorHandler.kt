package com.cabify.cabifymobilechallengexml.data.net

import okhttp3.Response

fun interface ErrorHandler {
    fun handleError(response: Response): Throwable
}
