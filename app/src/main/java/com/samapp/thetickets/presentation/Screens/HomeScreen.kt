package com.samapp.thetickets.presentation.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.samapp.thetickets.data.local.model.Priority
import com.samapp.thetickets.data.local.model.Ticket
import com.samapp.thetickets.presentation.components.Chip.FilterChipsRow
import com.samapp.thetickets.presentation.components.Chip.TicketFilter
import com.samapp.thetickets.presentation.components.TicketComposable
import com.samapp.thetickets.presentation.components.uiStates.UiState
import com.samapp.thetickets.presentation.viewModels.ticketViewModel
import com.samapp.thetickets.ui.theme.Background
import com.samapp.thetickets.ui.theme.Surface
@Composable
fun HomeScreen(
    viewModel: ticketViewModel,
    onTicketClick: (Int) -> Unit
){

    val ticketListState by viewModel.ticketListState.collectAsState()

    var filter by remember { mutableStateOf(TicketFilter.ALL) }
    val filteredTickets = remember(ticketListState, filter) {
        if (ticketListState is UiState.Success) {
            val tickets = (ticketListState as UiState.Success<List<Ticket>>).data
            when (filter) {
                TicketFilter.ALL -> tickets
                TicketFilter.HIGH -> tickets.filter { it.ticketPriority == Priority.HIGH }
                TicketFilter.MEDIUM -> tickets.filter { it.ticketPriority == Priority.MEDIUM }
                TicketFilter.LOW -> tickets.filter { it.ticketPriority == Priority.LOW }
                TicketFilter.DATE_ASCENDING -> tickets.sortedBy { it.dueDate }
            }
        } else emptyList()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        FilterChipsRow(
            selectedFilter = filter,
            onFilterChange = { filter = it }
        )

        Spacer(modifier = Modifier.height(12.dp))

        when (ticketListState) {
            is UiState.Loading -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
            is UiState.Success -> {
                if (filteredTickets.isEmpty()) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Text(
                            text = "No tickets found.",
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                } else {
                    LazyColumn() {
                        items(filteredTickets) { ticket ->
                            TicketComposable(
                                ticket = ticket,
                                onClick = {id->
                                    onTicketClick(id)
                                }
                            )
                        }
                    }
                }
            }
            is UiState.Error -> {
                val errorMessage = (ticketListState as UiState.Error).message
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = "Error: $errorMessage",
                        color = Color.Red,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
            else -> {
                // UiState.Idle or other states if any
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = "Welcome! Please load tickets.",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}
