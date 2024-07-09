package com.example.fetchrewardsexercise.presentation.navigation

sealed class Screen(val route: String) {

    data object HiringList : Screen(route = HIRING_LIST_SCREEN_ROUTE)

    private companion object {
        const val HIRING_LIST_SCREEN_ROUTE = "hiring_list_screen"
    }

}