package com.groupec.cleanarchitecturesampleapp.utils

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import com.facebook.flipper.plugins.navigation.NavigationFlipperPlugin
import com.groupec.cleanarchitecturesampleapp.feature.detail.navigation.DETAIL_ROUTE
import com.groupec.cleanarchitecturesampleapp.feature.home.navigation.HOME_ROUTE

class FlipperNavigationLogger(private val flipperPlugin: NavigationFlipperPlugin) : NavController.OnDestinationChangedListener {
    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        val route = destination.route
        // Log the navigation event to Flipper
        flipperPlugin.sendNavigationEvent(route, controller.currentDestinationClassName(), null)
    }
}

fun NavController.currentDestinationClassName(): String? {
    val route = currentBackStackEntry?.destination?.route
    return when (route?.substringBeforeLast("/")?.substringBeforeLast("?")) {
        HOME_ROUTE -> featurePackage.plus(".HomeScreen")
        DETAIL_ROUTE -> featurePackage.plus(".DetailScreen")
        else -> null
    }
}

const val featurePackage = "com.groupec.cleanarchitecturesampleapp.feature"