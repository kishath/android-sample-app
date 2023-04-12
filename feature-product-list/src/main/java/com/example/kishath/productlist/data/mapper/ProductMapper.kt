package com.example.kishath.productlist.data.mapper

import com.example.kishath.core.mapper.DataMapper
import com.example.kishath.productlist.data.model.ProductData
import com.example.kishath.productlist.domain.model.Product

class ProductMapper : DataMapper() {
    internal operator fun invoke(e: ProductData): Product {
        return Product(
            id = e.id ?: fail(e),
            title = e.title ?: fail(e),
            price = e.price?.toBigDecimal() ?: fail(e),
            category = e.category ?: fail(e),
            description = e.description ?: fail(e),
            imageUrl = e.image ?: ""
        )
    }
}