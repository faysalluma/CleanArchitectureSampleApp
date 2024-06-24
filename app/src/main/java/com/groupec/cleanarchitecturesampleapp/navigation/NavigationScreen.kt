package com.groupec.cleanarchitecturesampleapp.navigation

sealed class NavigationScreen(
    val route: String,
    val titleRes: Int,
) {
    object Home : NavigationScreen("home_screen", R.string.home)
    object Detail : NavigationScreen("detail_screen", R.string.detail)
    object DetailContributor : NavigationScreen("detail_contributor_screen", R.string.detail_contributor)
}

enum class NavigationScreen(
    val titleTextId: Int,
) {
    HOME(
        titleTextId = R.string.app_name
    ),
    DETAIL(
        titleTextId = R.string.app_name
    ),
    DETAIL_CONTRIBUTOR(
        titleTextId = R.string.app_name
    ),
}
