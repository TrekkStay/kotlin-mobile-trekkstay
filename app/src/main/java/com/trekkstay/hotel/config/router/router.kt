package com.trekkstay.hotel.config.router

import android.os.Build
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
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
import com.trekkstay.hotel.feature.hotel.domain.entities.Hotel
import com.trekkstay.hotel.feature.hotel.domain.entities.Room
import com.trekkstay.hotel.feature.hotel.presentation.activities.BookingDetailScreen
import com.trekkstay.hotel.feature.hotel.presentation.activities.CreateEmpScreen
import com.trekkstay.hotel.feature.hotel.presentation.activities.HotelScreen
import com.trekkstay.hotel.feature.hotel.presentation.activities.CreateHotelScreen
import com.trekkstay.hotel.feature.hotel.presentation.activities.CreateRoomScreen
import com.trekkstay.hotel.feature.hotel.presentation.activities.HotelEmpListScreen
import com.trekkstay.hotel.feature.hotel.presentation.activities.HotelDetailScreen
import com.trekkstay.hotel.feature.hotel.presentation.activities.HotelHomeScreen
import com.trekkstay.hotel.feature.hotel.presentation.activities.HotelRoomManageScreen
import com.trekkstay.hotel.feature.hotel.presentation.activities.RoomDetailScreen
import com.trekkstay.hotel.feature.hotel.presentation.activities.SearchEngineScreen
import com.trekkstay.hotel.feature.hotel.presentation.states.hotel.HotelViewModel
import com.trekkstay.hotel.feature.hotel.presentation.states.location.LocationViewModel
import com.trekkstay.hotel.feature.hotel.presentation.states.room.RoomViewModel
import com.trekkstay.hotel.feature.notification.presentation.activities.CustomerNotificationScreen
import com.trekkstay.hotel.feature.notification.presentation.activities.HotelNotificationScreen
import com.trekkstay.hotel.feature.qr_scanner.QRScannerScreen
import com.trekkstay.hotel.feature.reservation.presentation.activities.CustomerReservationScreen
import com.trekkstay.hotel.feature.reservation.presentation.activities.HotelReservationScreen

object AppRouter {
    private var navController: NavHostController? = null
    private lateinit var authStateManager: AuthViewModel
    private lateinit var empAuthStateManager: EmpAuthViewModel
    private lateinit var hotelViewModel: HotelViewModel
    private lateinit var roomViewModel: RoomViewModel
    private lateinit var locationViewModel: LocationViewModel
    private lateinit var activity: ComponentActivity

    fun initialize(
        authStateManager: AuthViewModel,
        empAuthStateManager: EmpAuthViewModel,
        hotelViewModel: HotelViewModel,
        roomViewModel: RoomViewModel,
        locationViewModel: LocationViewModel,
        activity: ComponentActivity,
        navController: NavHostController
    ) {
        this.authStateManager = authStateManager
        this.empAuthStateManager = empAuthStateManager
        this.hotelViewModel = hotelViewModel
        this.roomViewModel = roomViewModel
        this.locationViewModel = locationViewModel
        this.activity = activity
        this.navController = navController
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun Navigation() {
        val navController = navController ?: rememberNavController()

        NavHost(navController = navController, startDestination = "start-up") {
            composable("start-up") {
                StartupScreen(navController = navController)
            }
            composable("login") {
                LoginScreen(authStateManager, navController = navController)
            }
            composable("emp_login") {
                EmpLoginScreen(empAuthStateManager, navController = navController)
            }
            composable("register") {
                RegisterScreen(authStateManager, navController = navController)
            }
            composable("emp_register") {
                EmpRegisterScreen(empAuthStateManager, navController = navController)
            }
            composable("customer_main") {
                CustomerMainScreen(hotelViewModel)
            }
            composable("hotel_main") {
                HotelScreen(hotelViewModel, roomViewModel, locationViewModel, activity)
            }
        }
    }

    fun navigateTo(destination: String) {
        navController?.navigate(destination)
    }
}


@Composable
fun CustomerRouter(hotelViewModel: HotelViewModel,navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "customer_home"
    ) {
        // Home Screen
        composable(route = "customer_home") {
            CustomerHomeScreen(hotelViewModel,navController)
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
            HotelHomeScreen(hotelViewModel= hotelViewModel,navController = navController)
        }
        composable(route = "booking_detail"){
            BookingDetailScreen(navController = navController)
        }
        composable(route = "hotel_reservations") {
            HotelReservationScreen()
        }
        composable(route = "hotel_notifications") {
            HotelNotificationScreen()
        }
        composable("hotel_QR") {
            QRScannerScreen(
                navController= navController,
                activity
            )
        }
        composable(route = "hotel_profile") {
            HotelProfileScreen(navController)
        }
        composable("hotel_create"){
            CreateHotelScreen(hotelViewModel,locationViewModel, navController)
        }
        composable(route = "hotel_detail/{hotelId}") { backStackEntry ->

            val id = backStackEntry.arguments?.getString("hotelId")

            if (id != null) {
                HotelDetailScreen(navController,hotelViewModel, id)
            }
        }
        composable(route = "hotel_room_manage") {
            HotelRoomManageScreen(roomViewModel,navController)
        }
        composable(route = "hotel_room_detail/{roomJson}") { backStackEntry ->
            println(backStackEntry.arguments)
            val roomJson = backStackEntry.arguments?.getString("roomJson")
            val roomType = object : TypeToken<Room>() {}.type
            val gson = Gson()
            val room = gson.fromJson<Room>(roomJson, roomType)
            RoomDetailScreen(navController, room)
        }
        composable("hotel_room_create") {
            CreateRoomScreen(hotelViewModel,roomViewModel,navController)
        }
        composable(route = "hotel_emp_list") {
            HotelEmpListScreen(navController)
        }
        composable("hotel_emp_create") {
            CreateEmpScreen(navController)
        }
    }
}