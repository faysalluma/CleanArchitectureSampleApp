package com.groupec.cleanarchitecturesampleapp.feature.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.groupec.cleanarchitecturesampleapp.core.model.data.Order
import com.groupec.cleanarchitecturesampleapp.core.ui.ComposableLifecycle
import com.groupec.cleanarchitecturesampleapp.core.designsystem.R
import com.groupec.cleanarchitecturesampleapp.core.toDateString


@Composable
internal fun HomeRoute(
    onOrderClick: (Order) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val orderState by viewModel.orderUiState.collectAsStateWithLifecycle()
    HomeScreen(
        orderState = orderState,
        onOrderClick = onOrderClick,
        getOrders = viewModel::getOrders,
        modifier = modifier
    )
}


@Composable
internal fun HomeScreen(
    orderState: OrderUiState,
    onOrderClick: (Order) -> Unit,
    getOrders: () -> Unit,
    modifier: Modifier = Modifier

) {
    ComposableLifecycle(
        onResume = { getOrders() }
    )

    Box {
        when (orderState) {
            is OrderUiState.Loading -> LoadingScreen()
            is OrderUiState.Empty -> EmptyScreen()
            is OrderUiState.Success -> OrderList(orderState.orders)
            is OrderUiState.Error -> ErrorScreen(orderState.message)
        }
    }
}


@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun EmptyScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "No data available")
    }
}

@Composable
fun ErrorScreen(error: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Error: $error")
    }
}

@Composable
fun OrderList(orders: List<Order>) {
    Column {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://find.groupec.net/images/groupec-logo-large.png")
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
            // .border(2.dp, Color.Gray, CircleShape),
            // colorFilter = ColorFilter.tint(Color.Blue)
        )

        LazyColumn {
            items(orders) { order ->
                RouteItem(order)
            }
        }
    }
}

@Composable
fun RouteItem(order: Order) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = order.datecreation.toDateString(), style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = order.customerName)
        }
    }
}
