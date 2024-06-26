package com.trekkstay.hotel.feature.customer.presentation.activities

import android.os.Build
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.trekkstay.hotel.config.router.CustomerRouter
import com.trekkstay.hotel.feature.authenticate.presentation.states.AuthViewModel
import com.trekkstay.hotel.feature.customer.presentation.fragments.CustomerBotNav
import com.trekkstay.hotel.feature.hotel.presentation.states.attraction.AttractionViewModel
import com.trekkstay.hotel.feature.hotel.presentation.states.hotel.HotelViewModel
import com.trekkstay.hotel.feature.hotel.presentation.states.review.ReviewViewModel
import com.trekkstay.hotel.feature.hotel.presentation.states.room.RoomViewModel
import com.trekkstay.hotel.feature.hotel.presentation.states.search.SearchViewModel
import com.trekkstay.hotel.feature.reservation.presentation.states.ReservationViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CustomerMainScreen(
    hotelViewModel: HotelViewModel,
    roomViewModel: RoomViewModel,
    searchViewModel: SearchViewModel,
    reservationViewModel: ReservationViewModel,
    attractionViewModel: AttractionViewModel,
    authViewModel: AuthViewModel,
    activity: ComponentActivity,
    reviewViewModel: ReviewViewModel
) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { CustomerBotNav(navController = navController) }
    ) { _ ->
        CustomerRouter(
            hotelViewModel,
            roomViewModel,
            searchViewModel,
            reservationViewModel,
            navController,
            attractionViewModel,
            authViewModel,
            activity,
            reviewViewModel
        )
    }
}
