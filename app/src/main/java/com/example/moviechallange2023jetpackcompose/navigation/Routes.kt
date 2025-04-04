package com.example.moviechallange2023jetpackcompose.navigation

object Routes {
    const val LOGIN = "login"
    const val DASHBOARD = "dashboard"
    const val MOVIE_DETAILS = "movieDetails/{movieId}"
    const val SEARCH_MOVIES = "searchMovies"
    const val TASK_MANAGER = "taskManager"

    fun movieDetailsRoute(movieId: Int) = "movieDetails/$movieId"
}