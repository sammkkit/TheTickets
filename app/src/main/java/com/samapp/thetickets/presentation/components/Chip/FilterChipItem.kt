package com.samapp.thetickets.presentation.components.Chip

import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.samapp.thetickets.ui.theme.OnPrimary
import com.samapp.thetickets.ui.theme.OnSurface
import com.samapp.thetickets.ui.theme.Primary
import com.samapp.thetickets.ui.theme.Surface
import com.samapp.thetickets.ui.theme.Tertiary

@Composable
fun FilterChipItem(
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    FilterChip(
        selected = selected,
        onClick = onClick,
        label = { Text(label) },
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = Surface,
            containerColor = OnSurface,
            selectedLabelColor = OnSurface,
            labelColor = Surface
        )
    )
}