package com.samapp.thetickets.presentation.components

import androidx.compose.runtime.Composable
import com.samapp.thetickets.data.local.model.Ticket
import com.samapp.thetickets.utils.priorityToColor

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.samapp.thetickets.ui.theme.Background
import com.samapp.thetickets.ui.theme.OnPrimary
import com.samapp.thetickets.ui.theme.OnSurface
import com.samapp.thetickets.ui.theme.Secondary
import com.samapp.thetickets.ui.theme.Surface
import com.samapp.thetickets.utils.ZigZagTopShape
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun TicketDetailTicketComposable(
    ticket: Ticket
) {
    val color = priorityToColor(ticket.ticketPriority)
    val formattedDate = ticket.dueDate?.let {
        SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(Date(it))
            .uppercase(Locale.getDefault())
    } ?: "N/A"
    val formattedTime = ticket.dueDate?.let {
        SimpleDateFormat("hh:mm a", Locale.getDefault()).format(Date(it))
            .uppercase(Locale.getDefault())
    } ?: "N/A"

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Secondary),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            colors = CardDefaults.cardColors(
                containerColor = OnSurface,
                contentColor = Surface
            )
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {

                // ZigZag Header
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .clip(ZigZagTopShape())
                        .background(color),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "TICKET DETAILS",
                        color = OnPrimary,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = 1.5.sp
                    )
                }

                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "Ticket Name",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )

                    Text(
                        text = ticket.name,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Description",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = ticket.description,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.DarkGray
                    )


                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text("DATE", color = Color.Gray, fontSize = 14.sp)
                            Text(formattedDate, color = Color.Black, fontSize = 16.sp)
                        }
                        Column {
                            Text("TIME", color = Color.Gray, fontSize = 14.sp)
                            Text(formattedTime, color = Color.Black, fontSize = 16.sp)
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "PRIORITY: ${ticket.ticketPriority.name}",
                            color = color,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "ID: ${ticket.id.toString().padStart(5, '0')}",
                            color = Color.Gray,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
    }
}



