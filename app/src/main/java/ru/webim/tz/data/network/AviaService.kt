package ru.webim.tz.data.network

import retrofit2.http.GET
import retrofit2.http.Query
import ru.webim.tz.data.network.model.TicketListResponse
import ru.webim.tz.data.network.model.UserResponse

interface AviaService {

    @GET("/auth")
    suspend fun createUser(
        @Query("login") login: String,
        @Query("password") password: Int
    ): UserResponse

    @GET("/get")
    suspend fun getTicketList(
        @Query("token") token: String
    ): TicketListResponse
}