package com.groupec.cleanarchitecturesampleapp.feature.detail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.groupec.cleanarchitecturesampleapp.core.model.data.Order


@Composable
internal fun DetailRoute(
    order: Order ? = null,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    // viewModel: HomeViewModel = hiltViewModel(),
) {
    /*val orderState by viewModel.orderUiState.collectAsStateWithLifecycle()
    HomeScreen(
        orderState = orderState,
        onOrderClick = onOrderClick,
        getOrders = viewModel::getOrders,
        modifier = modifier
    )*/
    order?.let {
        Text(text = it.customerName)
    }

}

