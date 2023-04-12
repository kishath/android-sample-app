package com.example.kishath.designsystem.components

import androidx.compose.ui.test.junit4.createComposeRule
import com.example.kishath.testutils.robots.ErrorItemRobot
import io.mockk.mockk
import io.mockk.verify
import org.junit.Rule
import org.junit.Test

class ErrorItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val errorItemRobot = ErrorItemRobot(composeTestRule)

    @Test
    fun errorLabelIsDisplayed() {
        composeTestRule.setContent {
            ErrorItem()
        }

        errorItemRobot.verifyErrorLabelIsDisplayed()
    }

    @Test
    fun retryButtonIsDisplayed() {
        composeTestRule.setContent {
            ErrorItem()
        }

        errorItemRobot.verifyRetryButtonIsDisplayed()
    }

    @Test
    fun retryButtonIsClickable() {
        val callback: () -> Unit = mockk(relaxed = true)

        composeTestRule.setContent {
            ErrorItem(onRetryClicked = callback)
        }

        errorItemRobot.clickRetryButton()
        verify { callback() }
    }
}
