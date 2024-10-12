package com.groupec.cleanarchitecturesampleapp.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.groupec.cleanarchitecturesampleapp.core.model.data.Order
import com.groupec.cleanarchitecturesampleapp.feature.detail.DetailScreen
import com.groupec.cleanarchitecturesampleapp.feature.home.HomeScreen

@Composable
fun AppNavHost(
    modifier: Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Home.route,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
        enterTransition = {
            EnterTransition.None
        },
        exitTransition = {
            ExitTransition.None
        }
    ) {
        composable(NavigationItem.Home.route) {
            HomeScreen(
                onOrderClick = { order ->
                    navController.currentBackStackEntry?.savedStateHandle?.set("order", order)
                    navController.navigate(NavigationItem.Detail.route)
                }
            )
        }

        composable(NavigationItem.Detail.route) {
            val order = navController.previousBackStackEntry?.savedStateHandle?.get<Order>("order")
            DetailScreen(order, onBackClick = { navController.popBackStack() })
        }
    }
}