package com.example.moviechallange2023jetpackcompose.domain

import java.util.UUID

data class User(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val email: String
)
