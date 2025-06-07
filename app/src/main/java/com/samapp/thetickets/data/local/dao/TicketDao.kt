package com.samapp.thetickets.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.samapp.thetickets.data.local.model.Ticket

@Dao
interface TicketDao {
    @Insert
    suspend fun insertTicket(ticket: Ticket)

    @Query("SELECT * FROM ticket_table")
    suspend fun getAllTickets(): List<Ticket>

    @Delete
    suspend fun deleteTicket(ticket: Ticket)

    @Query("DELETE FROM ticket_table")
    suspend fun deleteAllTickets()

    @Query("SELECT * FROM ticket_table WHERE id = :id")
    suspend fun getTicketById(id: Int): Ticket?

    @Update
    suspend fun updateTicket(ticket: Ticket)

    @Query("DELETE FROM ticket_table WHERE name = :name")
    suspend fun deleteTicketById(name: String)

    @Query("SELECT * FROM ticket_table ORDER BY dueDate ASC")
    suspend fun getTicketsSortedByDueDate(): List<Ticket>

    @Query("SELECT * FROM ticket_table ORDER BY ticketPriority DESC")
    suspend fun getTicketsSortedByPriority(): List<Ticket>

}