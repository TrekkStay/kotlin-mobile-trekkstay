package com.trekkstay.hotel.feature.hotel.presentation.activities

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.trekkstay.hotel.config.router.HotelRouter
import com.trekkstay.hotel.feature.hotel.presentation.fragments.HotelBotNav

@Composable
fun HotelScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { HotelBotNav(navController = navController) }
    ) { _ ->
        HotelRouter(navController)
    }
}