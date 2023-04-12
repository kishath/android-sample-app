package com.kishath.examples.template.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.kishath.core.navigation.Navigator
import com.example.kishath.designsystem.theme.AndroidTemplateTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RootActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidTemplateTheme {
                NavHostContainer()
            }
        }
    }

    @Composable
    private fun NavHostContainer() {
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = "products") {
            navigator.buildNavTree(this)
        }
    }
}