package com.example.moviechallange2023jetpackcompose.domain

data class Ticket(
    val actual_completion_date: String,
    val comments: List<String>,
    val created_at: Long,
    val guide_id: Int,
    val how: String,
    val id: Int,
    val media: List<String>,
    val plannedCompletionDate: String,
    val score: Int,
    val status: String,
    val what: String,
    val `when`: String,
    val `where`: String,
    val who: String
)