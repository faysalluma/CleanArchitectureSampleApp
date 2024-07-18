package com.groupec.cleanarchitecturesampleapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.groupec.cleanarchitecturesampleapp.feature.detail.navigation.DETAIL_ROUTE
import com.groupec.cleanarchitecturesampleapp.feature.detail.navigation.detailScreen
import com.groupec.cleanarchitecturesampleapp.feature.home.navigation.HOME_ROUTE
import com.groupec.cleanarchitecturesampleapp.feature.home.navigation.homeScreen
import com.groupec.cleanarchitecturesampleapp.feature.home.navigation.navigateToDetail

@Composable
fun AppNavHost(
    modifier: Modifier,
    navController: NavHostController,
    startDestination: String = HOME_ROUTE,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        homeScreen(
            onOrderClick = navController::navigateToDetail
        )
        detailScreen(
            onBackClick = navController::popBackStack
        )
    }
}

@Composable
private fun getCurrentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return when (navBackStackEntry?.destination?.route?.substringBeforeLast("/")?.substringBeforeLast("?")) {
        HOME_ROUTE -> HOME_ROUTE
        DETAIL_ROUTE -> DETAIL_ROUTE
        else -> null
    }
}