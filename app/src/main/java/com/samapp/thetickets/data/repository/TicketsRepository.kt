package com.samapp.thetickets.data.repository

import com.samapp.thetickets.data.local.dao.TicketDao
import com.samapp.thetickets.data.local.model.Ticket
import javax.inject.Inject

class TicketsRepository @Inject constructor(
    private val ticketDao: TicketDao
) {
    suspend fun insertTicket(ticket: Ticket) {
        ticketDao.insertTicket(ticket)
    }
    suspend fun getAllTickets(): List<Ticket> {
        return ticketDao.getAllTickets()
    }
    suspend fun deleteTicket(ticket: Ticket) {
        ticketDao.deleteTicket(ticket)
    }
    suspend fun deleteAllTickets() {
        ticketDao.deleteAllTickets()
    }
    suspend fun getTicketById(id: Int): Ticket? {
        return ticketDao.getTicketById(id)
    }
    suspend fun updateTicket(ticket: Ticket) {
        ticketDao.updateTicket(ticket)
    }
    suspend fun deleteTicketById(name: String) {
        ticketDao.deleteTicketById(name)
    }
    suspend fun getTicketsSortedByDueDate(): List<Ticket> {
        return ticketDao.getTicketsSortedByDueDate()
    }
    suspend fun getTicketsSortedByPriority(): List<Ticket> {
        return ticketDao.getTicketsSortedByPriority()
    }
}