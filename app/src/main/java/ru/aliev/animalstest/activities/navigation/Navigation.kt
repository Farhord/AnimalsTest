package ru.aliev.animalstest.activities.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ru.aliev.animalstest.features.detail.ui.DetailScreen
import ru.aliev.animalstest.features.favorite.ui.FavoriteScreen
import ru.aliev.animalstest.features.main.ui.MainScreen

@Composable
fun Navigation(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screen.MAIN.route) {
        composable(Screen.MAIN.route) {
            MainScreen(
                viewModel = hiltViewModel(),
                navToDetail = {
                    navController.navigate(Screen.DETAIL.withArgs(it))
                }
            )
        }
        composable(
            route = Screen.DETAIL.withKeys("id"),
            arguments = listOf(
                navArgument(name = "id") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            DetailScreen(
                id = requireNotNull(backStackEntry.arguments?.getString("id")),
                viewModel = hiltViewModel()
            )
        }
        composable(Screen.FAVORITE.route) {
            FavoriteScreen(
                viewModel = hiltViewModel(),
                navToDetail = {
                    navController.navigate(Screen.DETAIL.withArgs(it))
                }
            )
        }
    }
}