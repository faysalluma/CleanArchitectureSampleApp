package com.groupec.cleanarchitecturesampleapp.core.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.layout.PaneAdaptedValue
import androidx.compose.material3.adaptive.navigation.BackNavigationBehavior
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.groupec.cleanarchitecturesampleapp.core.model.data.Order
import com.groupec.cleanarchitecturesampleapp.core.toDate
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun SampleListDetailPaneScaffold() {

    val orders = listOf(
        Order(1, "2022-10-02 10:00:03".toDate()!!, "Jean"),
        Order(2, "2022-10-03 10:00:03".toDate()!!, "Patrice"),
        Order(3, "2022-10-04 10:00:03".toDate()!!, "Lola"),
    )

    val scaffoldNavigator = rememberListDetailPaneScaffoldNavigator<Order>()
    val scope = rememberCoroutineScope()
    val backNavigationBehavior = BackNavigationBehavior.PopUntilScaffoldValueChange

    NavigableListDetailPaneScaffold(
        navigator = scaffoldNavigator,
        listPane = {
            AnimatedPane {
                OrderCardListWithSearchBar(
                    orders,
                    onOrderClick = { item ->
                        // Navigate to the detail pane with the passed item
                        scope.launch {
                            scaffoldNavigator.navigateTo(
                                ListDetailPaneScaffoldRole.Detail,
                                item
                            )
                        }
                    }
                )
            }
        },
        detailPane = {
            AnimatedPane {
                // Show the detail pane content if selected item is available
                scaffoldNavigator.currentDestination?.contentKey?.let {
                    Column {
                        // Allow users to dismiss the detail pane. Use back navigation to
                        // hide an expanded detail pane.
                        if (scaffoldNavigator.scaffoldValue[ListDetailPaneScaffoldRole.Detail] == PaneAdaptedValue.Expanded) {
                            // Material design principles promote the usage of a right-aligned
                            // close (X) button.
                            IconButton(
                                modifier =  Modifier.align(Alignment.End).padding(16.dp),
                                onClick = {
                                    scope.launch {
                                        scaffoldNavigator.navigateBack(backNavigationBehavior)  // This method only hide detail
                                    }
                                }
                            ) {
                                Icon(Icons.Default.Close, contentDescription = "Close")
                            }
                        }

                        Column(
                            modifier = Modifier.fillMaxWidth().padding(24.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Text(it.id.toString())
                            Text(it.customerName)
                            Text(it.datecreation.toString())
                        }
                    }
                }
            }
        },
    )
}

@Preview
@Composable
fun SampleListDetailPaneScaffoldPreview() {
    SampleListDetailPaneScaffold()
}