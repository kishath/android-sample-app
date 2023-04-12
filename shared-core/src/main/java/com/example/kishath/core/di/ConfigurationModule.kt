package com.example.kishath.core.di

import com.example.kishath.core.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
@Module
@InstallIn(SingletonComponent::class)
class ConfigurationModule {

    // Additional app configuration goes here..
    @Provides
    fun provideNetworkConfiguration() = NetworkConfiguration(
        baseUrl = BuildConfig.BASE_URL,
        requestTimeoutInSeconds = 60,
        shouldEnableLogging = BuildConfig.DEBUG,
        cacheMaxAgeInSeconds = 60,
        apiToken = BuildConfig.API_TOKEN
    )
}

data class NetworkConfiguration(
    val baseUrl: String,
    val requestTimeoutInSeconds: Long,
    val shouldEnableLogging: Boolean,
    val cacheMaxAgeInSeconds: Long,
    val apiToken: String
)