package com.example.kishath.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

class Navigator {

    private val navTargetsList: MutableList<NavTarget> = mutableListOf()

    fun addNavTarget(navTarget: NavTarget) {
        navTargetsList.add(navTarget)
    }

    fun buildNavTree(navGraphBuilder: NavGraphBuilder): NavGraphBuilder {
        navTargetsList.forEach { target ->
            navGraphBuilder.composable(route = target.path) {
                target.screen()
            }
        }

        return navGraphBuilder
    }
}

data class NavTarget(
    val path: String,
    val screen: @Composable () -> Unit
)