package com.example.kishath.productlist.data

import com.example.kishath.core.network.exceptions.CorruptEntityException
import com.example.kishath.productlist.data.mapper.ProductMapper
import com.example.kishath.productlist.data.model.ProductData
import org.junit.Assert.assertEquals
import org.junit.Test

class ProductMapperTests {

    @Test
    fun `can map ProductData model to product domain model`() {
        val productData = ProductData(
            id = "id",
            title = "title",
            price = 200.50,
            category = "category",
            description = "description",
            image = "image"
        )

        val subject = ProductMapper()
        val result = subject(productData)

        with(result) {
            assertEquals(productData.id, id)
            assertEquals(productData.title, title)
            assertEquals(productData.price?.toBigDecimal(), price)
            assertEquals(productData.category, category)
            assertEquals(productData.description, description)
            assertEquals(productData.image, imageUrl)
        }
    }

    @Test(expected = CorruptEntityException::class)
    fun `throws error when ProductData model is missing required fields`() {
        val productData = ProductData(
            id = "id",
            title = "title",
            price = null,
            category = null,
            description = null,
            image = null
        )

        val subject = ProductMapper()
        subject(productData)
    }
}