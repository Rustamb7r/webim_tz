package ru.webim.tz.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.webim.tz.stash.source.TicketsCacheSource
import ru.webim.tz.stash.implementation.TicketOnConflictStrategy
import ru.webim.tz.stash.implementation.TicketsCacheImplementation
import ru.webim.tz.stash.implementation.TicketsDB

@Module
object ListModule {

    @ListScope
    @Provides
    fun provideTicketDb(app: Application): TicketsDB {
        return Room
            .databaseBuilder(app, TicketsDB::class.java, TicketsDB.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @ListScope
    @Provides
    fun provideTicketDAO(ticketDatabase: TicketsDB): TicketOnConflictStrategy {
        return ticketDatabase.ticketDao()
    }

    @ListScope
    @Provides
    fun provideTicketCacheDataSourceImpl(ticketDao: TicketOnConflictStrategy): TicketsCacheSource {
        return TicketsCacheImplementation(ticketDao)
    }

}
