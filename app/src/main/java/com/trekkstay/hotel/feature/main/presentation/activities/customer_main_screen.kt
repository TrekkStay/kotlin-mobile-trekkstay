package com.trekkstay.hotel.feature.main.presentation.activities

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.trekkstay.hotel.feature.main.presentation.fragments.BotNavBar
import com.trekkstay.hotel.feature.reservation.presentation.activities.CustomerReservationScreen
import com.trekkstay.hotel.feature.search.presentation.activities.SearchEngineScreen

@Composable
fun CustomerMainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BotNavBar(navController = navController) }
    ) { _ ->
        NavHost(
            navController = navController,
            startDestination = "customer_home"
        ) {
            composable(route = "customer_home") {
                CustomerHomeScreen(navController)
            }
            composable(route = "customer_reservations") {
                CustomerReservationScreen()
            }
            composable(route = "customer_notifications") {
                Text("Notifications")
            }
            composable(route = "customer_profile") {
                Text("Settings")
            }
            composable(route = "customer_search_engine") {
                SearchEngineScreen(navController)
            }
        }
    }
}
