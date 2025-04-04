package com.example.moviechallange2023jetpackcompose.data

import com.example.moviechallange2023jetpackcompose.domain.Ticket
import retrofit2.Response

interface ITaskManagerRepository {
    suspend fun getTasks(isStaffPick: Boolean): Response<List<Ticket>>

    suspend fun postTask(ticket: Ticket): Response<Ticket>
}
