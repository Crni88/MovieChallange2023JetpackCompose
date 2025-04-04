package com.example.moviechallange2023jetpackcompose.ui.screens.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.moviechallange2023jetpackcompose.R
import com.example.moviechallange2023jetpackcompose.domain.MovieItem
import com.example.moviechallange2023jetpackcompose.navigation.Routes
import com.example.moviechallange2023jetpackcompose.navigation.Routes.SEARCH_MOVIES
import com.example.moviechallange2023jetpackcompose.navigation.Routes.TASK_MANAGER
import com.example.moviechallange2023jetpackcompose.ui.components.MoviesColumn
import com.example.moviechallange2023jetpackcompose.ui.components.YourFavoritesMovieComponent
import com.example.moviechallange2023jetpackcompose.ui.theme.MovieShapes

@Composable
fun DashboardScreenRoute(
    navController: NavHostController,
    dashboardViewModel: DashboardViewModel = hiltViewModel(),
) {
    val dashboardState by dashboardViewModel.dashboardState.collectAsState()
    DashboardScreen(
        dashboardState = dashboardState,
        bookmarkMovie = { dashboardViewModel.bookmarkMovie(it) },
        navigateToSearchMoviesScreen = { navController.navigate(SEARCH_MOVIES) },
        navigateToMovieDetailsScreen = { movieId: Int ->
            navController.navigate(Routes.movieDetailsRoute(movieId))
        },
        navigateToTaskManagerScreen = { navController.navigate(TASK_MANAGER) }
    )
}

@Composable
fun DashboardScreen(
    dashboardState: DashboardState,
    bookmarkMovie: (MovieItem) -> Unit,
    navigateToSearchMoviesScreen: () -> Unit,
    navigateToMovieDetailsScreen: (Int) -> Unit,
    navigateToTaskManagerScreen: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp, vertical = 55.dp)
    ) {
        // Header Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                modifier = Modifier
                    .size(58.dp)
                    .clip(CircleShape),
                painter = painterResource(R.drawable.poster_grid),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Hello \uD83D\uDC4B",
                    color = Color.Black,
                    style = MaterialTheme.typography.labelLarge
                )
                Text(
                    text = "Jane Doe",
                    color = Color.Black,
                    style = MaterialTheme.typography.titleSmall
                )
            }
            Card(
                modifier = Modifier
                    .size(48.dp)
                    .clickable { navigateToSearchMoviesScreen() },
                colors = CardDefaults.cardColors(containerColor = Color.White),
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        modifier = Modifier,
                        imageVector = Icons.Default.Search,
                        tint = Color.Gray,
                        contentDescription = "Search Icon",
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
//        Button(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(50.dp),
//            shape = MovieShapes.medium,
//            colors = ButtonDefaults.buttonColors(
//                containerColor = Color.Black
//            ),
//            onClick = { navigateToTaskManagerScreen() }) {
//            Text(
//                text = "Task Manager",
//                color = Color.White,
//                style = MaterialTheme.typography.titleSmall
//            )
//        }
        Text(
            text = stringResource(R.string.your_favorites),
            color = Color.Black,
            style = MaterialTheme.typography.labelLarge
        )
        Spacer(modifier = Modifier.height(20.dp))
        LazyRow {
            if (dashboardState.allMovies.any { it.isBookmarked }) {
                items(dashboardState.allMovies.filter { it.isBookmarked }) { movie ->
                    YourFavoritesMovieComponent(
                        onClick = { navigateToMovieDetailsScreen(movie.id) },
                        imageUrl = movie.posterUrl
                    )
                    Spacer(modifier = Modifier.width(30.dp))
                }
            } else {
                item {
                    Spacer(modifier = Modifier.height(270.dp))
                }
            }
        }
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "OUR STAFF PICKS",
            color = Color.Black,
            style = MaterialTheme.typography.labelLarge
        )
        Spacer(modifier = Modifier.height(20.dp))
        MoviesColumn(
            allMovies = dashboardState.allMovies,
            bookmarkMovie = bookmarkMovie,
            navigateToMovieDetailsScreen = { movieId -> navigateToMovieDetailsScreen(movieId) }
        )
    }
}
