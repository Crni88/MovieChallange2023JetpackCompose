package com.example.moviechallange2023jetpackcompose.data

import com.example.moviechallange2023jetpackcompose.domain.User

interface IUserRepository {
    fun getUser(): User?
    fun saveUser(user: User)
}