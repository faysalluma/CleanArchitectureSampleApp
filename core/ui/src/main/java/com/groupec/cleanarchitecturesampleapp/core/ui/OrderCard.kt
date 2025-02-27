package com.groupec.cleanarchitecturesampleapp.core.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.groupec.cleanarchitecturesampleapp.core.designsystem.theme.CleanArchitectureSampleAppTheme
import com.groupec.cleanarchitecturesampleapp.core.model.data.Order
import com.groupec.cleanarchitecturesampleapp.core.toDate
import com.groupec.cleanarchitecturesampleapp.core.toDateString

@Composable
fun OrderCard(order: Order) {
    ListItem(
        headlineContent = { Text(order.datecreation.toDateString(), fontWeight = FontWeight.Bold) },
        supportingContent = {
            Text(order.customerName)
        },
        leadingContent = {
            Text(text = stringResource(id = R.string.title_number, order.id))
        },
        trailingContent = {
            Icon(
                Icons.Filled.Star,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        }
    )
    Divider()
}

@Preview
// @Preview(device = Devices.TABLET)
@Composable
fun OrderCardPreview() {
    CleanArchitectureSampleAppTheme {
        OrderCard(order = Order(1, "2022-10-02 10:00:03".toDate()!!, "Jean"))
    }
}
