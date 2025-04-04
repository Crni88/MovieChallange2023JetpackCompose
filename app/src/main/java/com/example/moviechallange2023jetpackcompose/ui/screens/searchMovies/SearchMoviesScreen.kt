package com.example.moviechallange2023jetpackcompose.ui.screens.searchMovies

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.moviechallange2023jetpackcompose.domain.MovieItem
import com.example.moviechallange2023jetpackcompose.navigation.Routes
import com.example.moviechallange2023jetpackcompose.ui.components.MovieTextField
import com.example.moviechallange2023jetpackcompose.ui.components.MoviesColumn

@Composable
fun SearchMoviesRoute(
    navController: NavHostController,
    searchMoviesViewModel: SearchMoviesViewModel = hiltViewModel()
) {
    val dashboardState by searchMoviesViewModel.searchMoviesState.collectAsState()
    SearchMovieScreen(
        state = dashboardState,
        searchMovies = { searchMoviesViewModel.searchMovies(it) },
        goBack = { navController.popBackStack() },
        bookmarkMovie = { searchMoviesViewModel.bookmarkMovie(it) },
        navigateToMovieDetailsScreen = { movieId: Int ->
            navController.navigate(Routes.movieDetailsRoute(movieId))
        }
    )
}

@Composable
fun SearchMovieScreen(
    goBack: () -> Boolean,
    state: SearchMoviesState,
    bookmarkMovie: (MovieItem) -> Unit,
    searchMovies: (String) -> Unit,
    navigateToMovieDetailsScreen: (Int) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(64.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.clickable { goBack() },
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Go Back Icon",
            )
            Text(
                text = "All Movies",
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = Color.Black,
                style = MaterialTheme.typography.titleLarge
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        MovieTextField(
            icon = Icons.Filled.Search,
            value = state.searchMoviesQuery,
            onValueChange = { searchMovies(it) },
            label = "Search"
        )
        Spacer(modifier = Modifier.height(35.dp))
        MoviesColumn(state.allMovies, bookmarkMovie, navigateToMovieDetailsScreen)
    }
}