package com.trekkstay.hotel

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.navigation.compose.rememberNavController
import com.trekkstay.hotel.config.router.AppRouter
import com.trekkstay.hotel.di.startKoinDependencyInjection
import com.trekkstay.hotel.feature.authenticate.presentation.states.AuthViewModel
import com.trekkstay.hotel.feature.authenticate.presentation.states.EmpAuthViewModel
import com.trekkstay.hotel.feature.hotel.presentation.states.attraction.AttractionViewModel
import com.trekkstay.hotel.feature.hotel.presentation.states.hotel.HotelViewModel
import com.trekkstay.hotel.feature.hotel.presentation.states.location.LocationViewModel
import com.trekkstay.hotel.feature.hotel.presentation.states.media.MediaViewModel
import com.trekkstay.hotel.feature.hotel.presentation.states.review.ReviewViewModel
import com.trekkstay.hotel.feature.hotel.presentation.states.room.RoomViewModel
import com.trekkstay.hotel.feature.hotel.presentation.states.search.SearchViewModel
import com.trekkstay.hotel.feature.reservation.presentation.states.CreatePaymentAction
import com.trekkstay.hotel.feature.reservation.presentation.states.ReservationViewModel
import org.koin.android.ext.android.inject
import vn.momo.momo_partner.AppMoMoLib

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
    private val reviewViewModel: ReviewViewModel by inject()

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
                attractionViewModel,
                navController,
                reviewViewModel
            )
            AppRouter.Navigation()
        }
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == AppMoMoLib.getInstance().REQUEST_CODE_MOMO && resultCode == -1) {
            if (data != null) {
                when (data.getIntExtra("status", -1)) {
                    0 -> {

                        val paymentAction = CreatePaymentAction(
                            amount = data.getStringExtra("requestId").toString()
                                .substringAfter('.'),
                            method = "MOMO",
                            reservationId = data.getStringExtra("requestId").toString()
                                .substringBefore('.'),
                            status = "SUCCESS",

                            )
                        println(paymentAction.toString())
                        reservationViewModel.processAction(paymentAction)
                        println(data.getStringExtra("requestId"))
                    }

                    1 -> { // Payment failed
                        val paymentAction = CreatePaymentAction(
                            amount = data.getStringExtra("requestId") ?: "123.1000".substringAfter(
                                '.'
                            ),
                            method = "PAY_AT_HOTEL",
                            reservationId = data.getStringExtra("requestId")
                                ?: "123.1000".substringBefore('.'),
                            status = "PENDING",

                            )
                        reservationViewModel.processAction(paymentAction)
                    }

                    2 -> {
                        val paymentAction = CreatePaymentAction(
                            amount = data.getStringExtra("requestId") ?: "123.1000".substringAfter(
                                '.'
                            ),
                            method = "PAY_AT_HOTEL",
                            reservationId = data.getStringExtra("requestId")
                                ?: "123.1000".substringBefore('.'),
                            status = "PENDING",

                            )
                        reservationViewModel.processAction(paymentAction)
                    }

                    else -> {
                        val paymentAction = CreatePaymentAction(
                            amount = data.getStringExtra("requestId") ?: "123.1000".substringAfter(
                                '.'
                            ),
                            method = "PAY_AT_HOTEL",
                            reservationId = data.getStringExtra("requestId")
                                ?: "123.1000".substringBefore('.'),
                            status = "PENDING",

                            )
                        reservationViewModel.processAction(paymentAction)
                    }
                }
            } else {
                println("main null data")
            }
        } else {
            println("main invalid")
        }
    }


}


