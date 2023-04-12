package com.example.kishath.core.network.exceptions

class RequestFailureException(
    val errorCode: Int? = null,
    message: String? = null,
    description: String? = null,
    val apiErrorCode: String? = null
) : AppException(message, description)