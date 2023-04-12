package com.example.kishath.core.domain

/**
 * Wrapper class to wrap the results of use cases
 */
sealed class UseCaseResult<out R> {
    data class Success<out T>(val data: T) : UseCaseResult<T>()
    data class Error(val exception: Exception) : UseCaseResult<Nothing>()
    object Loading : UseCaseResult<Nothing>()
}