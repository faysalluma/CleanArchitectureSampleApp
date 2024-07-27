package com.groupec.cleanarchitecturesampleapp.core.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.groupec.cleanarchitecturesampleapp.core.designsystem.theme.CleanArchitectureSampleAppTheme
import com.groupec.cleanarchitecturesampleapp.core.model.data.Order
import com.groupec.cleanarchitecturesampleapp.core.toDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderCardListWithSearchBar(orders: List<Order>, onOrderClick: (Order) -> Unit) {
    var text by remember { mutableStateOf("") } // Query for SearchBar
    var active by remember { mutableStateOf(false) } // Active state for SearchBar
    val filteredItems = orders.filter { it.customerName.contains(text, ignoreCase = true) }

    Column (modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)){

        // Search Bar
        SearchBar(modifier = Modifier.fillMaxWidth(),
            // colors = SearchBarDefaults.colors(containerColor = Color.White),
            query = text,
            onQueryChange = {
                text = it
            },
            onSearch = {
                active = false
            },
            active = false, // spread out search bar list content
            onActiveChange = {
                active = it
            },
            placeholder = {
                Text(text = "Enter your query")
            },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search icon")
            },
            trailingIcon = {
                if (active && text.isNotEmpty()) {
                    Icon(
                        modifier = Modifier.clickable {
                            if (text.isNotEmpty()) {
                                text = ""
                            } else {
                                active = false
                            }
                        },
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close icon"
                    )
                }
            }
        )
        {

        }

        Spacer(modifier = Modifier.height(8.dp))

        // List
        LazyColumn {
            items(filteredItems) { item ->
                // Display your filtered items here
                Column (modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onOrderClick(item) }) {
                    OrderCard(order = item)
                }
            }
        }
    }
}

@Preview
@Preview(device = Devices.TABLET)
@Composable
fun OrderCardListPreview() {
    CleanArchitectureSampleAppTheme {
        OrderCardListWithSearchBar(
            orders = listOf(
                Order(1, "2022-10-02 10:00:03".toDate()!!, "Jean"),
                Order(2, "2022-10-02 10:00:03".toDate()!!, "Patrice"),
            ),
            onOrderClick = {}
        )
    }
}
