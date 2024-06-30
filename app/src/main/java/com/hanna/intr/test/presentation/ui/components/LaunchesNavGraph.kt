package com.hanna.intr.test.presentation.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hanna.intr.test.presentation.routes.NavRoutes


@Composable
fun LaunchesNavGraph(startDestination: String = NavRoutes.LaunchList.route) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        composable(NavRoutes.LaunchList.route) {
            LaunchesListScreen(navController)
        }
        composable(
            route = NavRoutes.LaunchDetail.route,
            arguments = listOf(navArgument("launchId") { type = NavType.StringType })
        ) { backStackEntry ->
            val launchId = backStackEntry.arguments?.getString("launchId")
            if (launchId != null) {
                LaunchDetailScreen(launchId)
            }
        }
    }
}