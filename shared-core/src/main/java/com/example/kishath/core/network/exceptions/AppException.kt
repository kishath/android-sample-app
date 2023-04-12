package com.example.kishath.core.network.exceptions

open class AppException(
    private val localizedMessage: String? = null,
    private val description: String? = null,
    private val throwable: Throwable? = null
) : Exception() {
    fun getMessageError(): String? {
        return localizedMessage ?: throwable?.message
    }

    fun getDescription(): String? {
        return description ?: throwable?.cause?.message
    }
}