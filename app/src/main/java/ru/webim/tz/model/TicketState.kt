package ru.webim.tz.model

data class TicketState(
    var isLoading: Boolean = false,
    var isAuthenticated: Boolean = false,
    var error: TicketError? = null
)