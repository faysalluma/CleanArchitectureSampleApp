package com.groupec.cleanarchitecturesampleapp.core.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.groupec.cleanarchitecturesampleapp.core.designsystem.R
import com.groupec.cleanarchitecturesampleapp.core.designsystem.theme.CleanArchitectureSampleAppTheme
import com.groupec.cleanarchitecturesampleapp.core.model.data.Order
import com.groupec.cleanarchitecturesampleapp.core.toDate

@Composable
fun OrderCardList(orders: List<Order>, onOrderClick: (Order) -> Unit) {
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
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onOrderClick(order) }) {
                    OrderCard(order)
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
        OrderCardList(
            orders = listOf(
                Order(1, "2022-10-02 10:00:03".toDate()!!, "Jean"),
                Order(2, "2022-10-02 10:00:03".toDate()!!, "Patrice"),
            ),
            onOrderClick = {}
        )
    }
}
