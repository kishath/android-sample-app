package com.example.kishath.testutils.robots

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText

class LoadingItemRobot(composeTestRule: ComposeTestRule) : BaseRobot(composeTestRule) {

    fun verifyLoadingLabelIsDisplayed() = waitFor {
        composeTestRule.onNodeWithText("Loading").assertIsDisplayed()
    }

    fun verifyLoadingSpinnerIsDisplayed() = waitFor {
        composeTestRule
            .onNodeWithTag(testTag = "loadingIndicator")
            .assertIsDisplayed()
    }

    fun verifyLoadingItemIsDisplayed() = waitFor {
        verifyLoadingLabelIsDisplayed()
        verifyLoadingSpinnerIsDisplayed()
    }

    fun verifyLoadingSpinnerIsNotDisplayed() {
        composeTestRule
            .onNodeWithTag(testTag = "loadingIndicator")
            .assertDoesNotExist()
    }

    fun verifyLoadingLabelIsNotDisplayed() {
        composeTestRule.onNodeWithText("Loading").assertDoesNotExist()
    }
}
