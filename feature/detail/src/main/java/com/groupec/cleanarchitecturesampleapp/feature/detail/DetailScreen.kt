package com.groupec.cleanarchitecturesampleapp.feature.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.groupec.cleanarchitecturesampleapp.core.designsystem.component.BackButton
import com.groupec.cleanarchitecturesampleapp.core.designsystem.component.EmptyScreen
import com.groupec.cleanarchitecturesampleapp.core.designsystem.component.ErrorScreen
import com.groupec.cleanarchitecturesampleapp.core.designsystem.component.LoadingScreen
import com.groupec.cleanarchitecturesampleapp.core.model.data.Order
import com.groupec.cleanarchitecturesampleapp.core.model.data.Product
import com.groupec.cleanarchitecturesampleapp.core.ui.ComposableLifecycle
import com.groupec.cleanarchitecturesampleapp.core.ui.ProductCardList


@Composable
fun DetailScreen(
    order: Order?,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val productState by viewModel.productUiState.collectAsStateWithLifecycle()

    ComposableLifecycle(
        onCreate = {
            order?.let {
                viewModel.getProducts(it.id)
            }
        }
    )

    Box {
        when (productState) {
            is ProductUiState.Loading -> LoadingScreen()
            is ProductUiState.Empty -> EmptyScreen()
            is ProductUiState.Success -> ProductScreen((productState as ProductUiState.Success).products, onBackClick)
            is ProductUiState.Error -> ErrorScreen((productState as ProductUiState.Error).message)
        }
    }
}

@Composable
private fun ProductScreen(products: List<Product>, onBackClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        ProductCardList(products)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp),
            contentAlignment = Alignment.Center
        ) {
            BackButton(onClick = onBackClick)
        }
    }
}

