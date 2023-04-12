package com.example.kishath.productlist.data

import com.example.kishath.productlist.data.datasource.remote.ProductRemoteDataSource
import com.example.kishath.productlist.data.datasource.remote.ProductService
import com.example.kishath.productlist.data.model.ProductData
import com.example.kishath.productlist.domain.model.Product
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class ProductRemoteDataSourceTests {

    @Test
    fun `can fetch products from api`() = runTest {
        val rawProducts = listOf(
            ProductData(
                id = "prod_1",
                title = "title",
                price = 10.0,
                category = "Category",
                description = "Description",
                image = "ImageUrl"
            )
        )
        val expected = Product(
            id = "prod_1",
            title = "title",
            price = 10.0.toBigDecimal(),
            category = "Category",
            description = "Description",
            imageUrl = "ImageUrl"
        )

        val service = mockk<ProductService> {
            coEvery { getProducts() } returns rawProducts
        }
        val subject = ProductRemoteDataSource(service)

        val products = subject.getAllProducts()

        assertEquals(products.size, rawProducts.size)
        with(rawProducts[0]) {
            assertEquals(expected.id, id)
            assertEquals(expected.title, title)
            assertEquals(expected.price, price?.toBigDecimal())
            assertEquals(expected.category, category)
            assertEquals(expected.description, description)
            assertEquals(expected.imageUrl, image)
        }
        coVerify(exactly = 1) {
            service.getProducts()
        }
    }

    @Test
    fun `throws exception on service failure`() = runTest {
        val errorMessage = "Network Exception"
        val service = mockk<ProductService> {
            coEvery { getProducts() } throws IOException(errorMessage)
        }
        val subject = ProductRemoteDataSource(service)

        val exception = Assert.assertThrows(IOException::class.java) {
            runBlocking {
                subject.getAllProducts()
            }
        }

        assertEquals(errorMessage, exception.message)
    }
}