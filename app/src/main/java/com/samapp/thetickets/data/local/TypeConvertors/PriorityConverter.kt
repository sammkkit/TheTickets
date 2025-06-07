package com.samapp.thetickets.data.local.TypeConvertors

import androidx.room.TypeConverter
import com.samapp.thetickets.data.local.model.Priority

class PriorityConverter {
    @TypeConverter
    fun fromPriority(priority: Priority): Int = priority.level

    @TypeConverter
    fun toPriority(level: Int): Priority = Priority.entries.first { it.level == level }
}