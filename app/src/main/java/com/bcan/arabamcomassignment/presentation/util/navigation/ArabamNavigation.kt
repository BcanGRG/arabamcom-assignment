package com.bcan.arabamcomassignment.presentation.util.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bcan.arabamcomassignment.presentation.detail.CarDetailScreen
import com.bcan.arabamcomassignment.presentation.list.CarListScreen

@Composable
fun ArabamNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.ListScreen.route) {

        composable(route = Screen.ListScreen.route) {
            CarListScreen(navController)
        }

        composable(
            route = Screen.DetailScreen.route + "/{car_id}",
            arguments = listOf(
                navArgument("car_id") {
                    type = NavType.IntType
                }
            )
        ) { entry ->
            CarDetailScreen(
                navController = navController,
                carId = entry.arguments?.getInt("car_id")
            )
        }
    }
}
