package com.groupec.cleanarchitecturesampleapp.core.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.groupec.cleanarchitecturesampleapp.core.model.data.Product


@Composable
fun ProductCardList(products: List<Product>) {
    LazyColumn {
        items(products) { product ->
            Column(modifier = Modifier.fillMaxWidth()) {
                ProductCard(product = product)
            }
        }
    }
}