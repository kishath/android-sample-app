package com.example.kishath.productlist.components

import androidx.compose.ui.test.junit4.createComposeRule
import com.example.kishath.productlist.domain.model.Product
import com.example.kishath.productlist.helpers.ProductListRobot
import com.example.kishath.productlist.ui.ProductList
import io.mockk.mockk
import io.mockk.verify
import org.junit.Rule
import org.junit.Test

class ProductListTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val productListRobot = ProductListRobot(composeTestRule)

    private val product = Product(
        id = "prod_1",
        title = "title",
        price = 10.0.toBigDecimal(),
        category = "Category",
        description = "Description",
        imageUrl = "ImageUrl"
    )

    private val listOfProducts = listOf(
        product,
        product.copy(title = "title2")
    )

    @Test
    fun checkIfProductListIsEmpty() {
        composeTestRule.setContent {
            ProductList(listOf()) {}
        }

        productListRobot.verifyListItemsCount(0)
    }

    @Test
    fun checkIfProductListHasElements() {
        composeTestRule.setContent {
            ProductList(
                products = listOfProducts
            ) {}
        }

        productListRobot.verifyListItemsCount(2)
    }

    @Test
    fun checkCountryListHasCorrectProductItems() {
        composeTestRule.setContent {
            ProductList(products = listOfProducts, onProductClicked = {})
        }

        productListRobot.verifyItems(listOfProducts)
    }

    @Test
    fun productIsClickable() {
        val callback: (Product) -> Unit = mockk(relaxed = true)

        composeTestRule.setContent {
            ProductList(products = listOfProducts, onProductClicked = callback)
        }

        productListRobot.clickFirstProduct()
        verify { callback(listOfProducts[0]) }
    }
}