package com.example.kishath.productlist.data.datasource.remote

import com.example.kishath.productlist.data.model.ProductData
import retrofit2.http.GET

interface ProductService {

    @GET("products")
    suspend fun getProducts(): List<ProductData>
}