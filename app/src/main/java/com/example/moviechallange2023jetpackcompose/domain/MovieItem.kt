package com.example.moviechallange2023jetpackcompose.domain

data class MovieItem(
    val budget: Int,
    val cast: List<Cast>,
    val director: Director,
    val genres: List<String>,
    val id: Int,
    val language: String,
    val overview: String,
    val posterUrl: String,
    val rating: Double,
    val releaseDate: String,
    val revenue: Int,
    val reviews: Int,
    val runtime: Int,
    val title: String,
    val isStaffPick: Boolean,
    val isBookmarked: Boolean,
    val formattedBudget: String? = null,
    val formattedRevenue: String? = null,
)