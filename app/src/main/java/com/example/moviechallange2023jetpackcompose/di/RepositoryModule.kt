package com.example.moviechallange2023jetpackcompose.di

import com.example.moviechallange2023jetpackcompose.data.IMovieRepository
import com.example.moviechallange2023jetpackcompose.data.ITaskManagerRepository
import com.example.moviechallange2023jetpackcompose.data.IUserRepository
import com.example.moviechallange2023jetpackcompose.data.MovieRepository
import com.example.moviechallange2023jetpackcompose.data.TaskRepository
import com.example.moviechallange2023jetpackcompose.data.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindUserRepository(userRepository: UserRepository): IUserRepository

    @Binds
    @Singleton
    abstract fun bindMovieRepository(movieRepository: MovieRepository): IMovieRepository

    @Binds
    @Singleton
    abstract fun bindTaskManagerRepository(taskRepository: TaskRepository): ITaskManagerRepository
}
