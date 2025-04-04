package com.example.moviechallange2023jetpackcompose.ui.components

import FiveStarRating
import android.widget.ImageView
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.bumptech.glide.Glide
import com.example.moviechallange2023jetpackcompose.domain.MovieItem
import com.example.moviechallange2023jetpackcompose.ui.theme.MovieShapes
import com.example.moviechallange2023jetpackcompose.ui.theme.inputFieldGray

@Composable
fun YourFavoritesMovieComponent(onClick: () -> Unit, imageUrl: String) {
    Card(
        shape = MovieShapes.extraSmall,
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        modifier = Modifier
            .width(180.dp)
            .height(270.dp)
            .clickable { onClick() }
    ) {
        GlideImage(
            imageUrl = imageUrl,
            modifier = Modifier
                .fillMaxSize()
                .clip(MovieShapes.small)
                .clickable { onClick() }
        )
    }
}

@Composable
fun MovieItemComposable(
    onClick: () -> Unit,
    title: String,
    yearOfRelease: String,
    rating: Double,
    posterUrl: String,
    onBookmarkMovie: () -> Unit,
    isBookMarked: Boolean,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(vertical = 7.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        GlideImage(
            imageUrl = posterUrl,
            modifier = Modifier
                .width(60.dp)
                .height(90.dp)
                .clip(MovieShapes.small)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) { onClick() }
        )
        Spacer(modifier = Modifier.width(30.dp))
        Column(
            modifier = Modifier
                .weight(1f)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) { onClick() }
        ) {
            Text(
                text = yearOfRelease,
                color = inputFieldGray,
                style = MaterialTheme.typography.labelLarge
            )
            Text(
                text = title,
                color = Color.Black,
                style = MaterialTheme.typography.titleSmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            FiveStarRating(starSize = 9.dp, rating = rating.toFloat())
        }
        Icon(
            modifier = Modifier.clickable { onBookmarkMovie() },
            imageVector = if (isBookMarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
            tint = if (isBookMarked) Color.Yellow else Color.Black,
            contentDescription = "Bookmark Icon",
        )
    }
}

@Composable
fun GlideImage(
    imageUrl: String,
    modifier: Modifier = Modifier
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            ImageView(context).apply {
                scaleType = ImageView.ScaleType.CENTER_CROP
            }
        },
        update = { imageView ->
            Glide.with(imageView.context)
                .load(imageUrl)
                .into(imageView)
        }
    )
}

@Composable
fun MoviesColumn(
    allMovies: List<MovieItem>,
    bookmarkMovie: (MovieItem) -> Unit,
    navigateToMovieDetailsScreen: (Int) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        if (allMovies.isNotEmpty()) {
            items(
                items = allMovies,
            ) { movie ->
                MovieItemComposable(
                    onClick = { navigateToMovieDetailsScreen(movie.id) },
                    title = movie.title,
                    yearOfRelease = movie.releaseDate.substring(0, 4),
                    rating = movie.rating,
                    posterUrl = movie.posterUrl,
                    onBookmarkMovie = { bookmarkMovie(movie) },
                    isBookMarked = movie.isBookmarked,
                )
            }
        } else {
            item {
                Text(
                    text = "Unable to load",
                    color = Color.Black,
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }
}
