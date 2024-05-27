package com.auraseguros.auraclientes.domain.exceptions.validation

import java.io.IOException

class BadRequestException(message: String?) : IOException(message)