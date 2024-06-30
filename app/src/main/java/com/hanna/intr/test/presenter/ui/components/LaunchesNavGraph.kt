package com.hanna.intr.test.presenter.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hanna.intr.test.presenter.routes.NavRoutes


@Composable
fun LaunchesNavGraph(navController: NavHostController, startDestination: String = NavRoutes.LaunchList.route) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(NavRoutes.LaunchList.route) {
            LaunchesListScreen(navController)
        }
        composable(
            route = NavRoutes.LaunchDetail.route,
            arguments = listOf(navArgument("launchId") { type = NavType.StringType })
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("launchId")?.let {
                LaunchDetailScreen(it)
            }

        }
    }
}