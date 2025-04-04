package com.example.moviechallange2023jetpackcompose.data

import com.example.moviechallange2023jetpackcompose.domain.Ticket
import com.example.moviechallange2023jetpackcompose.retrofit.ApiService
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepository @Inject constructor(private val apiService: ApiService) :
    ITaskManagerRepository {
    override suspend fun getTasks(isStaffPick: Boolean): Response<List<Ticket>> {
        return apiService.getTickets()
    }

    override suspend fun postTask(ticket: Ticket): Response<Ticket> {
        return apiService.postTicket(ticket)
    }
}
