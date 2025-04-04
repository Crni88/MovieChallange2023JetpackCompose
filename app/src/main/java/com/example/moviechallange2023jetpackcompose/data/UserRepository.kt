package com.example.moviechallange2023jetpackcompose.data

import android.util.Log
import com.example.moviechallange2023jetpackcompose.domain.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor() : IUserRepository {
    private var currentUser: User? = null

    override fun saveUser(user: User) {
        currentUser = user
        Log.d("User Repository", "Current user $user")
    }

    override fun getUser(): User? = currentUser
}