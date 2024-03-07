package com.trekkstay.hotel.feature.hotel.presentation.activities

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.trekkstay.hotel.feature.hotel.presentation.fragments.HotelBotNav

@Composable
fun HotelScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { HotelBotNav(navController = navController) }
    ) { _ ->
        NavHost(
            navController = navController,
            startDestination = "hotel_home"
        ) {
            composable(route = "hotel_home") {
                Text("Hotel Home")
            }
            composable(route = "hotel_reservations") {
                Text("Hotel Reservations")
            }
            composable(route = "hotel_QR") {
                Text("Hotel Scan")
            }
            composable(route = "hotel_notifications") {
                Text("Notifications")
            }
            composable(route = "hotel_profile") {
                Text("Settings")
            }
        }
    }
}