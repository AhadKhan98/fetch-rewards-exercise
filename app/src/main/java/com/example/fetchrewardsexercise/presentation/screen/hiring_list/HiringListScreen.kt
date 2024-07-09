package com.example.fetchrewardsexercise.presentation.screen.hiring_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fetchrewardsexercise.presentation.screen.hiring_list.component.HiringListErrorMessage
import com.example.fetchrewardsexercise.presentation.screen.hiring_list.component.HiringListLoading
import com.example.fetchrewardsexercise.presentation.screen.hiring_list.component.LazyHiringList


@Composable
fun HiringListScreen(viewModel: HiringListViewModel) {
    val uiState = viewModel.uiState.collectAsState().value
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        with(uiState) {
            when {
                isLoading -> {
                    HiringListLoading(modifier = Modifier.align(Alignment.Center))
                }

                errorState.hasError -> {
                    HiringListErrorMessage(
                        modifier = Modifier.align(Alignment.Center),
                        message = errorState.errorMessage,
                        onRetryFetch = actions.onRetryFetch
                    )
                }

                groupedHiringList != null && !isLoading -> {
                    LazyHiringList(groupedHiringList)
                }
            }
        }
    }
}