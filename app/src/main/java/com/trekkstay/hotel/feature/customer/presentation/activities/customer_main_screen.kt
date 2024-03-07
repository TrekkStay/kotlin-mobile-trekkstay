package com.trekkstay.hotel.feature.customer.presentation.activities

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.trekkstay.hotel.config.router.CustomerRouter

import com.trekkstay.hotel.feature.customer.presentation.fragments.CustomerBotNav

@Composable
fun CustomerMainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { CustomerBotNav(navController = navController) }
    ) { _ ->
        CustomerRouter(navController)
    }
}
