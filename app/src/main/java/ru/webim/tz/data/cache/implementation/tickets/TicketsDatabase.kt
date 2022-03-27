package ru.webim.tz.data.cache.implementation.tickets

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.webim.tz.data.cache.model.TicketCacheEntity

@Database(entities = [TicketCacheEntity::class ], version = 1)
abstract class TicketsDatabase: RoomDatabase() {

    abstract fun ticketDao(): TicketDao

    companion object{
        const val DATABASE_NAME: String = "ticket_db"
    }
}