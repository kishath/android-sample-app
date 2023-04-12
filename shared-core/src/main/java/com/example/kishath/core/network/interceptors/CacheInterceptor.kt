package com.example.kishath.core.network.interceptors

import com.example.kishath.core.di.NetworkConfiguration
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CacheInterceptor @Inject constructor(
    private val networkConfiguration: NetworkConfiguration
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        val cacheControl = CacheControl.Builder()
            .maxAge(networkConfiguration.cacheMaxAgeInSeconds.toInt(), TimeUnit.SECONDS)
            .build()

        response.newBuilder()
            .header("Cache-Control", cacheControl.toString())
            .build()

        return response
    }
}