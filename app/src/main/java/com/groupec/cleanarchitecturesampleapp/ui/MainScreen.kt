package com.groupec.cleanarchitecturesampleapp.ui

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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.groupec.cleanarchitecturesampleapp.R
import com.groupec.cleanarchitecturesampleapp.core.designsystem.component.ErrorScreen
import com.groupec.cleanarchitecturesampleapp.core.model.data.Order
import com.groupec.cleanarchitecturesampleapp.navigation.AppNavHost
import com.groupec.cleanarchitecturesampleapp.navigation.NavigationItem


@Composable
fun MainScreen(
    connectionState: Boolean,
    navController: NavHostController = rememberNavController()
) {
    var appBarTitle = stringResource(id = R.string.app_name)
    var onNavigationClick: (() -> Unit)? = null
    var dropDownItemsMenu: List<Pair<String, () -> Unit>> = emptyList()

    val currentDestination = remember {
        mutableStateOf(navController.currentDestination?.route)
    }

    LaunchedEffect(navController) {
        navController.addOnDestinationChangedListener { _, destination, arguments ->
            currentDestination.value =
                destination.route?.substringBeforeLast("/")?.substringBeforeLast("?")
        }
    }

    when (currentDestination.value) {
        NavigationItem.Home.route -> {
            dropDownItemsMenu = getDropdownItemsWithActions(navController)
        }

        NavigationItem.Detail.route -> {
            // Get arguments and show it in TopBar
            val order = navController.previousBackStackEntry?.savedStateHandle?.get<Order>("order")
            appBarTitle = "Order ${order?.id}"

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

