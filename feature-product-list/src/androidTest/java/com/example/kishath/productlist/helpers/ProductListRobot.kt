package com.example.kishath.productlist.helpers

import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.example.kishath.productlist.domain.model.Product
import com.example.kishath.productlist.ui.ProductItemSemanticsMatcher
import com.example.kishath.testutils.robots.BaseRobot

class ProductListRobot(composeTestRule: ComposeTestRule) : BaseRobot(composeTestRule) {

    fun verifyListItemsCount(count: Int) = waitFor {
        composeTestRule.onNodeWithTag("ProductList")
            .onChildren()
            .assertCountEquals(count)
    }

    fun verifyItems(listOfProducts: List<Product>) = waitFor {
        composeTestRule.onNodeWithTag("ProductList").onChildren().apply {
            listOfProducts.forEachIndexed { index, product ->
                val matcher = SemanticsMatcher.expectValue(
                    ProductItemSemanticsMatcher, product.title
                )
                get(index).assert(matcher)
            }
        }
    }

    fun clickFirstProduct() {
        composeTestRule.onNodeWithTag("ProductList").onChildren().onFirst().performClick()
    }

    fun verifyListIsNotDisplayed() {
        composeTestRule.onNodeWithTag("ProductList").assertDoesNotExist()
    }
}