package com.example.kishath.productlist

import androidx.compose.ui.test.hasParent
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import com.example.kishath.productlist.domain.model.Product
import com.example.kishath.productlist.helpers.ProductListRobot
import com.example.kishath.productlist.ui.ProductViewModel
import com.example.kishath.productlist.ui.ProductsScreen
import com.example.kishath.testutils.robots.ErrorItemRobot
import com.example.kishath.testutils.robots.LoadingItemRobot
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Rule
import org.junit.Test

class ProductsScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val loadingItemRobot = LoadingItemRobot(composeTestRule)
    private val errorItemRobot = ErrorItemRobot(composeTestRule)
    private val productListRobot = ProductListRobot(composeTestRule)

    @Test
    fun verifyTitleIsDisplayed() {
        composeTestRule.setContent {
            ProductsScreen(
                viewModel = mockk(relaxUnitFun = true) {
                    every { uiState } returns MutableStateFlow(ProductViewModel.UiState.Loading)
                }
            )
        }

        composeTestRule.onNode(hasParent(hasTestTag("TitleBar")) and hasText("Products"))
            .assertExists()
    }

    @Test
    fun checkIfLoadingStateIsDisplayed() {
        val viewModel = mockk<ProductViewModel>(relaxUnitFun = true) {
            every { uiState } returns MutableStateFlow(ProductViewModel.UiState.Loading)
        }
        composeTestRule.setContent {
            ProductsScreen(
                viewModel = viewModel,
            )
        }
        loadingItemRobot.verifyLoadingItemIsDisplayed()
    }

    @Test
    fun checkIfErrorStateIsDisplayed() {
        val viewModel = mockk<ProductViewModel>(relaxUnitFun = true) {
            every { uiState } returns MutableStateFlow(
                ProductViewModel.UiState.Error(
                    Exception()
                )
            )
        }
        composeTestRule.setContent {
            ProductsScreen(
                viewModel = viewModel,
            )
        }
        errorItemRobot.verifyErrorItemIsDisplayed()
    }

    @Test
    fun checkIfDataIsDisplayed() {
        val products = listOf<Product>(
            mockk(relaxed = true),
            mockk(relaxed = true),
            mockk(relaxed = true)
        )
        val viewModel = mockk<ProductViewModel>(relaxUnitFun = true) {
            every { uiState } returns MutableStateFlow(
                ProductViewModel.UiState.Success(
                    products
                )
            )
        }

        composeTestRule.setContent {
            ProductsScreen(
                viewModel = viewModel,
            )
        }

        productListRobot.verifyItems(products)
    }
}