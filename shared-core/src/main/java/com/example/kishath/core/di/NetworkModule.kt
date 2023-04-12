package com.example.kishath.core.di

import com.example.kishath.core.network.interceptors.AuthHeaderInterceptor
import com.example.kishath.core.network.interceptors.CacheInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideOkHttpClient(
        networkConfiguration: NetworkConfiguration,
        authHeaderInterceptor: AuthHeaderInterceptor,
        cacheInterceptor: CacheInterceptor
    ): OkHttpClient {
        val client = OkHttpClient.Builder()
            .connectTimeout(networkConfiguration.requestTimeoutInSeconds, TimeUnit.SECONDS)
            .readTimeout(networkConfiguration.requestTimeoutInSeconds, TimeUnit.SECONDS)
            .writeTimeout(networkConfiguration.requestTimeoutInSeconds, TimeUnit.SECONDS)
            .addInterceptor(authHeaderInterceptor)
            .addInterceptor(cacheInterceptor)

        if (networkConfiguration.shouldEnableLogging) {
            client.addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }

        return client.build()
    }

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        networkConfiguration: NetworkConfiguration
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(networkConfiguration.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
}