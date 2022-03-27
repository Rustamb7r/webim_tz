package ru.webim.tz.data.cache.abstraction

import ru.webim.tz.domain.model.Ticket

interface TicketsCacheDataSource {

    suspend fun insertTickets(tickets: List<Ticket>): LongArray

    suspend fun getTickets(): List<Ticket>
}