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
import com.groupec.cleanarchitecturesampleapp.core.model.data.Product
import com.groupec.cleanarchitecturesampleapp.core.toDate
import com.groupec.cleanarchitecturesampleapp.core.toDateString

@Composable
fun ProductCard(product: Product) {
    ListItem(
        /* colors = ListItemDefaults.colors(
            containerColor = Silver
        ),*/
        headlineContent = { Text(text = product.libelle, fontWeight = FontWeight.Bold) },
        supportingContent = {
            Text(product.desc)
        }
    )
    Divider()
}

@Preview
// @Preview(device = Devices.TABLET)
@Composable
fun ProductCardPreview() {
    CleanArchitectureSampleAppTheme {
        ProductCard(product = Product(1, "Jean", "Lorem Ipsun"))
    }
}
