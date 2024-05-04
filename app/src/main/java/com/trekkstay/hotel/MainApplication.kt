package com.trekkstay.hotel

import android.content.Intent
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
import com.trekkstay.hotel.feature.hotel.presentation.states.attraction.AttractionViewModel
import com.trekkstay.hotel.feature.hotel.presentation.states.hotel.HotelViewModel
import com.trekkstay.hotel.feature.hotel.presentation.states.location.LocationViewModel
import com.trekkstay.hotel.feature.hotel.presentation.states.media.MediaViewModel
import com.trekkstay.hotel.feature.hotel.presentation.states.room.RoomViewModel
import com.trekkstay.hotel.feature.hotel.presentation.states.search.SearchViewModel
import com.trekkstay.hotel.feature.reservation.presentation.states.ReservationViewModel
import org.koin.android.ext.android.inject
import vn.momo.momo_partner.AppMoMoLib
import java.text.NumberFormat
import java.util.Locale

class MainActivity : ComponentActivity() {

    private val authStateManager: AuthViewModel by inject()
    private val empAuthStateManager: EmpAuthViewModel by inject()
    private val hotelViewModel: HotelViewModel by inject()
    private val roomViewModel: RoomViewModel by inject()
    private val locationViewModel: LocationViewModel by inject()
    private val mediaViewModel: MediaViewModel by inject()
    private val searchViewModel: SearchViewModel by inject()
    private val attractionViewModel: AttractionViewModel by inject()
    private val reservationViewModel: ReservationViewModel by inject()


    private var environment = 1

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startKoinDependencyInjection(this)
        AppMoMoLib.getInstance()
            .setEnvironment(AppMoMoLib.ENVIRONMENT.DEVELOPMENT);

        when (environment) {
            0 -> {
                AppMoMoLib.getInstance().setEnvironment(AppMoMoLib.ENVIRONMENT.DEBUG);
            }
            1 -> {
                AppMoMoLib.getInstance().setEnvironment(AppMoMoLib.ENVIRONMENT.DEVELOPMENT);
            }
            2 -> {
                AppMoMoLib.getInstance().setEnvironment(AppMoMoLib.ENVIRONMENT.PRODUCTION);
            }
        }

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
                attractionViewModel,
                navController,
                )
            AppRouter.Navigation()
        }
    }
    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == AppMoMoLib.getInstance().REQUEST_CODE_MOMO && resultCode == -1) {
            if (data != null) {
                val status = data.getIntExtra("status", -1)
                when (status) {
                    0 -> { // Payment success
                        val token = data.getStringExtra("data")
                        val phoneNumber = data.getStringExtra("phonenumber")

                        println("$token $phoneNumber")
                    }

                    1 -> { // Payment failed
                        val message = data.getStringExtra("message") ?: "Thất bại"
                        println(message)
                    }

                    2 -> println("Payment cancelled")
                    else -> println("Unknown error")
                }
            } else {
                println("Data is null")
            }
        } else {
            println("Invalid request")
        }
    }

}


