package ru.webim.tz.data.cache.implementation.tickets

import ru.webim.tz.data.cache.abstraction.TicketsCacheDataSource
import ru.webim.tz.data.cache.model.toCacheTicket
import ru.webim.tz.data.cache.model.toTicket
import ru.webim.tz.domain.model.Ticket
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TicketsCacheDataSourceImpl
@Inject
constructor(
    private val ticketDao: TicketDao
) : TicketsCacheDataSource {

    override suspend fun insertTickets(tickets: List<Ticket>): LongArray {
        return ticketDao.insertTickets(tickets.map { it.toCacheTicket() })
    }

    override suspend fun getTickets(): List<Ticket> {
        return ticketDao.getTickets().map { it.toTicket() }
    }

}