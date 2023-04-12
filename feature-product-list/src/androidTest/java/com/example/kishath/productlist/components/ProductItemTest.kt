package com.example.kishath.productlist.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.kishath.productlist.domain.model.Product
import com.example.kishath.productlist.ui.ProductItem
import io.mockk.mockk
import io.mockk.verify
import org.junit.Rule
import org.junit.Test

class ProductItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val product = Product(
        id = "prod_1",
        title = "title",
        price = 10.0.toBigDecimal(),
        category = "Category",
        description = "Description",
        imageUrl = "ImageUrl"
    )

    @Test
    fun titleIsDisplayed() {
        composeTestRule.setContent {
            ProductItem(product = product, onClicked = {})
        }

        composeTestRule.onNodeWithText("title").assertIsDisplayed()
    }

    @Test
    fun priceIsDisplayed() {
        composeTestRule.setContent {
            ProductItem(product = product, onClicked = {})
        }

        composeTestRule.onNodeWithText("$10").assertIsDisplayed()
    }

    @Test
    fun countryRowIsClickable() {
        val callback: (Product) -> Unit = mockk(relaxed = true)

        composeTestRule.setContent {
            ProductItem(product = product, onClicked = callback)
        }

        composeTestRule.onNodeWithTag("ProductItem").performClick()
        verify { callback(product) }
    }
}