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
const val DETAIL_ROUTE = "detail_route/{$ARG_ORDER}"

fun NavGraphBuilder.detailScreen(
    onBackClick: () -> Unit
) {
    composable(
        route = DETAIL_ROUTE,
        arguments = mutableListOf(
            navArgument(ARG_ORDER) {
                type = Order.NavigationType
                nullable = false  // Use true for nullables parameters
            }
        ),
    ) { backStack ->
        val order = backStack.arguments?.getParcelable<Order>(ARG_ORDER)
        DetailRoute(order, onBackClick)
    }
}