package com.example.fetchrewardsexercise.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fetchrewardsexercise.presentation.screen.hiring_list.HiringListScreen
import com.example.fetchrewardsexercise.presentation.screen.hiring_list.HiringListViewModel

@Composable
fun MainNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.HiringList.route,
        modifier = Modifier
    ) {
        composable(Screen.HiringList.route) {
            val viewModel: HiringListViewModel = hiltViewModel()
            HiringListScreen(viewModel)
        }
    }
}