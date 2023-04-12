package com.example.kishath.core.network.exceptions

/**
 * Exception class to handle errors when required fields from the backend is missing or of invalid types.
 * See DataMapper class.
 */
class CorruptEntityException(
    message: String
) : AppException(message)