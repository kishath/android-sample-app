package com.example.kishath.productlist.data.datasource

import com.example.kishath.productlist.domain.model.Product

interface ProductDataSource {
    suspend fun getAllProducts(): List<Product>
}