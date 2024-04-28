package com.trekkstay.hotel

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.navigation.compose.rememberNavController
import com.trekkstay.hotel.di.startKoinDependencyInjection
import com.trekkstay.hotel.config.router.AppRouter
import com.trekkstay.hotel.feature.authenticate.presentation.states.AuthViewModel
import com.trekkstay.hotel.feature.authenticate.presentation.states.EmpAuthViewModel
import com.trekkstay.hotel.feature.hotel.presentation.states.hotel.HotelViewModel
import com.trekkstay.hotel.feature.hotel.presentation.states.location.LocationViewModel
import com.trekkstay.hotel.feature.hotel.presentation.states.media.MediaViewModel
import com.trekkstay.hotel.feature.hotel.presentation.states.room.RoomViewModel
import com.trekkstay.hotel.feature.hotel.presentation.states.search.SearchViewModel
import com.trekkstay.hotel.feature.reservation.presentation.states.ReservationViewModel
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val authStateManager: AuthViewModel by inject()
    private val empAuthStateManager: EmpAuthViewModel by inject()
    private val hotelViewModel: HotelViewModel by inject()
    private val roomViewModel: RoomViewModel by inject()
    private val locationViewModel: LocationViewModel by inject()
    private val mediaViewModel: MediaViewModel by inject()
    private val searchViewModel: SearchViewModel by inject()
    private val reservationViewModel: ReservationViewModel by inject()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startKoinDependencyInjection(this)

        setContent {
            val navController = rememberNavController()
            AppRouter.initialize(
                authStateManager,
                empAuthStateManager,
                hotelViewModel,
                roomViewModel,
                locationViewModel,
                mediaViewModel,
                searchViewModel,
                reservationViewModel,
                this,
                navController
            )
            AppRouter.Navigation()
        }
    }
}


