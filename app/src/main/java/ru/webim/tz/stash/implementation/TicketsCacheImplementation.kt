package ru.webim.tz.stash.implementation

import ru.webim.tz.stash.source.TicketsCacheSource
import ru.webim.tz.stash.toTicket
import ru.webim.tz.stash.toCacheTicket
import ru.webim.tz.model.TicketParameter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TicketsCacheImplementation
@Inject
constructor(
    private val ticketDao: TicketOnConflictStrategy
) : TicketsCacheSource {

    override suspend fun insertTickets(tickets: List<TicketParameter>): LongArray {
        return ticketDao.insertTickets(tickets.map { it.toCacheTicket() })
    }

    override suspend fun getTickets(): List<TicketParameter> {
        return ticketDao.getTickets().map { it.toTicket() }
    }

}