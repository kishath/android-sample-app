package com.example.kishath.core.network.interceptors

import com.example.kishath.core.di.NetworkConfiguration
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthHeaderInterceptor @Inject constructor(
    private val networkConfiguration: NetworkConfiguration
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request()).newBuilder()
            .header("Authorization", "Bearer ${networkConfiguration.apiToken}")
            .build()
    }
}