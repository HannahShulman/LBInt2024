package com.hanna.intr.test.presenter.ui.uistates

import com.hanna.intr.test.domain.models.Launch

data class LaunchesListUiState(val isLoading: Boolean, val launchesList: List<Launch>?, val error: String?)
data class LaunchesByIdUiState(val isLoading: Boolean, val launch: Launch?, val error: String?)