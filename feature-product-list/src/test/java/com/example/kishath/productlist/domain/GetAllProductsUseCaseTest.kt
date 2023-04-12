package com.example.kishath.productlist.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.example.kishath.core.domain.UseCaseResult
import com.example.kishath.core.network.exceptions.BadConnectionException
import com.example.kishath.productlist.domain.model.Product
import com.example.kishath.productlist.domain.usecase.GetAllProductsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import java.net.UnknownHostException

@OptIn(ExperimentalCoroutinesApi::class)
class GetAllProductsUseCaseTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `can load a list of products`() = runTest {
        val expected = listOf(
            Product(
                id = "prod_1",
                title = "title",
                price = 10.0.toBigDecimal(),
                category = "Category",
                description = "Description",
                imageUrl = "ImageUrl"
            ),
            Product(
                id = "prod_2",
                title = "title",
                price = 20.0.toBigDecimal(),
                category = "Category",
                description = "Description",
                imageUrl = "ImageUrl"
            )
        )

        val mockProductRepository = mockk<ProductRepository>()
        coEvery { mockProductRepository.getAllProducts() } returns expected

        val subject = GetAllProductsUseCase(mockProductRepository)
        subject(Unit).test {
            assertEquals(UseCaseResult.Loading, awaitItem())
            assertEquals(UseCaseResult.Success(expected), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `can propagate exception`() = runTest {
        val exception = UnknownHostException("Network error")
        val mockProductRepository = mockk<ProductRepository>()
        coEvery { mockProductRepository.getAllProducts() } throws exception

        val subject = GetAllProductsUseCase(mockProductRepository)
        subject(Unit).test {
            assertEquals(UseCaseResult.Loading, awaitItem())
            assertThat(
                (awaitItem() as UseCaseResult.Error).exception,
                instanceOf(BadConnectionException::class.java)
            )
            cancelAndIgnoreRemainingEvents()
        }
    }
}