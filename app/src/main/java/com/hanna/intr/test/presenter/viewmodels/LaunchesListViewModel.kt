package com.hanna.intr.test.presenter.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanna.intr.test.domain.usecases.FetchAllLaunchesUseCase
import com.hanna.intr.test.presenter.ui.uistates.LaunchesListUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LaunchesListViewModel (private val fetchListUseCase: FetchAllLaunchesUseCase) : ViewModel() {

    private val _launchesListUiState = MutableStateFlow(LaunchesListUiState(false, null, null))
    val launchesListUiState: StateFlow<LaunchesListUiState> = _launchesListUiState.asStateFlow()

    init {
        viewModelScope.launch {
            fetchAllLaunches()
        }
    }

    suspend fun fetchAllLaunches() {
        _launchesListUiState.value = _launchesListUiState.value.copy(isLoading = true, launchesList = null, error = null)
        val result = fetchListUseCase()
        _launchesListUiState.value =
            _launchesListUiState.value.copy(isLoading = false, launchesList = result.getOrNull(), error = result.exceptionOrNull()?.message)
    }

    fun retryFetchAllLaunches() {
        viewModelScope.launch {
            fetchAllLaunches()
        }
    }
}