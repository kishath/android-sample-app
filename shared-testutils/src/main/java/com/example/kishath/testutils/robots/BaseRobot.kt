package com.example.kishath.testutils.robots

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.test.junit4.ComposeTestRule
import org.awaitility.Awaitility.await
import java.time.Duration
import java.util.concurrent.TimeUnit

abstract class BaseRobot(protected val composeTestRule: ComposeTestRule) {

    @RequiresApi(Build.VERSION_CODES.O)
    protected fun waitFor(assertion: () -> Unit) {
        await().pollDelay(Duration.ZERO).pollInterval(Duration.ofMillis(50))
            .atMost(10, TimeUnit.SECONDS).ignoreExceptionsMatching {
                it is AssertionError
            }.untilAsserted { assertion() }
    }
}