package com.groupec.cleanarchitecturesampleapp.navigation

import androidx.compose.animation.ExitTransition.Companion.None as ExitNone
import androidx.compose.animation.EnterTransition.Companion.None as EnterNone
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
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