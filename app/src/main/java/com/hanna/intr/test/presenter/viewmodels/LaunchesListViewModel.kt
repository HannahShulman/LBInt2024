package com.hanna.intr.test.presenter.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanna.intr.test.domain.usecases.FetchAllLaunchesUseCase
import com.hanna.intr.test.presenter.events.LaunchesListEvent
import com.hanna.intr.test.presenter.intents.LaunchesListIntent
import com.hanna.intr.test.presenter.ui.uistates.LaunchesListUiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LaunchesListViewModel(private val fetchListUseCase: FetchAllLaunchesUseCase) : ViewModel() {

    private val _launchesListUiState = MutableStateFlow(LaunchesListUiState(false, null, null))
    val launchesListUiState: StateFlow<LaunchesListUiState> = _launchesListUiState.asStateFlow()

    private val _eventFlow = MutableSharedFlow<LaunchesListEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        handleIntent(LaunchesListIntent.LoadLaunches)
    }

    fun handleIntent(intent: LaunchesListIntent) {
        when (intent) {
            is LaunchesListIntent.LaunchSelected -> navigateToDetail(intent.id)
            LaunchesListIntent.LoadLaunches, LaunchesListIntent.Retry -> viewModelScope.launch { fetchAllLaunches() }
        }

    }

    private fun navigateToDetail(id: String) {
        viewModelScope.launch {
            _eventFlow.emit(LaunchesListEvent.NavigateToDetail(id))
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