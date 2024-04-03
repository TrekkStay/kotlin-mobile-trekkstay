package com.trekkstay.hotel.config.router

import android.os.Build
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.trekkstay.hotel.feature.authenticate.presentation.activities.EmpLoginScreen
import com.trekkstay.hotel.feature.authenticate.presentation.activities.LoginScreen
import com.trekkstay.hotel.feature.authenticate.presentation.activities.RegisterScreen
import com.trekkstay.hotel.feature.authenticate.presentation.states.AuthViewModel
import com.trekkstay.hotel.feature.authenticate.presentation.states.EmpAuthViewModel
import com.trekkstay.hotel.feature.customer.presentation.activities.CustomerHomeScreen
import com.trekkstay.hotel.feature.customer.presentation.activities.CustomerMainScreen
import com.trekkstay.hotel.feature.hotel.presentation.activities.HotelProfileScreen
import com.trekkstay.hotel.feature.hotel.presentation.activities.HotelScreen
import com.trekkstay.hotel.feature.hotel.presentation.activities.CreateHotelScreen
import com.trekkstay.hotel.feature.hotel.presentation.activities.CreateRoomScreen
import com.trekkstay.hotel.feature.hotel.presentation.activities.HotelRoomManageScreen
import com.trekkstay.hotel.feature.hotel.presentation.activities.SearchEngineScreen
import com.trekkstay.hotel.feature.hotel.presentation.activities.ToggleButton
import com.trekkstay.hotel.feature.qr_scanner.QRScannerScreen
import com.trekkstay.hotel.feature.reservation.presentation.activities.CustomerReservationScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppRouter(
    authStateManager: AuthViewModel,
    empAuthStateManager: EmpAuthViewModel,
    activity: ComponentActivity,
) {
    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = "emp_login") {

        composable("login") {
            LoginScreen(authStateManager,navController)
        }
        composable("emp_login") {
            EmpLoginScreen(empAuthStateManager,navController)
        }
        composable("register") {
            RegisterScreen(authStateManager,navController)
        }
        composable("customer_main") {
            CustomerMainScreen()
        }
        composable("hotel_main") {
            HotelScreen(activity)
        }
        composable("create_hotel"){
            CreateHotelScreen()
        }
        composable("test"){
            ToggleButton("Air Conditioner")
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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HotelRouter(navController: NavHostController,activity: ComponentActivity,) {
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
        composable(route = "hotel_notifications") {
            Text("Notifications")
        }
        composable("hotel_QR") {
            QRScannerScreen(
                activity
            )
        }
//      Hotel Profile Screens
        composable(route = "hotel_profile") {
            HotelProfileScreen(navController)
        }
        composable(route = "hotel_room_manage") {
            HotelRoomManageScreen(navController)
        }
        composable("hotel_room_create") {
            CreateRoomScreen(navController)
        }
    }
}