package com.samapp.thetickets.presentation.Screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.samapp.thetickets.data.local.model.Ticket
import com.samapp.thetickets.presentation.components.TicketDetailTicketComposable
import com.samapp.thetickets.presentation.components.uiStates.UiState
import com.samapp.thetickets.presentation.viewModels.ticketViewModel

@Composable
fun TicketDetailsScreen(
    ticketId: Int,
    viewModel: ticketViewModel
) {

    val ticketState by viewModel.ticketbyid.collectAsState()
    val ticket = (ticketState as? UiState.Success<Ticket>)?.data
    LaunchedEffect(ticketId) {
        viewModel.getTicketById(ticketId)
        Log.d("TicketDetailsScreen", "Ticket: $ticket")
    }


    if (ticket != null) {
        TicketDetailTicketComposable(ticket)
    } else {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Loading...")
        }
    }
}