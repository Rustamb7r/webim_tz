package ru.webim.tz.interactors

import android.util.Log
import ru.webim.tz.data.cache.abstraction.TicketsCacheDataSource
import ru.webim.tz.data.network.AviaService
import ru.webim.tz.data.network.model.toTickets
import ru.webim.tz.domain.model.Ticket
import javax.inject.Inject

class GetTicketList @Inject constructor(
    private val ticketsCacheDataSource: TicketsCacheDataSource,
    private val aviaService: AviaService,
    private val getUserToken: GetUserToken
) {
    private val TAG = "GetTicketList"

    suspend fun load(): List<Ticket>? {
        return try {
            getNetworkTicketsAndSave()
        } catch (e: Exception) {
            Log.w(TAG, e)
            getCachedTickets()
        }
    }

    private suspend fun getNetworkTicketsAndSave(): List<Ticket>? {
        val tickets = aviaService.getTicketList(getUserToken.get() ?: "").ticketList?.toTickets()
        if (tickets?.isNotEmpty() == true) {
            ticketsCacheDataSource.insertTickets(tickets)
        }
        return tickets
    }

    private suspend fun getCachedTickets(): List<Ticket>? {
        return try {
            ticketsCacheDataSource.getTickets()
        } catch (e: java.lang.Exception) {
            Log.w(TAG, e)
            emptyList<Ticket>()
        }
    }
}
