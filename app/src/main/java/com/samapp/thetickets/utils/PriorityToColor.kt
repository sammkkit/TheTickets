package com.samapp.thetickets.utils

import androidx.compose.ui.graphics.Color
import com.samapp.thetickets.data.local.model.Priority
import com.samapp.thetickets.ui.theme.accentOrangeColor

fun priorityToColor(priority: Priority):Color{
    return when(priority){
        Priority.HIGH ->  Color.Red
        Priority.MEDIUM ->  accentOrangeColor
        Priority.LOW ->  Color(0xFF81a832)
    }
}