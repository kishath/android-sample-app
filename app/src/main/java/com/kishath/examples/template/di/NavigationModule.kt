package com.kishath.examples.template.di

import com.example.kishath.core.navigation.NavTarget
import com.example.kishath.core.navigation.Navigator
import com.example.kishath.productlist.ui.ProductsScreen
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NavigationModule {

    @Provides
    @Singleton
    fun provideNavigator(): Navigator {
        val navigator = Navigator()
        navigator.addNavTarget(
            NavTarget(
                path = "products",
                screen = { ProductsScreen() }
            )
        )
        return navigator
    }
}