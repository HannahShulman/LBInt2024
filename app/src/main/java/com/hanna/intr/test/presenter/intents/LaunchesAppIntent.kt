package com.hanna.intr.test.presenter.intents


// Intents for List Screen
sealed class LaunchesListIntent {
    data object LoadLaunches : LaunchesListIntent()
    data class LaunchSelected(val id: String) : LaunchesListIntent()
    data object Retry : LaunchesListIntent()
}

// Intents for Detail Screen
sealed class LaunchDetailIntent {
    data class LoadLaunchDetail(val id: String) : LaunchDetailIntent()
    data object Retry : LaunchDetailIntent()
}