package com.groupec.cleanarchitecturesampleapp.feature.home

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.groupec.cleanarchitecturesampleapp.core.designsystem.component.EmptyScreen
import com.groupec.cleanarchitecturesampleapp.core.designsystem.component.ErrorScreen
import com.groupec.cleanarchitecturesampleapp.core.designsystem.component.LoadingScreen
import com.groupec.cleanarchitecturesampleapp.core.model.data.Order
import com.groupec.cleanarchitecturesampleapp.core.ui.ComposableLifecycle
import com.groupec.cleanarchitecturesampleapp.core.ui.OrderCardListWithSearchBar

@Composable
fun HomeScreen(
    onOrderClick: (Order) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val orderState by viewModel.orderUiState.collectAsStateWithLifecycle()

    ComposableLifecycle(
        // onResume = { getOrders() }
    )

    Box {
        when (orderState) {
            is OrderUiState.Loading -> LoadingScreen()
            is OrderUiState.Empty -> EmptyScreen()
            is OrderUiState.Success -> OrderCardListWithSearchBar((orderState as OrderUiState.Success).orders, onOrderClick)
            is OrderUiState.Error -> ErrorScreen((orderState as OrderUiState.Error).message)
        }
    }
}