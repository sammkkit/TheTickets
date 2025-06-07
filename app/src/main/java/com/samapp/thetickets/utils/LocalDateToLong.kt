package com.samapp.thetickets.utils

import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date

fun localDateToMillis(date: LocalDate?): Long {
    return date
        ?.atStartOfDay(ZoneId.systemDefault())
        ?.toInstant()
        ?.toEpochMilli() ?: 0
}

fun millisToLocalDate(millis: Long): LocalDate {
    return Instant.ofEpochMilli(millis)
        .atZone(ZoneId.systemDefault())
        .toLocalDate()
}
