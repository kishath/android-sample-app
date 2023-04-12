package com.example.kishath.productlist.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.example.kishath.core.domain.UseCaseResult
import com.example.kishath.productlist.domain.model.Product
import com.example.kishath.productlist.domain.usecase.GetAllProductsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class ProductViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `can emit loading`() = runTest {
        val mockGetAllProductsUseCase = mockk<GetAllProductsUseCase>()
        coEvery { mockGetAllProductsUseCase(Unit) } returns flowOf(
            UseCaseResult.Loading
        )

        val subject = ProductViewModel(mockGetAllProductsUseCase)
        subject.loadProducts()

        subject.uiState.test {
            assertEquals(ProductViewModel.UiState.Loading, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `can return a list of products on success`() = runTest {
        val productA = mockk<Product>()
        val productB = mockk<Product>()
        val mockGetAllProductsUseCase = mockk<GetAllProductsUseCase>()
        coEvery { mockGetAllProductsUseCase(Unit) } returns flowOf(
            UseCaseResult.Success(
                listOf(productA, productB)
            )
        )

        val subject = ProductViewModel(mockGetAllProductsUseCase)
        subject.loadProducts()

        subject.uiState.test {
            assertEquals(ProductViewModel.UiState.Loading, awaitItem())
            assertEquals(ProductViewModel.UiState.Success(listOf(productA, productB)), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `can emit error state on failure after loading`() = runTest {
        val exception = IOException("Network error")
        val mockGetAllProductsUseCase = mockk<GetAllProductsUseCase>()
        coEvery { mockGetAllProductsUseCase(Unit) } returns flowOf(
            UseCaseResult.Error(exception)
        )

        val subject = ProductViewModel(mockGetAllProductsUseCase)
        subject.loadProducts()

        subject.uiState.test {
            assertEquals(ProductViewModel.UiState.Loading, awaitItem())
            assertEquals(ProductViewModel.UiState.Error(exception), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }
}