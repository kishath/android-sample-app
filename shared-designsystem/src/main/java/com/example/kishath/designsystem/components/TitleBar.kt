package com.example.kishath.designsystem.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver
import androidx.compose.ui.semantics.semantics

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TitleBar(title: String, backAction: (() -> Any?)? = null) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            backAction?.let {
                IconButton(onClick = { it.invoke() }, Modifier.testTag("TitleBarBack")) {
                    Icon(Icons.Filled.ArrowBack, null)
                }
            }
        },
        modifier = Modifier
            .semantics { isTopBar = true }
            .testTag("TitleBar"))
}

val IsTopBar = SemanticsPropertyKey<Boolean>("IsTopBar")
var SemanticsPropertyReceiver.isTopBar by IsTopBar