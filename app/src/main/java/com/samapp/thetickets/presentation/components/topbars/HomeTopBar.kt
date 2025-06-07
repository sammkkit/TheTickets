package com.samapp.thetickets.presentation.components.topbars

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.samapp.thetickets.ui.theme.Background
import com.samapp.thetickets.ui.theme.OnBackground
import com.samapp.thetickets.ui.theme.OnSurface
import com.samapp.thetickets.ui.theme.Surface

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar() {
    CenterAlignedTopAppBar(
        modifier = Modifier.padding(),
        title = {
            Text(
                text = "My Tickets",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = OnSurface
                )
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Surface,
        )
    )
}
