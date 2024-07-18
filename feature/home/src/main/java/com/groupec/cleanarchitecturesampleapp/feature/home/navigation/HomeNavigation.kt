package com.groupec.cleanarchitecturesampleapp.feature.home.navigation

import android.net.Uri
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.google.gson.Gson
import com.groupec.cleanarchitecturesampleapp.core.model.data.Order
import com.groupec.cleanarchitecturesampleapp.feature.home.HomeRoute


const val HOME_ROUTE = "home_route"

fun NavController.navigateToDetail(route: String, order: Order, navOptions: NavOptions? = null) {
    val jsonTransaction = Gson().toJson(order)
    navigate(route.plus("/${jsonTransaction}"), navOptions)
}

/*
// For optionnal parameters

fun NavController.navigateToInterests(topicId: String? = null, navOptions: NavOptions? = null) {
     val jsonTransaction = Uri.encode(Gson().toJson(transaction))
     navController.navigate(NavigationScreen.Result.route.plus("?transaction=${jsonTransaction}"))

    val route = if (topicId != null) {
        "${INTERESTS_ROUTE_BASE}?${TOPIC_ID_ARG}=$topicId"
    } else {
        INTERESTS_ROUTE_BASE
    }
    navigate(route, navOptions)
}
*/

fun NavGraphBuilder.homeScreen(
    onOrderClick: (Order) -> Unit
) {
    composable(route = HOME_ROUTE) {
        HomeRoute(onOrderClick)
    }
}