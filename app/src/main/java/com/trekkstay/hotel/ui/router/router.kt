package com.trekkstay.hotel.ui.router

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.trekkstay.hotel.feature.authenticate.presentation.activities.LoginScreen
import com.trekkstay.hotel.feature.main.presentation.activities.CustomerMainScreen
import com.trekkstay.hotel.feature.search.presentation.activities.SearchEngineScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppRouter() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "customer_main") {
        composable("login") {
            LoginScreen()
        }
        composable("customer_main") {
            CustomerMainScreen()
        }
    }
}