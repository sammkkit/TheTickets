package com.samapp.thetickets.presentation.Screens

import android.icu.util.LocaleData
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.samapp.thetickets.data.local.model.Priority
import com.samapp.thetickets.data.local.model.Ticket
import com.samapp.thetickets.presentation.components.DatePickerTextField
import com.samapp.thetickets.presentation.components.EnumDropdown
import com.samapp.thetickets.presentation.components.TicketComposable
import com.samapp.thetickets.presentation.components.uiStates.UiState
import com.samapp.thetickets.presentation.viewModels.ticketViewModel
import com.samapp.thetickets.ui.theme.Background
import com.samapp.thetickets.utils.localDateToMillis
import kotlinx.coroutines.flow.collectLatest
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date
import java.util.Locale

@Composable
fun AddTicketScreen(
    onAddTicket: (Ticket)->Unit,
    viewModel:ticketViewModel,
    onAddSuccess:()->Unit
){
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var priority by remember { mutableStateOf(Priority.MEDIUM) }
    var selectedDate by remember { mutableStateOf<LocalDate?>(LocalDate.now()) }

    val addTicketState by viewModel.addTicketState.collectAsState()

    LaunchedEffect(key1 = viewModel.toastMessage) {
        viewModel.toastMessage.collectLatest { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(Background)
    ) {
        TicketComposable(
            ticket = Ticket(
                name = name.ifEmpty { "Ticket Name" },
                description = description.ifEmpty { "Ticket Description" },
                ticketPriority = priority,
                dueDate = localDateToMillis(selectedDate)
            )
        )
        Column(modifier = Modifier.padding(16.dp)) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Ticket Name") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            EnumDropdown(
                modifier = Modifier.fillMaxWidth(),
                selected = priority,
                onSelectedChange = { priority = it }
            )
            Spacer(modifier = Modifier.height(16.dp))

            DatePickerTextField(
                selectedDate = selectedDate,
                onDateSelected = { selectedDate = it }
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    //TODO - add ticket from viewmodel
                    if(name.isBlank() || description.isBlank() || selectedDate == null){
                        Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                        return@Button
                    }
                    onAddTicket(
                        Ticket(
                            name = name.ifEmpty { "Ticket Name" },
                            description = description.ifEmpty { "Ticket Description" },
                            ticketPriority = priority,
                            dueDate = localDateToMillis(selectedDate)
                        )
                    )
                    onAddSuccess()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                if (addTicketState is UiState.Loading) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .height(24.dp)
                            .width(24.dp)
                    )
                } else {
                    Text("Add Ticket")
                }
            }
        }
    }
}