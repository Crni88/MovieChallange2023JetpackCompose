package com.example.moviechallange2023jetpackcompose.ui.screens.searchMovies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviechallange2023jetpackcompose.data.IMovieRepository
import com.example.moviechallange2023jetpackcompose.domain.MovieItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SearchMoviesState(
    val allMovies: List<MovieItem> = emptyList<MovieItem>(),
    val searchMoviesQuery: String = ""
)

@HiltViewModel
class SearchMoviesViewModel @Inject constructor(
    private val movieRepository: IMovieRepository
) : ViewModel() {
    private val _searchMoviesState = MutableStateFlow(SearchMoviesState())
    val searchMoviesState = _searchMoviesState.asStateFlow()

    private var fullMovieList: List<MovieItem> = emptyList()

    init {
        viewModelScope.launch {
            movieRepository.movieListFlow.collect { movies ->
                fullMovieList = movies
                updateState(allMovies = movies)
            }
        }
    }

    fun searchMovies(searchQuery: String) {
        val filteredMovies = if (searchQuery.isBlank()) {
            fullMovieList
        } else {
            fullMovieList.filter { movie ->
                movie.title.contains(searchQuery, ignoreCase = true)
            }
        }
        updateState(allMovies = filteredMovies, searchMoviesQuery = searchQuery)
    }

    fun bookmarkMovie(movie: MovieItem) {
        movieRepository.setMovieBookmark(movieId = movie.id, bookmarked = !movie.isBookmarked)
    }


    private fun updateState(
        allMovies: List<MovieItem> = searchMoviesState.value.allMovies,
        searchMoviesQuery: String = searchMoviesState.value.searchMoviesQuery
    ) {
        _searchMoviesState.value = searchMoviesState.value.copy(
            allMovies = allMovies,
            searchMoviesQuery = searchMoviesQuery
        )
    }
}