package com.trekkstay.hotel.feature.main.presentation.fragments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.trekkstay.hotel.ui.theme.TrekkStayCyan

@Composable
fun BotNavBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val bottomBarIndicatorColor = NavigationBarItemDefaults.colors(
        unselectedIconColor = Color(0xFFB8B8B9),
        unselectedTextColor = Color(0xFFB8B8B9),
        selectedIconColor = Color.White,
        selectedTextColor = TrekkStayCyan,
        indicatorColor = TrekkStayCyan
    )
    NavigationBar(
        containerColor = Color.White
    ) {
        NavigationBarItem(
            label = { Text("Home") },
            selected = currentDestination?.hierarchy?.any {
                it.route == "customer_home"
            } == true,
            onClick = {
                navController.navigate("customer_home") {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            },
            icon = {
                Icon(
                    Icons.Default.Home,
                    contentDescription = "Home",
                    modifier = Modifier.size(30.dp)
                )
            },
            colors = bottomBarIndicatorColor
        )
        NavigationBarItem(
            label = { Text("Reservations") },
            selected = currentDestination?.hierarchy?.any {
                it.route == "customer_reservations"
            } == true,
            onClick = {
                navController.navigate("customer_reservations") {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            },
            icon = {
                Icon(
                    Icons.AutoMirrored.Filled.List,
                    contentDescription = "List",
                    modifier = Modifier.size(30.dp)
                )
            },
            colors = bottomBarIndicatorColor
        )
        NavigationBarItem(
            label = { Text("Notification") },
            selected = currentDestination?.hierarchy?.any {
                it.route == "customer_notifications"
            } == true,
            onClick = {
                navController.navigate("customer_notifications") {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            },
            icon = {
                Box() {
                    Icon(
                        Icons.Default.Notifications,
                        contentDescription = "Bottom Bar Notification Icon",
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
            },
            colors = bottomBarIndicatorColor
        )
        NavigationBarItem(
            label = { Text("Profile") },
            selected = currentDestination?.hierarchy?.any {
                it.route == "customer_profile"
            } == true,
            onClick = {
                navController.navigate("customer_profile") {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            },
            icon = {
                Icon(
                    Icons.Default.AccountCircle,
                    contentDescription = "Bottom Bar Profile Icon",
                    modifier = Modifier.size(30.dp)
                )
            },
            colors = bottomBarIndicatorColor
        )
    }
}