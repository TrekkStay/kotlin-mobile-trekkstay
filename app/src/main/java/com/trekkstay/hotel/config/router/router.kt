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
import com.trekkstay.hotel.feature.authenticate.presentation.activities.CustomerProfileScreen
import com.trekkstay.hotel.feature.authenticate.presentation.activities.EmpLoginScreen
import com.trekkstay.hotel.feature.authenticate.presentation.activities.EmpRegisterScreen
import com.trekkstay.hotel.feature.authenticate.presentation.activities.LoginScreen
import com.trekkstay.hotel.feature.authenticate.presentation.activities.RegisterScreen
import com.trekkstay.hotel.feature.authenticate.presentation.states.AuthViewModel
import com.trekkstay.hotel.feature.authenticate.presentation.states.EmpAuthViewModel
import com.trekkstay.hotel.feature.customer.presentation.activities.CustomerHomeScreen
import com.trekkstay.hotel.feature.customer.presentation.activities.CustomerMainScreen
import com.trekkstay.hotel.feature.authenticate.presentation.activities.HotelProfileScreen
import com.trekkstay.hotel.feature.authenticate.presentation.activities.StartupScreen
import com.trekkstay.hotel.feature.hotel.presentation.activities.HotelScreen
import com.trekkstay.hotel.feature.hotel.presentation.activities.CreateHotelScreen
import com.trekkstay.hotel.feature.hotel.presentation.activities.CreateRoomScreen
import com.trekkstay.hotel.feature.hotel.presentation.activities.HotelHomeScreen
import com.trekkstay.hotel.feature.hotel.presentation.activities.HotelRoomManageScreen
import com.trekkstay.hotel.feature.hotel.presentation.activities.SearchEngineScreen
import com.trekkstay.hotel.feature.hotel.presentation.states.hotel.HotelViewModel
import com.trekkstay.hotel.feature.hotel.presentation.states.location.LocationViewModel
import com.trekkstay.hotel.feature.hotel.presentation.states.room.RoomViewModel
import com.trekkstay.hotel.feature.notification.presentation.activities.CustomerNotificationScreen
import com.trekkstay.hotel.feature.qr_scanner.QRScannerScreen
import com.trekkstay.hotel.feature.reservation.presentation.activities.CustomerReservationScreen
import com.trekkstay.hotel.feature.reservation.presentation.activities.HotelReservationScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppRouter(
    authStateManager: AuthViewModel,
    empAuthStateManager: EmpAuthViewModel,
    hotelViewModel: HotelViewModel,
    roomViewModel: RoomViewModel,
    locationViewModel: LocationViewModel,
    activity: ComponentActivity,
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "start-up") {
        composable("start-up") {
            StartupScreen(navController)
        }
        composable("login") {
            LoginScreen(authStateManager,navController)
        }
        composable("emp_login") {
            EmpLoginScreen(empAuthStateManager,navController)
        }
        composable("register") {
            RegisterScreen(authStateManager,navController)
        }
        composable("emp_register") {
            EmpRegisterScreen(empAuthStateManager,navController)
        }
        composable("customer_main") {
            CustomerMainScreen()
        }
        composable("hotel_main") {
            HotelScreen(hotelViewModel ,roomViewModel,locationViewModel,activity)
        }

    }
}

@Composable
fun CustomerRouter(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "customer_home"
    ) {
        // Home Screen
        composable(route = "customer_home") {
            CustomerHomeScreen(navController)
        }
        composable(route = "customer_search_engine") {
            SearchEngineScreen(navController)
        }
        // Reservation
        composable(route = "customer_reservations") {
            CustomerReservationScreen()
        }
        // Notifications
        composable(route = "customer_notifications") {
            CustomerNotificationScreen()
        }
        // Profile
        composable(route = "customer_profile") {
            CustomerProfileScreen(navController = navController)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HotelRouter(hotelViewModel: HotelViewModel,roomViewModel: RoomViewModel,locationViewModel: LocationViewModel,navController: NavHostController,activity: ComponentActivity) {
    NavHost(
        navController = navController,
        startDestination = "hotel_home"
    ) {
        composable(route = "hotel_home") {
            HotelHomeScreen(navController = navController)
        }
        composable(route = "hotel_reservations") {
            HotelReservationScreen()
        }
        composable(route = "hotel_notifications") {
            Text("Notifications")
        }
        composable("hotel_QR") {
            QRScannerScreen(
                activity
            )
        }
        composable(route = "hotel_profile") {
            HotelProfileScreen(navController)
        }
        composable("hotel_create"){
            CreateHotelScreen(hotelViewModel,locationViewModel, navController)
        }
        composable(route = "hotel_room_manage") {
            HotelRoomManageScreen(navController)
        }
        composable("hotel_room_create") {
            CreateRoomScreen(hotelViewModel,roomViewModel,navController)
        }
    }
}