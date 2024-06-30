package com.hanna.intr.test.presenter.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanna.intr.test.domain.usecases.FetchLaunchByIdUseCase
import com.hanna.intr.test.presenter.intents.LaunchDetailIntent
import com.hanna.intr.test.presenter.ui.uistates.LaunchesByIdUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LaunchByIdViewModel(
    val fetchLaunchByIdUseCase: FetchLaunchByIdUseCase
) : ViewModel() {

    private val _launchByIdUiState = MutableStateFlow(LaunchesByIdUiState(false, null, null))
    val launchByIdUiState: StateFlow<LaunchesByIdUiState> = _launchByIdUiState.asStateFlow()


    fun handleIntent(intent: LaunchDetailIntent) {
        when (intent) {
            is LaunchDetailIntent.LoadLaunchDetail -> fetchLaunchById(intent.id)
            is LaunchDetailIntent.Retry -> fetchLaunchById(launchByIdUiState.value.launch?.id ?: "")
        }
    }

    private fun fetchLaunchById(id: String?) {
        if (id == null) {
            _launchByIdUiState.value = _launchByIdUiState.value.copy(isLoading = false, launch = null, error = "Something went wrong")
            return
        }
        viewModelScope.launch {
            _launchByIdUiState.value = _launchByIdUiState.value.copy(isLoading = true, launch = null, error = null)
            val result = fetchLaunchByIdUseCase(id)
            _launchByIdUiState.value =
                _launchByIdUiState.value.copy(isLoading = false, launch = result.getOrNull(), error = result.exceptionOrNull()?.message)
        }
    }
}