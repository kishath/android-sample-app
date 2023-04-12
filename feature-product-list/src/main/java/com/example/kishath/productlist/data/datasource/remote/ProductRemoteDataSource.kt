package com.example.kishath.productlist.data.datasource.remote

import com.example.kishath.productlist.data.datasource.ProductDataSource
import com.example.kishath.productlist.data.mapper.ProductMapper
import com.example.kishath.productlist.domain.model.Product

class ProductRemoteDataSource(
    private val productService: ProductService,
    private val dataMapper: ProductMapper = ProductMapper()
) : ProductDataSource {

    override suspend fun getAllProducts(): List<Product> {
        return productService.getProducts().map { dataMapper(it) }
    }
}