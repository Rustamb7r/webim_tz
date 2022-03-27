package ru.webim.tz.web

import retrofit2.http.GET
import retrofit2.http.Query

interface AviaIntService {
    @GET("/auth")
    suspend fun createUser(
        @Query("login") login: String,
        @Query("password") password: Int
    ): User

    @GET("/get")
    suspend fun getTicketList(
        @Query("token") token: String
    ): TicketList
}