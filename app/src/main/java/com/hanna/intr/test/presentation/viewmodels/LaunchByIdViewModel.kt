package com.hanna.intr.test.presentation.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.hanna.intr.test.domain.usecases.FetchLaunchByIdUseCase
import com.hanna.intr.test.presentation.ui.uistates.LaunchesByIdUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LaunchByIdViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    val fetchLaunchByIdUseCase: FetchLaunchByIdUseCase) : ViewModel() {

    private val _launchByIdUiState = MutableStateFlow(LaunchesByIdUiState(false, null, null))
    val launchByIdUiState: StateFlow<LaunchesByIdUiState> = _launchByIdUiState.asStateFlow()

    private suspend fun fetchLaunchById(id: String) {
        _launchByIdUiState.value = _launchByIdUiState.value.copy(isLoading = true, launchesList = null, error = null)
        val result = fetchLaunchByIdUseCase(id)
        _launchByIdUiState.value =
            _launchByIdUiState.value.copy(isLoading = false, launchesList = result.getOrNull(), error = result.exceptionOrNull()?.message)
    }

}