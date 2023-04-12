package com.example.kishath.productlist.di

import com.example.kishath.productlist.data.ProductRepositoryImpl
import com.example.kishath.productlist.data.datasource.ProductDataSource
import com.example.kishath.productlist.data.datasource.remote.ProductRemoteDataSource
import com.example.kishath.productlist.data.datasource.remote.ProductService
import com.example.kishath.productlist.domain.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun provideProductRepository(productDataSource: ProductDataSource):
            ProductRepository = ProductRepositoryImpl(productDataSource)

    @Provides
    @Singleton
    fun provideProductDataSource(productService: ProductService):
            ProductDataSource = ProductRemoteDataSource(productService)
}