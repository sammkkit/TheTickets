package com.samapp.thetickets.presentation.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object AddTicket : Screen("add_ticket")
    data class TicketDetails(val ticketId: Int) : Screen("ticket_details/{ticketId}") {
        fun createRoute() = "ticket_details/$ticketId"
    }
    data class EditTicket(val ticketId: Int) : Screen("edit_ticket/{ticketId}") {
        fun createRoute() = "edit_ticket/$ticketId"
    }
}
