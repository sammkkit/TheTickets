package com.samapp.thetickets.di

import android.content.Context
import androidx.room.Room
import com.samapp.thetickets.data.local.dao.TicketDao
import com.samapp.thetickets.data.local.database.TicketDatabase
import com.samapp.thetickets.data.repository.TicketsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): TicketDatabase{
        return Room.databaseBuilder(
            context.applicationContext,
            TicketDatabase::class.java,
            "tickets_database"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context):Context{
        return context
    }

    @Provides
    @Singleton
    fun provideTicketDao(database: TicketDatabase): TicketDao{
        return database.ticketDao()
    }

    @Provides
    @Singleton
    fun provideTicketRepository(ticketDao: TicketDao): TicketsRepository {
        return TicketsRepository(ticketDao)
    }
}