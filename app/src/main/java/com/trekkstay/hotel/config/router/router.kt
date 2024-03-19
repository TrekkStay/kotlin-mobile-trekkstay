package com.trekkstay.hotel.config.router

import android.os.Build
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.annotation.RequiresApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.journeyapps.barcodescanner.ScanOptions
import com.trekkstay.hotel.feature.authenticate.presentation.activities.LoginScreen
import com.trekkstay.hotel.feature.authenticate.presentation.activities.RegisterScreen
import com.trekkstay.hotel.feature.authenticate.presentation.states.AuthViewModel
import com.trekkstay.hotel.feature.customer.presentation.activities.CustomerHomeScreen
import com.trekkstay.hotel.feature.customer.presentation.activities.CustomerMainScreen
import com.trekkstay.hotel.feature.hotel.presentation.activities.HotelProfileScreen
import com.trekkstay.hotel.feature.hotel.presentation.activities.HotelScreen
import com.trekkstay.hotel.feature.hotel.presentation.activities.CreateHotelScreen
import com.trekkstay.hotel.feature.hotel.presentation.activities.CreateRoomScreen
import com.trekkstay.hotel.feature.hotel.presentation.activities.SearchEngineScreen
import com.trekkstay.hotel.feature.hotel.presentation.activities.ToggleButton
import com.trekkstay.hotel.feature.qr_scanner.QRScannerScreen
import com.trekkstay.hotel.feature.reservation.presentation.activities.CustomerReservationScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppRouter(
    authStateManager: AuthViewModel,
    activity: ComponentActivity,
) {
    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = "customer_main") {
        composable("login") {
            LoginScreen(authStateManager,navController)
        }
        composable("register") {
            RegisterScreen(authStateManager,navController)
        }
        composable("customer_main") {
            CustomerMainScreen()
        }
        composable("hotel_main") {
            HotelScreen()
        }
        composable("create_hotel"){
            CreateHotelScreen()
        }
        composable("test"){
            ToggleButton("Air Conditioner")
        }
        composable("hotel_create_room") {
            CreateRoomScreen()
        }
        composable("qr_scanner") {
            QRScannerScreen(
                activity
            )
        }
    }
}

@Composable
fun CustomerRouter(navController: NavHostController) {
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

@Composable
fun HotelRouter(navController: NavHostController) {
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
            HotelProfileScreen()
        }
    }
}