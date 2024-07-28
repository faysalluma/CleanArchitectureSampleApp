package com.groupec.cleanarchitecturesampleapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.facebook.flipper.plugins.navigation.NavigationFlipperPlugin
import com.groupec.cleanarchitecturesampleapp.feature.detail.navigation.DETAIL_ROUTE
import com.groupec.cleanarchitecturesampleapp.feature.detail.navigation.detailScreen
import com.groupec.cleanarchitecturesampleapp.feature.home.navigation.HOME_ROUTE
import com.groupec.cleanarchitecturesampleapp.feature.home.navigation.homeScreen
import com.groupec.cleanarchitecturesampleapp.feature.home.navigation.navigateToDetail
import com.groupec.cleanarchitecturesampleapp.utils.FlipperNavigationLogger

@Composable
fun AppNavHost(
    modifier: Modifier,
    navController: NavHostController,
    startDestination: String = HOME_ROUTE,
) {
    LaunchedEffect (Unit) {
        val flipperPlugin = NavigationFlipperPlugin.getInstance()
        val flipperLogger = FlipperNavigationLogger(flipperPlugin)
        navController.addOnDestinationChangedListener(flipperLogger)
    }

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
        /*      enterTransition = { EnterNone },
              exitTransition = { ExitNone }*/
    ) {
        homeScreen(
            onOrderClick = { order ->
                navController.navigateToDetail(DETAIL_ROUTE, order)
            }
        )
        detailScreen(
            onBackClick = navController::popBackStack
        )
    }
}
