package ru.webim.tz.di.list

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.webim.tz.data.cache.abstraction.TicketsCacheDataSource
import ru.webim.tz.data.cache.implementation.tickets.TicketDao
import ru.webim.tz.data.cache.implementation.tickets.TicketsCacheDataSourceImpl
import ru.webim.tz.data.cache.implementation.tickets.TicketsDatabase

@Module
object CoreTicketListModule {

    @TicketListFragmentScope
    @Provides
    fun provideTicketDb(app: Application): TicketsDatabase {
        return Room
            .databaseBuilder(app, TicketsDatabase::class.java, TicketsDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @TicketListFragmentScope
    @Provides
    fun provideTicketDAO(ticketDatabase: TicketsDatabase): TicketDao {
        return ticketDatabase.ticketDao()
    }

    @TicketListFragmentScope
    @Provides
    fun provideTicketCacheDataSourceImpl(ticketDao: TicketDao): TicketsCacheDataSource {
        return TicketsCacheDataSourceImpl(ticketDao)
    }

}
