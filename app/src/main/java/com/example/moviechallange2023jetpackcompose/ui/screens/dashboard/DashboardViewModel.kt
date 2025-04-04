package com.example.moviechallange2023jetpackcompose.ui.screens.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviechallange2023jetpackcompose.data.IMovieRepository
import com.example.moviechallange2023jetpackcompose.data.IUserRepository
import com.example.moviechallange2023jetpackcompose.domain.MovieItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DashboardState(
    val allMovies: List<MovieItem> = emptyList<MovieItem>()
)

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val movieRepository: IMovieRepository,
    private val userRepository: IUserRepository
) : ViewModel() {
    private val _dashboardState = MutableStateFlow(DashboardState())
    val dashboardState = _dashboardState.asStateFlow()

    init {
        viewModelScope.launch {
            // Collect movies from the repository reactively.
            movieRepository.movieListFlow.collect { movies ->
                updateState(allMovies = movies.filter { it.isStaffPick })
            }
        }
    }

    fun bookmarkMovie(movie: MovieItem) {
        movieRepository.setMovieBookmark(movieId = movie.id, bookmarked = !movie.isBookmarked)
    }

    fun updateState(
        allMovies: List<MovieItem> = dashboardState.value.allMovies,
    ) {
        _dashboardState.value = dashboardState.value.copy(
            allMovies = allMovies,
        )
    }
}