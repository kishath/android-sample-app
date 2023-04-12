package com.example.kishath.productlist.domain

import com.example.kishath.productlist.domain.model.Product

interface ProductRepository {
    suspend fun getAllProducts(): List<Product>
}