package com.samapp.thetickets.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.samapp.thetickets.ui.theme.Background
import com.samapp.thetickets.ui.theme.OnPrimary
import com.samapp.thetickets.ui.theme.OnSecondary
import com.samapp.thetickets.ui.theme.OnSurface
import com.samapp.thetickets.ui.theme.Primary
import com.samapp.thetickets.ui.theme.Secondary
import com.samapp.thetickets.ui.theme.Surface

@Composable
fun FloatingActionButtonComposable(
    onFabClick: () -> Unit
) {
    FloatingActionButton(
        onClick = onFabClick,
        containerColor = Surface,
        contentColor = OnSurface
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add Ticket"
        )
    }
}
