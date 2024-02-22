package com.trekkstay.hotel.feature.main.presentation.fragments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BotNavBar() {
    var selectedItem by remember { mutableIntStateOf(0) }

    NavigationBar(
        containerColor = Color.White
    ) {
        NavigationBarItem(
            label = { Text("Home") },
            selected = selectedItem == 0,
            onClick = { selectedItem = 0 },
            icon = {
                Icon(
                    Icons.Default.Home,
                    contentDescription = "Home",
                    modifier = Modifier.size(30.dp)
                )
            }
        )
        NavigationBarItem(
            label = { Text("Booking") },
            selected = selectedItem == 1,
            onClick = { selectedItem = 1 },
            icon = {
                Icon(
                    Icons.Default.List,
                    contentDescription = "List",
                    modifier = Modifier.size(30.dp)
                )
            }
        )
        NavigationBarItem(
            label = { Text("Notification") },
            selected = selectedItem == 2,
            onClick = { selectedItem = 2 },
            icon = {
                Box() {
                    Icon(
                        Icons.Default.Notifications,
                        contentDescription = "Botbar Notification",
                        modifier = Modifier.size(30.dp)
                    )
                    Text(
                        text = "18",
                        fontSize = 10.sp,
                        color = Color.White,
                        modifier = Modifier
                            .background(Color.Red, shape = CircleShape)
                            .align(Alignment.TopEnd)
                            .padding(3.dp)
                    )
                }
            }
        )
        NavigationBarItem(
            label = { Text("Profile") },
            selected = selectedItem == 3,
            onClick = { selectedItem = 3 },
            icon = {
                Icon(
                    Icons.Default.AccountCircle,
                    contentDescription = "List",
                    modifier = Modifier.size(30.dp)
                )
            }
        )
    }
}