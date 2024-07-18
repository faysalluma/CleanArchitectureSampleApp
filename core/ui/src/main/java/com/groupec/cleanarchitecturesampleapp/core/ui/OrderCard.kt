package com.groupec.cleanarchitecturesampleapp.core.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.groupec.cleanarchitecturesampleapp.core.designsystem.theme.CleanArchitectureSampleAppTheme
import com.groupec.cleanarchitecturesampleapp.core.model.data.Order
import com.groupec.cleanarchitecturesampleapp.core.toDate
import com.groupec.cleanarchitecturesampleapp.core.toDateString

@Composable
fun OrderCard(order: Order) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = order.datecreation.toDateString(),
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = order.customerName)
        }
    }
}

@Preview
// @Preview(device = Devices.TABLET)
@Composable
fun OrderCardPreview() {
    CleanArchitectureSampleAppTheme {
        OrderCard(order = Order(1, "2022-10-02 10:00:03".toDate()!!, "Jean"))
    }
}
