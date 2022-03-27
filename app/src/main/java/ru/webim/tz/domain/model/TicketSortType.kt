package ru.webim.tz.domain.model

sealed class TicketSortType(id: String)

class AscendingOrder : TicketSortType("AscendingOrder") {
    override fun equals(other: Any?): Boolean {
        return other is AscendingOrder
    }

    override fun hashCode(): Int {
        return "AscendingOrder".hashCode()
    }

    override fun toString(): String {
        return "AscendingOrder"
    }
}

class DescendingOrder : TicketSortType("DescendingOrder") {
    override fun equals(other: Any?): Boolean {
        return other is DescendingOrder
    }

    override fun hashCode(): Int {
        return "DescendingOrder".hashCode()
    }

    override fun toString(): String {
        return "DescendingOrder"
    }
}