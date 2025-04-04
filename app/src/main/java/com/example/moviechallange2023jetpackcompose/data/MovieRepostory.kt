package com.example.moviechallange2023jetpackcompose.data

import android.content.Context
import android.util.Log
import com.example.moviechallange2023jetpackcompose.domain.MovieItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(@ApplicationContext private val context: Context) :
    IMovieRepository {

    // Using a MutableStateFlow to hold the immutable list of movies.
    private val _movieListFlow = MutableStateFlow<List<MovieItem>>(emptyList())
    override val movieListFlow: StateFlow<List<MovieItem>> get() = _movieListFlow

    init {
        val movies = getMoviesFromAssets("movies.json")
        val staffPicks = getMoviesFromAssets("staff_picks.json").map { it.copy(isStaffPick = true) }

        val staffPickIds = staffPicks.map { it.id }.toSet()
        val updatedMovies = movies.map { movie ->
            if (movie.id in staffPickIds) movie.copy(isStaffPick = true) else movie
        }

        val combinedMovies = (updatedMovies + staffPicks)
            .groupBy { it.id }
            .map { (_, movieGroup) ->
                movieGroup.first().copy(isStaffPick = movieGroup.any { it.isStaffPick })
            }
        _movieListFlow.value = combinedMovies
    }

    override fun getMovies(isStaffPick: Boolean): List<MovieItem> {
        val currentList = movieListFlow.value
        return if (isStaffPick) currentList.filter { it.isStaffPick } else currentList
    }

    private fun getMoviesFromAssets(fileName: String): List<MovieItem> {
        val json = readJsonFromAssets(fileName)
        return if (json != null) parseMovies(json) else emptyList()
    }

    private fun readJsonFromAssets(fileName: String): String? {
        return try {
            context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (e: Exception) {
            Log.e("MovieRepository", "Error reading $fileName: ${e.message}")
            null
        }
    }

    private fun parseMovies(json: String): List<MovieItem> {
        val listType = object : TypeToken<List<MovieItem>>() {}.type
        return Gson().fromJson(json, listType)
    }

    override fun getMovieById(id: Int): MovieItem? {
        return movieListFlow.value.find { it.id == id }
    }

    override fun setMovieBookmark(movieId: Int, bookmarked: Boolean) {
        // Create a new list with the updated movie instance using copy()
        _movieListFlow.value = movieListFlow.value.map { movie ->
            if (movie.id == movieId) movie.copy(isBookmarked = bookmarked) else movie
        }
    }
}
