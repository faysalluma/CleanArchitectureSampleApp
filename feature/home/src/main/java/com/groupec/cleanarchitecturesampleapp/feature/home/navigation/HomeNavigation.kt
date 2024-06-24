package com.groupec.cleanarchitecturesampleapp.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.groupec.cleanarchitecturesampleapp.core.model.Repository

const val HOME_ROUTE = "home_route"

fun NavController.navigateToHome(navOptions: NavOptions? = null) = navigate(HOME_ROUTE, navOptions)

fun NavGraphBuilder.homeScreen(
    onRepositoryClick: (Repository) -> Unit,
) {
    composable(route = HOME_ROUTE) {
        HomeRoute(onRepositoryClick)
    }
}