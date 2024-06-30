package com.hanna.intr.test.presentation.routes

sealed class NavRoutes(val route: String) {
    data object LaunchList : NavRoutes("launchList")
    data object LaunchDetail : NavRoutes("launchDetail/{launchId}") {
        fun createRouteWithArgs(launchId: String) = "launchDetail/$launchId"
    }
}