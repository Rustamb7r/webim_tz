package ru.webim.tz.interactors

import android.util.Log
import ru.webim.tz.stash.source.TicketsCacheSource
import ru.webim.tz.web.AviaIntService
import ru.webim.tz.web.toTickets
import ru.webim.tz.model.TicketParameter
import javax.inject.Inject

class GetTicketList @Inject constructor(
    private val ticketsCacheDataSource: TicketsCacheSource,
    private val aviaService: AviaIntService,
    private val getUserToken: Token
) {
    private val TAG = "GetTicketList"

    suspend fun load(): List<TicketParameter>? {
        return try {
            getNetworkTicketsAndSave()
        } catch (e: Exception) {
            Log.w(TAG, e)
            getCachedTickets()
        }
    }

    private suspend fun getNetworkTicketsAndSave(): List<TicketParameter>? {
        val tickets = aviaService.getTicketList(getUserToken.get() ?: "").ticketList?.toTickets()
        if (tickets?.isNotEmpty() == true) {
            ticketsCacheDataSource.insertTickets(tickets)
        }
        return tickets
    }

    private suspend fun getCachedTickets(): List<TicketParameter>? {
        return try {
            ticketsCacheDataSource.getTickets()
        } catch (e: java.lang.Exception) {
            Log.w(TAG, e)
            emptyList<TicketParameter>()
        }
    }
}
