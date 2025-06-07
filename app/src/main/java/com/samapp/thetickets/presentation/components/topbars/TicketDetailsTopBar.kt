package com.samapp.thetickets.presentation.components.topbars

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.samapp.thetickets.ui.theme.OnError
import com.samapp.thetickets.ui.theme.OnSurface
import com.samapp.thetickets.ui.theme.Primary
import com.samapp.thetickets.ui.theme.Surface


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicketDetailTopBar(
    onBack:()->Unit,
    onUpdate:()->Unit,
    onDelete:()->Unit
) {
    val context = LocalContext.current
    var menuExpanded by remember { mutableStateOf(false) }
    var showDeleteDialogBox by remember { mutableStateOf(false) }

    CenterAlignedTopAppBar(
        modifier = Modifier.padding(),
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
                text = "Ticket Detail",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = OnSurface
                )
            )
        },
        actions = {
            IconButton(onClick = {
                menuExpanded = !menuExpanded
            }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Menu",
                    tint = OnSurface
                )
            }
            DropdownMenu(
                expanded = menuExpanded,
                onDismissRequest = { menuExpanded = false },
                modifier = Modifier.background(OnSurface, shape = RoundedCornerShape(4.dp))
            ) {
                DropdownMenuItem(
                    text = { Text("Update Details",color = Color.Black) },
                    onClick = {
                        menuExpanded = false
                        onUpdate()
                    },
                    colors = MenuDefaults.itemColors(
                        textColor = Color.White,
                        leadingIconColor = Primary
                    )
                )
                Divider(color = Color.Gray)
                DropdownMenuItem(
                    text = { Text("Delete Tenant",color = Color.Red) },
                    onClick = {
                        menuExpanded = false
                        showDeleteDialogBox = true
                    },
                    colors = MenuDefaults.itemColors(
                        textColor = OnError,
                        leadingIconColor = OnError
                    )
                )

            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Surface,
        )
    )
    if (showDeleteDialogBox) {
        androidx.compose.material3.AlertDialog(
            onDismissRequest = { showDeleteDialogBox = false },
            title = { Text("Confirm Deletion") },
            text = { Text("Are you sure you want to delete this ticket? This action cannot be undone.") },
            confirmButton = {
                androidx.compose.material3.TextButton(
                    onClick = {
                        showDeleteDialogBox = false
                        onDelete()
                        Toast.makeText(
                            context,
                            "Ticket Deleted",
                            Toast.LENGTH_SHORT
                        ).show()
                        onBack()
                    }
                ) {
                    Text("Delete", color = Color.Red)
                }
            },
            dismissButton = {
                androidx.compose.material3.TextButton(
                    onClick = { showDeleteDialogBox = false }
                ) {
                    Text("Cancel")
                }
            }
        )
    }
}
