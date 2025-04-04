package com.example.moviechallange2023jetpackcompose.data

import com.example.moviechallange2023jetpackcompose.domain.MovieItem
import kotlinx.coroutines.flow.StateFlow

interface IMovieRepository {
    // Exposes the current list of movies as an observable StateFlow.
    val movieListFlow: StateFlow<List<MovieItem>>

    /**
     * Returns a list of movies.
     * @param isStaffPick If true, filters the list to only include movies marked as staff picks.
     */
    fun getMovies(isStaffPick: Boolean = false): List<MovieItem>

    /**
     * Retrieves a single movie by its unique identifier.
     * @param id The movie's unique identifier.
     * @return The MovieItem with the given id, or null if not found.
     */
    fun getMovieById(id: Int): MovieItem?

    /**
     * Updates the bookmark status for the movie with the given id.
     * @param movieId The unique identifier of the movie.
     * @param bookmarked The new bookmark status.
     */
    fun setMovieBookmark(movieId: Int, bookmarked: Boolean)

}
