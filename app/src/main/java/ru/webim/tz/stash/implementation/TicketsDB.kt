package ru.webim.tz.stash.implementation

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.webim.tz.stash.TicketCacheEntity

@Database(entities = [TicketCacheEntity::class ], version = 1)
abstract class TicketsDB: RoomDatabase() {

    abstract fun ticketDao(): TicketOnConflictStrategy

    companion object{
        const val DATABASE_NAME: String = "ticket_db"
    }
}