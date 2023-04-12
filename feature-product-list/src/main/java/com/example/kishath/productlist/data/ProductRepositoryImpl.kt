package com.example.kishath.productlist.data

import com.example.kishath.productlist.data.datasource.ProductDataSource
import com.example.kishath.productlist.domain.ProductRepository
import com.example.kishath.productlist.domain.model.Product

class ProductRepositoryImpl(private val productDataSource: ProductDataSource) : ProductRepository {

    override suspend fun getAllProducts(): List<Product> {
        return productDataSource.getAllProducts()
    }
}