package com.example.kishath.testutils.robots

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick

class ErrorItemRobot(composeTestRule: ComposeTestRule) : BaseRobot(composeTestRule) {

    fun verifyErrorLabelIsDisplayed() = waitFor {
        composeTestRule.onNodeWithText("There was an error").assertIsDisplayed()
    }

    fun verifyRetryButtonIsDisplayed() = waitFor {
        composeTestRule.onNodeWithText("Try again").assertIsDisplayed()
    }

    fun clickRetryButton() {
        composeTestRule.onNodeWithText("Try again").performClick()
    }

    fun verifyErrorItemIsDisplayed() = waitFor {
        verifyErrorLabelIsDisplayed()
        verifyRetryButtonIsDisplayed()
    }
}