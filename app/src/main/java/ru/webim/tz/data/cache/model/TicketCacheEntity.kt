package ru.webim.tz.data.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.webim.tz.domain.model.Ticket

@Entity(tableName = "tickets")
data class TicketCacheEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0L,

    @ColumnInfo(name = "cost")
    var cost: Int,

    @ColumnInfo(name = "date")
    var date: String,

    @ColumnInfo(name = "destination")
    var dest: String,

    @ColumnInfo(name = "from")
    var from: String

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TicketCacheEntity

        if (cost != other.cost) return false
        if (date != other.date) return false
        if (dest != other.dest) return false
        if (from != other.from) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + cost.hashCode()
        result = 31 * result + date.hashCode()
        result = 31 * result + dest.hashCode()
        result = 31 * result + from.hashCode()
        return result
    }
}


fun TicketCacheEntity.toTicket() = Ticket(
    cost = cost,
    date = date,
    dest = dest,
    from = from
)

fun Ticket.toCacheTicket() = TicketCacheEntity(
    cost = cost ?: 0,
    date = date ?: "",
    dest = dest ?: "",
    from = from ?: ""
)