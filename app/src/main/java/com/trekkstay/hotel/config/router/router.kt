package com.trekkstay.hotel.config.router

import android.os.Build
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.trekkstay.hotel.feature.authenticate.presentation.activities.CustomerEditInfoScreen
import com.trekkstay.hotel.feature.authenticate.presentation.activities.CustomerProfileScreen
import com.trekkstay.hotel.feature.authenticate.presentation.activities.CustomerResetPwScreen
import com.trekkstay.hotel.feature.authenticate.presentation.activities.EmpLoginScreen
import com.trekkstay.hotel.feature.authenticate.presentation.activities.EmpRegisterScreen
import com.trekkstay.hotel.feature.authenticate.presentation.activities.LoginScreen
import com.trekkstay.hotel.feature.authenticate.presentation.activities.RegisterScreen
import com.trekkstay.hotel.feature.authenticate.presentation.states.AuthViewModel
import com.trekkstay.hotel.feature.authenticate.presentation.states.EmpAuthViewModel
import com.trekkstay.hotel.feature.customer.presentation.activities.CustomerHomeScreen
import com.trekkstay.hotel.feature.customer.presentation.activities.CustomerMainScreen
import com.trekkstay.hotel.feature.authenticate.presentation.activities.HotelProfileScreen
import com.trekkstay.hotel.feature.authenticate.presentation.activities.HotelResetPwScreen
import com.trekkstay.hotel.feature.authenticate.presentation.activities.StartupScreen
import com.trekkstay.hotel.feature.hotel.presentation.activities.BookingDetailScreen
import com.trekkstay.hotel.feature.hotel.presentation.activities.BookingFormScreen
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
import com.trekkstay.hotel.feature.hotel.presentation.activities.UpdateHotelScreen
import com.trekkstay.hotel.feature.hotel.presentation.states.hotel.HotelViewModel
import com.trekkstay.hotel.feature.hotel.presentation.states.location.LocationViewModel
import com.trekkstay.hotel.feature.hotel.presentation.states.media.MediaViewModel
import com.trekkstay.hotel.feature.hotel.presentation.states.room.RoomViewModel
import com.trekkstay.hotel.feature.hotel.presentation.states.search.SearchViewModel
import com.trekkstay.hotel.feature.notification.presentation.activities.CustomerNotificationScreen
import com.trekkstay.hotel.feature.notification.presentation.activities.HotelNotificationScreen
import com.trekkstay.hotel.feature.qr_scanner.QRScannerScreen
import com.trekkstay.hotel.feature.reservation.presentation.activities.CustomerReservationScreen
import com.trekkstay.hotel.feature.reservation.presentation.activities.HotelReservationScreen
import com.trekkstay.hotel.feature.reservation.presentation.states.ReservationViewModel

object AppRouter {
    private var navController: NavHostController? = null
    private lateinit var authViewModel: AuthViewModel
    private lateinit var empAuthViewModel: EmpAuthViewModel
    private lateinit var hotelViewModel: HotelViewModel
    private lateinit var roomViewModel: RoomViewModel
    private lateinit var locationViewModel: LocationViewModel
    private lateinit var mediaViewModel: MediaViewModel
    private lateinit var searchViewModel: SearchViewModel
    private lateinit var reservationViewModel: ReservationViewModel
    private lateinit var activity: ComponentActivity

    fun initialize(
        authViewModel: AuthViewModel,
        empAuthViewModel: EmpAuthViewModel,
        hotelViewModel: HotelViewModel,
        roomViewModel: RoomViewModel,
        locationViewModel: LocationViewModel,
        mediaViewModel: MediaViewModel,
        searchViewModel: SearchViewModel,
        reservationViewModel: ReservationViewModel,
        activity: ComponentActivity,
        navController: NavHostController
    ) {
        this.authViewModel = authViewModel
        this.empAuthViewModel = empAuthViewModel
        this.hotelViewModel = hotelViewModel
        this.roomViewModel = roomViewModel
        this.locationViewModel = locationViewModel
        this.mediaViewModel = mediaViewModel
        this.searchViewModel = searchViewModel
        this.reservationViewModel = reservationViewModel
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
                LoginScreen(authViewModel, navController = navController)
            }
            composable("emp_login") {
                EmpLoginScreen(empAuthViewModel, navController = navController)
            }
            composable("register") {
                RegisterScreen(authViewModel, navController = navController)
            }
            composable("emp_register") {
                EmpRegisterScreen(empAuthViewModel, navController = navController)
            }
            composable("customer_main") {
                CustomerMainScreen(hotelViewModel, roomViewModel,searchViewModel,
                    reservationViewModel)
            }
            composable("hotel_main") {
                HotelScreen(
                    empAuthViewModel,hotelViewModel, roomViewModel, locationViewModel,
                    mediaViewModel, activity)
            }
        }
    }

    fun navigateTo(destination: String) {
        navController?.navigate(destination)
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CustomerRouter(hotelViewModel: HotelViewModel,roomViewModel: RoomViewModel,searchViewModel: SearchViewModel,reservationViewModel: ReservationViewModel,navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "customer_home"
    ) {
        // Home Screen
        composable(route = "customer_home") {
            CustomerHomeScreen(hotelViewModel,navController)
        }
        composable(route = "customer_search_engine") {
            SearchEngineScreen(searchViewModel,navController)
        }
        composable(route = "hotel_detail/{hotelId}") { backStackEntry ->

            val id = backStackEntry.arguments?.getString("hotelId")

            if (id != null) {
                HotelDetailScreen(navController,hotelViewModel, id)
            }
        }
        composable(route = "hotel_room_detail/{roomId}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("roomId")

            if (id != null) {
                RoomDetailScreen(navController, roomViewModel,id)
            }
        }
        composable(route = "booking_form/{roomId}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("roomId")

            if (id != null) {
                BookingFormScreen( id,
                    reservationViewModel,
                     navController
                )
            }
        }
        // Reservation
        composable(route = "customer_reservations") {
                CustomerReservationScreen(
                    reservationViewModel,
                    navController = navController
                )
        }
        // Notifications
        composable(route = "customer_notifications") {
            CustomerNotificationScreen()
        }
        // Profile
        composable(route = "customer_profile") {
            CustomerProfileScreen(navController = navController)
        }
        composable(route = "customer_reset_pw") {
            CustomerResetPwScreen(navController = navController)
        }
        composable(route = "customer_edit_info") {
            CustomerEditInfoScreen(navController = navController)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HotelRouter(empAuthViewModel: EmpAuthViewModel,hotelViewModel: HotelViewModel,roomViewModel: RoomViewModel,locationViewModel: LocationViewModel,mediaViewModel: MediaViewModel,navController: NavHostController,activity: ComponentActivity) {
    NavHost(
        navController = navController,
        startDestination = "hotel_home"
    ) {
        composable(route = "hotel_home") {
            HotelHomeScreen(navController)
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
            UpdateHotelScreen(hotelViewModel,locationViewModel, mediaViewModel,navController)
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
        composable(route = "hotel_room_detail/{roomId}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("roomId")

            if (id != null) {
                RoomDetailScreen(navController, roomViewModel,id)
            }
        }
        composable("hotel_room_create") {
            CreateRoomScreen(hotelViewModel,roomViewModel,mediaViewModel,navController)
        }
        composable(route = "hotel_emp_list") {
            HotelEmpListScreen(empAuthViewModel,navController,activity)
        }
        composable("hotel_emp_create") {
            CreateEmpScreen(empAuthViewModel,navController)
        }
        composable("hotel_reset_pw") {
            HotelResetPwScreen(navController)
        }
    }
}