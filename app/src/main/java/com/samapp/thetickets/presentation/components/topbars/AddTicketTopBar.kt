package com.samapp.thetickets.presentation.components.topbars

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.samapp.thetickets.ui.theme.Background
import com.samapp.thetickets.ui.theme.OnBackground
import com.samapp.thetickets.ui.theme.OnSurface
import com.samapp.thetickets.ui.theme.Surface

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTicketTopBar(
    onBack: () -> Unit
) {
    CenterAlignedTopAppBar(
        navigationIcon = {
            IconButton(
                onClick = onBack,
                modifier = Modifier.padding(start = 4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = OnSurface
                )
            }
        },
        title = {
            Text(
                text = "Add Ticket",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = OnSurface
                )
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Surface
        )
    )
}
