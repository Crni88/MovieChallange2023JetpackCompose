package com.example.moviechallange2023jetpackcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.moviechallange2023jetpackcompose.navigation.Routes.DASHBOARD
import com.example.moviechallange2023jetpackcompose.navigation.Routes.LOGIN
import com.example.moviechallange2023jetpackcompose.navigation.Routes.SEARCH_MOVIES
import com.example.moviechallange2023jetpackcompose.navigation.Routes.TASK_MANAGER
import com.example.moviechallange2023jetpackcompose.ui.screens.dashboard.DashboardScreenRoute
import com.example.moviechallange2023jetpackcompose.ui.screens.login.LoginScreenRoute
import com.example.moviechallange2023jetpackcompose.ui.screens.movieDetails.MovieDetailsRoute
import com.example.moviechallange2023jetpackcompose.ui.screens.searchMovies.SearchMoviesRoute
import com.example.moviechallange2023jetpackcompose.ui.taskManager.TaskManagerRoot

@Composable
fun MovieNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = DASHBOARD) {
        composable(LOGIN) {
            LoginScreenRoute(navController = navController)
        }
        composable(DASHBOARD) {
            DashboardScreenRoute(navController = navController)
        }
        composable(
            route = Routes.MOVIE_DETAILS,
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("movieId") ?: -1
            MovieDetailsRoute(navController = navController, movieId = movieId)
        }
        composable(SEARCH_MOVIES) {
            SearchMoviesRoute(navController = navController)
        }
        composable(TASK_MANAGER) {
            TaskManagerRoot(navController = navController)
        }

    }
}