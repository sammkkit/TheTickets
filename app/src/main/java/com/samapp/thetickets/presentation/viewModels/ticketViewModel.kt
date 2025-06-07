package com.samapp.thetickets.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samapp.thetickets.data.local.model.Ticket
import com.samapp.thetickets.data.repository.TicketsRepository
import com.samapp.thetickets.presentation.components.uiStates.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ticketViewModel @Inject constructor(
    private val ticketRepository: TicketsRepository
): ViewModel() {

    private val _ticketListState  = MutableStateFlow<UiState<List<Ticket>>>(UiState.Loading)
    val ticketListState = _ticketListState.asStateFlow()

    private val _addTicketState = MutableStateFlow<UiState<Unit>>(UiState.Idle)
    val addTicketState = _addTicketState.asStateFlow()

    private val _updateTicketState = MutableStateFlow<UiState<Unit>>(UiState.Idle)
    val updateTicketState = _addTicketState.asStateFlow()

    private val _deleteTicketState = MutableStateFlow<UiState<Unit>>(UiState.Idle)
    val deleteTicketState = _addTicketState.asStateFlow()

    private val _toastMessage = MutableSharedFlow<String>()
    val toastMessage = _toastMessage.asSharedFlow()

    private val _ticketbyid = MutableStateFlow<UiState<Ticket>>(UiState.Loading)
    val ticketbyid = _ticketbyid.asStateFlow()

    init {
        GetAllTickets()
    }
    fun GetAllTickets() {
        viewModelScope.launch {
            _ticketListState.value = UiState.Loading
            try {
                val tickets = ticketRepository.getAllTickets()
                _ticketListState.value = UiState.Success(tickets)
            }catch (e:Exception){
                _ticketListState.value = UiState.Error(e.message ?: "Unknown error")
            }
        }
    }
    fun addTicket(ticket: Ticket) {
        viewModelScope.launch {
            _addTicketState.value = UiState.Loading
            try {
                ticketRepository.insertTicket(ticket)
                _addTicketState.value = UiState.Success(Unit)
                GetAllTickets()
                _toastMessage.emit("Ticket added successfully!")
            }catch (e:Exception){
                _addTicketState.value = UiState.Error(e.message ?: "Something went wrong")
                _toastMessage.emit(e.message ?: "Something went wrong")
            }
        }
    }

    fun updateTicket(ticket: Ticket){
        viewModelScope.launch {
            _updateTicketState.value = UiState.Loading
            try {
                ticketRepository.updateTicket(ticket)
                _updateTicketState.value = UiState.Success(Unit)
                GetAllTickets()
                _toastMessage.emit("Ticket Updated successfully!")
            }catch (e:Exception){
                _updateTicketState.value = UiState.Error(e.message ?: "Something went wrong")
                _toastMessage.emit(e.message ?: "Something went wrong")
            }
        }
    }

    fun deleteTicket(ticketId: Int){
        viewModelScope.launch {
            val ticket = ticketRepository.getTicketById(ticketId)
            _deleteTicketState.value = UiState.Loading
            try {
                ticketRepository.deleteTicket(ticket!!)
                _deleteTicketState.value = UiState.Success(Unit)
                GetAllTickets()
            }catch (e:Exception){
                _deleteTicketState.value = UiState.Error(e.message ?: "Something went wrong")
            }
        }
    }

    fun getTicketById(id:Int){
        viewModelScope.launch {
            _ticketbyid.value = UiState.Loading
            try {
                val ticket = ticketRepository.getTicketById(id)
                _ticketbyid.value = UiState.Success(ticket!!)
            }catch (e:Exception){
                _ticketbyid.value = UiState.Error(e.message ?: "Something went wrong")
            }
        }
    }
}