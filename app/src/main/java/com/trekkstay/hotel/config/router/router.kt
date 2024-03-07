package com.trekkstay.hotel.config.router

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.trekkstay.hotel.feature.authenticate.presentation.activities.LoginScreen
import com.trekkstay.hotel.feature.customer.presentation.activities.CustomerMainScreen
import com.trekkstay.hotel.feature.authenticate.presentation.activities.RegisterScreen
import com.trekkstay.hotel.feature.authenticate.presentation.states.AuthViewModel
import com.trekkstay.hotel.feature.customer.presentation.activities.CustomerHomeScreen
import com.trekkstay.hotel.feature.hotel.presentation.activities.HotelScreen
import com.trekkstay.hotel.feature.hotel.presentation.activities.SearchEngineScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppRouter(authStateManager: AuthViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "hotel_main") {
        composable("login") {
            LoginScreen()
        }
        composable("register") {
            RegisterScreen(authStateManager)
        }
        composable("customer_main") {
            CustomerMainScreen()
        }
        composable("hotel_main") {
            HotelScreen()
        }
    }
}
