package com.example.moviechallange2023jetpackcompose.retrofit

// ApiService.kt
import com.example.moviechallange2023jetpackcompose.domain.Ticket
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("ticket")
    suspend fun getTickets(): Response<List<Ticket>>

    @POST("ticket")
    suspend fun postTicket(@Body ticket: Ticket): Response<Ticket>
}
