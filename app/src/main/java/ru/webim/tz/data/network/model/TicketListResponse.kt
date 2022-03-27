package ru.webim.tz.data.network.model

import com.google.gson.annotations.SerializedName

class TicketListResponse {

    @SerializedName("data")
    var ticketList: List<TicketData>? = null

    @SerializedName("result")
    var result: String? = null

    @SerializedName("timestamp")
    var timestamp: String? = null

}