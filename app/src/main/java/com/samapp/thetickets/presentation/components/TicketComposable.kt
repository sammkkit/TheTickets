package com.samapp.thetickets.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.samapp.thetickets.data.local.model.Priority
import com.samapp.thetickets.data.local.model.Ticket
import com.samapp.thetickets.ui.theme.OnPrimary
import com.samapp.thetickets.ui.theme.OnSurface
import com.samapp.thetickets.ui.theme.Surface
import com.samapp.thetickets.ui.theme.accentOrangeColor
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import androidx.compose.ui.graphics.Outline
//import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.samapp.thetickets.utils.ZigZagLeftShape
import com.samapp.thetickets.utils.priorityToColor



@Composable
fun TicketComposable(
    ticket: Ticket,
    onClick:(Int)->Unit = {}
){
    val color = priorityToColor(ticket.ticketPriority)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 20.dp,
                vertical = 12.dp
            )
            .height(200.dp)
            .clickable { onClick(ticket.id) }
            .drawBehind {
                val strokeWidth = 1.dp.toPx()
                val color = Color.Gray

                // Top border
                drawLine(
                    color = color,
                    start = Offset( (size.width * 0.20f), 0f),
                    end = Offset(size.width, 0f),
                    strokeWidth = strokeWidth
                )
                // Right border
                drawLine(
                    color = color,
                    start = Offset(size.width , 0f),
                    end = Offset(size.width , size.height),
                    strokeWidth = strokeWidth
                )
                // Bottom border
                drawLine(
                    color = color,
                    start = Offset((size.width * 0.20f) , size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = strokeWidth
                )
            },
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
    ){
        Row{
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.21f)
                    .clip(ZigZagLeftShape())
                    .background(color)

                    ,
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "TICKET",
                    color = OnPrimary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.graphicsLayer {
                        rotationZ = -90f
                    }
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.79f)
                    .background(OnPrimary)
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Text(
                    text = ticket.name,
                    color = Surface,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val formattedDate = ticket.dueDate?.let {
                        SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(Date(it)).uppercase(Locale.getDefault())
                    } ?: "N/A"
                    val formattedTime = ticket.dueDate?.let {
                        SimpleDateFormat("hh:mm a", Locale.getDefault()).format(Date(it)).uppercase(Locale.getDefault())
                    } ?: "N/A"

                    Text(
                        text = formattedDate,
                        color = Color.DarkGray,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = formattedTime,
                        color = Color.DarkGray,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))

                // Description / Venue
                Text(
                    text = ticket.description,
                    color = Color.Gray,
                    fontSize = 14.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom // Align text to bottom if space is available
                ) {
                    Text(
                        text = "PRIORITY: ${ticket.ticketPriority.name}",
                        color = Color.Gray,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "ID: ${ticket.id.toString().padStart(5, '0')}", // Example ticket ID format
                        color = Color.Gray,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }

}
@Preview
@Composable
fun TicketComposablePreview(){
    TicketComposable(
        Ticket(
            id = 1,
            name = "Ticket Name",
            description = "Ticket Description",
            ticketPriority = Priority.HIGH,
            dueDate = System.currentTimeMillis() + 3 * 24 * 60 * 60 * 1000
        )
    )
}