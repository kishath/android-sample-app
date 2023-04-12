package com.example.kishath.designsystem.components

import androidx.compose.ui.test.junit4.createComposeRule
import com.example.kishath.testutils.robots.LoadingItemRobot
import org.junit.Rule
import org.junit.Test

class LoadingItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val loadingItemRobot = LoadingItemRobot(composeTestRule)

    @Test
    fun loadingLabelIsDisplayed() {
        composeTestRule.setContent {
            LoadingItem()
        }

        loadingItemRobot.verifyLoadingLabelIsDisplayed()
    }

    @Test
    fun loadingSpinnerIsDisplayed() {
        composeTestRule.setContent {
            LoadingItem()
        }

        loadingItemRobot.verifyLoadingSpinnerIsDisplayed()
    }
}
