package ru.webim.tz.data.cache.implementation.tickets

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.webim.tz.data.cache.model.TicketCacheEntity

@Dao
interface TicketDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTickets(tickets: List<TicketCacheEntity>): LongArray

    @Query("SELECT * FROM tickets")
    suspend fun getTickets(): List<TicketCacheEntity>

}