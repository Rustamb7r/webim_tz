package ru.webim.tz.data.network.model

import com.google.gson.annotations.SerializedName
import ru.webim.tz.domain.model.Ticket

class TicketData {

    @SerializedName("cost")
    val cost: Int? = null

    @SerializedName("date")
    val date: String? = null

    @SerializedName("dest")
    val destination: String? = null

    @SerializedName("from")
    val from: String? = null
}

fun List<TicketData>.toTickets(): List<Ticket> {
    return map {
        Ticket(
            cost = it.cost,
            date = it.date,
            dest = it.destination,
            from = it.from,
        )
    }
}