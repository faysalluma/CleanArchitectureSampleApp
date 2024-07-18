package com.groupec.cleanarchitecturesampleapp.feature.detail.navigation

import android.net.Uri
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.google.gson.Gson
import com.groupec.cleanarchitecturesampleapp.core.model.data.Order
import com.groupec.cleanarchitecturesampleapp.feature.detail.DetailRoute


// ?result={result}&transaction={transaction}
const val ARG_ORDER = "order"
const val DETAIL_ROUTE = "detail_route"
const val DETAIL_ROUTE_ARGS = "$DETAIL_ROUTE/{$ARG_ORDER}"

fun NavGraphBuilder.detailScreen(
    onBackClick: () -> Unit
) {
    composable(
        route = DETAIL_ROUTE_ARGS,
        arguments = mutableListOf(
            navArgument(ARG_ORDER) {
                type = NavType.StringType
                nullable = false  // Use true for nullables parameters
                // defaultValue = ""
            }
        ),
    ) { backStack ->
        val orderJsonString =  backStack.arguments?.getString(ARG_ORDER)
        val order = Gson().fromJson(orderJsonString, Order::class.java)
        DetailRoute(order, onBackClick)
    }
}