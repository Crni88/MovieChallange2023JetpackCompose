package com.example.moviechallange2023jetpackcompose.ui.screens.movieDetails

import FiveStarRating
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Close
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.moviechallange2023jetpackcompose.domain.MovieItem
import com.example.moviechallange2023jetpackcompose.ui.components.GlideImage
import com.example.moviechallange2023jetpackcompose.ui.components.KeyFactsCard
import com.example.moviechallange2023jetpackcompose.ui.components.MovieChip
import com.example.moviechallange2023jetpackcompose.ui.theme.MovieShapes
import com.example.moviechallange2023jetpackcompose.ui.theme.inputFieldGray

@Composable
fun MovieDetailsRoute(
    navController: NavHostController,
    movieDetailsViewModel: MovieDetailsViewModel = hiltViewModel(),
    movieId: Int
) {
    val movieDetailsState by movieDetailsViewModel.movieDetailsState.collectAsState()

    MovieDetailsScreen(
        movieDetailsState = movieDetailsState,
        onBackClick = { navController.popBackStack() },
        bookmarkMovie = { movieDetailsViewModel.bookmarkMovie(it) }
    )
}

@Composable
fun MovieDetailsScreen(
    onBackClick: () -> Boolean,
    movieDetailsState: MovieDetailsState,
    bookmarkMovie: (MovieItem) -> Unit
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(vertical = 25.dp, horizontal = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(74.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.clickable { bookmarkMovie(movieDetailsState.movie!!) },
                imageVector = if (movieDetailsState.movie?.isBookmarked == true) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                tint = if (movieDetailsState.movie?.isBookmarked == true) Color.Yellow else Color.Black,
                contentDescription = "Bookmark Icon",
            )
            Spacer(modifier = Modifier.width(30.dp))
            Card(
                modifier = Modifier
                    .size(30.dp)
                    .clickable { onBackClick() }
                    .clip(CircleShape)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        modifier = Modifier.size(15.dp),
                        imageVector = Icons.Default.Close,
                        contentDescription = "Go back Icon",
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        Card(
            shape = MovieShapes.small,
            elevation = CardDefaults.cardElevation(defaultElevation = 24.dp),
            modifier = Modifier
                .width(200.dp)
                .height(300.dp)
        ) {
            GlideImage(
                modifier = Modifier.fillMaxSize(),
                imageUrl = movieDetailsState.movie?.posterUrl ?: ""
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        FiveStarRating(starSize = 14.dp, rating = 4.12f)
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = movieDetailsState.movie?.releaseDate ?: "",
                color = inputFieldGray,
                style = MaterialTheme.typography.labelSmall
            )
            Text(
                text = " - ",
                color = inputFieldGray,
                style = MaterialTheme.typography.labelSmall
            )
            Text(
                text = movieDetailsState.movie?.runtime.toString(),
                color = inputFieldGray,
                style = MaterialTheme.typography.labelSmall
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = movieDetailsState.movie?.title ?: "",
                color = Color.Black,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = (" (" + movieDetailsState.movie?.releaseDate?.substring(0, 4) + ")"),
                color = Color.Gray,
                style = MaterialTheme.typography.titleMedium
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        LazyRow {
            movieDetailsState.movie?.let {
                items(it.genres) { genre ->
                    MovieChip(genre)
                    Spacer(modifier = Modifier.width(6.dp))
                }
            }
        }
        Spacer(modifier = Modifier.height(50.dp))
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Overview",
                color = Color.Black,
                style = MaterialTheme.typography.titleLarge,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = movieDetailsState.movie?.overview ?: "",
                color = Color.Black,
                style = MaterialTheme.typography.bodyMedium,
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "Key Facts",
                color = Color.Black,
                style = MaterialTheme.typography.titleLarge,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                KeyFactsCard(
                    title = "Budget",
                    value = "$ " + movieDetailsState.formattedBudget.toString()
                )
                Spacer(modifier = Modifier.width(12.dp))
                KeyFactsCard(
                    title = "Revenue",
                    value = "$ " + movieDetailsState.formattedRevenue.toString()
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row {
                KeyFactsCard(title = "Original Language", value = movieDetailsState.movie?.language)
                Spacer(modifier = Modifier.width(12.dp))
                KeyFactsCard(title = "Rating", value = movieDetailsState.movie?.rating.toString())
            }
        }
    }
}