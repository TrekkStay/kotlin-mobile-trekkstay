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
import com.trekkstay.hotel.feature.hotel.presentation.states.room.RoomViewModel
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val authStateManager: AuthViewModel by inject()
    private val empAuthStateManager: EmpAuthViewModel by inject()
    private val hotelViewModel: HotelViewModel by inject()
    private val roomViewModel: RoomViewModel by inject()
    private val locationViewModel: LocationViewModel by inject()

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
                this,
                navController // Pass NavController instance
            )
            AppRouter.Navigation()
        }
    }
}


