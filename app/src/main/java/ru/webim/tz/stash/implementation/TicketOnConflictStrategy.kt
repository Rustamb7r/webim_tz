package ru.webim.tz.stash.implementation

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.webim.tz.stash.TicketCacheEntity

@Dao
interface TicketOnConflictStrategy {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTickets(tickets: List<TicketCacheEntity>): LongArray

    @Query("SELECT * FROM tickets")
    suspend fun getTickets(): List<TicketCacheEntity>

}