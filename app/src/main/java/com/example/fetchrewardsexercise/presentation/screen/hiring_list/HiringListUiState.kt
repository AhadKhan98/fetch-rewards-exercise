package com.example.fetchrewardsexercise.presentation.screen.hiring_list

import com.example.fetchrewardsexercise.domain.model.GroupedHiringList

data class HiringListUiState(
    val isLoading: Boolean = true,
    val errorState: ErrorState = ErrorState(),
    val groupedHiringList: List<GroupedHiringList>? = null,
    val actions: HiringListActions
)

data class ErrorState(
    val hasError: Boolean = false,
    val errorMessage: String? = null
)

data class HiringListActions(
    val onRetryFetch: () -> Unit
)
