package com.trekkstay.hotel.ui.router

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.trekkstay.hotel.feature.authenticate.presentation.activities.LoginScreen
import com.trekkstay.hotel.feature.main.presentation.activities.UserHomeScreen

@Composable
fun AppRouter() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "user_home") {
        composable("login") {
            LoginScreen()
        }
        composable("user_home") {
            UserHomeScreen()
        }
    }
}