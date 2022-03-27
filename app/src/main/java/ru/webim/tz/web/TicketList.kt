package ru.webim.tz.web

import com.google.gson.annotations.SerializedName

class TicketList {
    @SerializedName("data")
    var ticketList: List<TicketData>? = null

    @SerializedName("result")
    var result: String? = null

    @SerializedName("timestamp")
    var timestamp: String? = null
}