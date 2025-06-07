package com.samapp.thetickets.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.samapp.thetickets.data.local.TypeConvertors.PriorityConverter
import com.samapp.thetickets.data.local.dao.TicketDao
import com.samapp.thetickets.data.local.model.Ticket


@Database(
    entities = [Ticket::class],
    version = 1
)
@TypeConverters(PriorityConverter::class)
abstract class TicketDatabase:RoomDatabase() {
    abstract fun ticketDao(): TicketDao
}