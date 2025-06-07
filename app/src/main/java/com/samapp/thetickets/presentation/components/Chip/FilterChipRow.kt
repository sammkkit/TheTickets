package com.samapp.thetickets.presentation.components.Chip

import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FilterChipsRow(
    selectedFilter: TicketFilter,
    onFilterChange: (TicketFilter) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        TicketFilter.entries.forEach { filter ->
            val label = when (filter) {
                TicketFilter.ALL -> "All"
                TicketFilter.HIGH -> "High"
                TicketFilter.MEDIUM -> "Medium"
                TicketFilter.LOW -> "Low"
                TicketFilter.DATE_ASCENDING -> "Date ⬆\uFE0F"
            }
            FilterChipItem(label, selectedFilter == filter) {
                onFilterChange(filter)
            }
        }
    }
}
