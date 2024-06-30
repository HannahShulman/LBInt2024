package com.hanna.intr.test.presenter.events

sealed class LaunchesListEvent {
    data class NavigateToDetail(val id: String) : LaunchesListEvent()
}