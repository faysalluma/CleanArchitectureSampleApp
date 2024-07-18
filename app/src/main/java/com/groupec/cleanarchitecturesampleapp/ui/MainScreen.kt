package com.groupec.cleanarchitecturesampleapp.ui

import android.os.Bundle
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavArgument
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import com.groupec.cleanarchitecturesampleapp.R
import com.groupec.cleanarchitecturesampleapp.core.designsystem.SampleTopAppBar
import com.groupec.cleanarchitecturesampleapp.core.designsystem.component.ErrorScreen
import com.groupec.cleanarchitecturesampleapp.navigation.AppNavHost
import com.groupec.cleanarchitecturesampleapp.core.model.data.Order
import com.groupec.cleanarchitecturesampleapp.feature.detail.navigation.ARG_ORDER
import com.groupec.cleanarchitecturesampleapp.feature.detail.navigation.DETAIL_ROUTE
import com.groupec.cleanarchitecturesampleapp.feature.home.navigation.HOME_ROUTE


@Composable
fun MainScreen(connectionState: Boolean /*, navController: NavHostController = rememberNavController()*/) {
    var appBarTitle = stringResource(id = R.string.app_name)
    var onNavigationClick : (() -> Unit) ? = null
    var dropDownItemsMenu: List<Pair<String, () -> Unit>>  = emptyList()

    val navController = rememberNavController()
    val currentDestination = remember {
        mutableStateOf(
            Destination(navController.currentDestination?.route,
            null)
        )
    }

    LaunchedEffect(navController) {
        navController.addOnDestinationChangedListener { _, destination, arguments ->
            currentDestination.value = Destination(
                destination.route?.substringBeforeLast("/")?.substringBeforeLast("?"),
                arguments
            )

        }
    }

    when (currentDestination.value.route) {
        HOME_ROUTE -> {
            dropDownItemsMenu = getDropdownItemsWithActions(navController)
        }
        DETAIL_ROUTE -> {
            // Get arguments and show it in TopBar
            val orderJsonString =  currentDestination.value.arguments?.getString(ARG_ORDER)
            val order = Gson().fromJson(orderJsonString, Order::class.java)
            appBarTitle ="Order ${order.id}"

            // Definie onNavigationClick method
            onNavigationClick = {
                navController.popBackStack()
            }
        }
        else -> {
            // Handle unexpected destinations (optional)
        }
    }

    Scaffold(
        topBar = {
            SampleTopAppBar(
                appBarTitle,
                onNavigationClick,
                dropDownItemsMenu
            )
        }
    ) {
        if (!connectionState) {
            ErrorScreen(error = "No internet connexion")
        } else {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                AppNavHost(navController = navController, modifier = Modifier.padding(it))
            }
        }
    }
}

fun getDropdownItemsWithActions(navController: NavHostController): List<Pair<String, () -> Unit>> {
    return listOf(
        DropdownItem.Settings.name to { /* navController.executeAction() */ },
    )
}

data class Destination(val route: String?, val arguments: Bundle?)