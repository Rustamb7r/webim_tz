package ru.webim.tz.web

import com.google.gson.annotations.SerializedName
import ru.webim.tz.model.TicketParameter

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

fun List<TicketData>.toTickets(): List<TicketParameter> {
    return map {
        TicketParameter(
            cost = it.cost,
            date = it.date,
            dest = it.destination,
            from = it.from,
        )
    }
}