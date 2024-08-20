package com.groupec.cleanarchitecturesampleapp.utils

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import com.facebook.flipper.plugins.navigation.NavigationFlipperPlugin
import com.groupec.cleanarchitecturesampleapp.navigation.NavigationItem

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
        NavigationItem.Home.route -> featurePackage.plus(".HomeScreen")
        NavigationItem.Detail.route -> featurePackage.plus(".DetailScreen")
        else -> null
    }
}

const val featurePackage = "com.groupec.cleanarchitecturesampleapp.feature"