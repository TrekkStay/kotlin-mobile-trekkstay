package com.trekkstay.hotel.feature.hotel.presentation.activities

import android.os.Build
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.trekkstay.hotel.config.router.HotelRouter
import com.trekkstay.hotel.feature.hotel.presentation.fragments.HotelBotNav
import com.trekkstay.hotel.feature.hotel.presentation.states.hotel.HotelViewModel
import com.trekkstay.hotel.feature.hotel.presentation.states.location.LocationViewModel
import com.trekkstay.hotel.feature.hotel.presentation.states.media.MediaViewModel
import com.trekkstay.hotel.feature.hotel.presentation.states.room.RoomViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HotelScreen(hotelViewModel: HotelViewModel,roomViewModel: RoomViewModel,locationViewModel: LocationViewModel,mediaViewModel: MediaViewModel,activity: ComponentActivity) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { HotelBotNav(navController = navController) }
    ) { _ ->
        HotelRouter(hotelViewModel,roomViewModel,locationViewModel,mediaViewModel,navController,activity)
    }
}