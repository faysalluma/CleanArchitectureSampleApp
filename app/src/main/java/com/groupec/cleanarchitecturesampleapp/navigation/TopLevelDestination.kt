
package com.groupec.cleanarchitecturesampleapp.navigation

import com.groupec.cleanarchitecturesampleapp.feature.home.R as homeR
import com.groupec.cleanarchitecturesampleapp.feature.detail.R as detailR

enum class TopLevelDestination(
    val titleTextId: Int,
    val iconTextId: Int,
) {
    HOME(
        titleTextId = homeR.string.feature_home_title,
        iconTextId = homeR.string.feature_home_title
    ),
    DETAIL(
        titleTextId = detailR.string.feature_detail_title,
        iconTextId = detailR.string.feature_detail_title
    )
}
