package com.example.kishath.core.domain

import com.example.kishath.core.BuildConfig
import com.example.kishath.core.network.exceptions.AppException
import com.example.kishath.core.network.exceptions.BadConnectionException
import com.example.kishath.core.network.exceptions.RequestFailureException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

abstract class FlowUseCase<in P, R>(private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO) {

    operator fun invoke(parameters: P): Flow<UseCaseResult<R>> = execute(parameters)
        .catch { e ->
            if (BuildConfig.DEBUG) {
                e.printStackTrace()
            }

            emit(UseCaseResult.Error(handleError(throwable = e)))
        }
        .flowOn(coroutineDispatcher)

    protected abstract fun execute(parameters: P): Flow<UseCaseResult<R>>

    private fun handleError(throwable: Throwable): Exception {
        return when (throwable) {
            is UnknownHostException, is SocketTimeoutException, is IOException -> {
                BadConnectionException()
            }
            is HttpException -> {
                RequestFailureException(
                    errorCode = throwable.code(),
                    message = throwable.message,
                )
            }
            else -> AppException(throwable = throwable)
        }
    }
}
