package ru.webim.tz.stash.source

import ru.webim.tz.model.TicketParameter

interface TicketsCacheSource {

    suspend fun insertTickets(tickets: List<TicketParameter>): LongArray

    suspend fun getTickets(): List<TicketParameter>
}