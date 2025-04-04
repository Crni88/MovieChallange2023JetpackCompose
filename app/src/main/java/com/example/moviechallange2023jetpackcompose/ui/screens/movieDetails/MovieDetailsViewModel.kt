package com.example.moviechallange2023jetpackcompose.ui.screens.movieDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviechallange2023jetpackcompose.data.IMovieRepository
import com.example.moviechallange2023jetpackcompose.domain.MovieItem
import com.example.moviechallange2023jetpackcompose.util.formatNumber
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MovieDetailsState(
    val movie: MovieItem? = null,
    val formattedBudget: String = "",
    val formattedRevenue: String = ""
)

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val movieRepository: IMovieRepository
) : ViewModel() {

    private val _movieDetailsState = MutableStateFlow(MovieDetailsState())
    val movieDetailsState = _movieDetailsState.asStateFlow()

    private val movieId: Int =
        savedStateHandle["movieId"] ?: throw IllegalArgumentException("Missing movie id")

    init {
        viewModelScope.launch {
            val movie = movieRepository.getMovieById(movieId)
            updateState(movie)
        }
    }

    fun bookmarkMovie(movie: MovieItem) {
        val newBookmarkedState = !movie.isBookmarked
        movieRepository.setMovieBookmark(movieId = movie.id, bookmarked = newBookmarkedState)
        updateState(movie.copy(isBookmarked = newBookmarkedState))
    }


    private fun updateState(movie: MovieItem?) {
        if (movie == null) {
            _movieDetailsState.value = _movieDetailsState.value.copy(movie = null)
            return
        }

        val safeBudget = movie.budget ?: 0
        val safeRevenue = movie.revenue ?: 0

        val formattedBudget = formatNumber(safeBudget.toLong())
        val formattedRevenue = formatNumber(safeRevenue.toLong())

        _movieDetailsState.value = movieDetailsState.value.copy(
            movie = movie.copy(
                formattedBudget = formattedBudget,
                formattedRevenue = formattedRevenue
            ),
            formattedBudget = formattedBudget,
            formattedRevenue = formattedRevenue
        )
    }
}
