package com.example.fetchrewardsexercise.presentation.screen.hiring_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetchrewardsexercise.core.util.Resource
import com.example.fetchrewardsexercise.domain.usecase.GetSortedHiringListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HiringListViewModel @Inject constructor(
    private val getSortedHiringList: GetSortedHiringListUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        HiringListUiState(actions = HiringListActions(onRetryFetch = ::onRetryFetch))
    )
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch { getHiringListAndUpdateUi() }
    }

    private suspend fun getHiringListAndUpdateUi() {
        getSortedHiringList().collectLatest { resource ->
            _uiState.update { it.copy(isLoading = resource is Resource.Loading) }
            when (resource) {
                is Resource.Loading -> {
                    _uiState.update { it.copy(isLoading = true) }
                }

                is Resource.Success -> {
                    _uiState.update { it.copy(groupedHiringList = resource.data) }
                }

                is Resource.Failure -> {
                    val errorState = ErrorState(hasError = true, errorMessage = resource.message)
                    _uiState.update { it.copy(errorState = errorState) }
                }
            }
        }
    }

    private fun onRetryFetch() {
        _uiState.update { it.copy(errorState = ErrorState()) }
        viewModelScope.launch {
            getHiringListAndUpdateUi()
        }
    }

}