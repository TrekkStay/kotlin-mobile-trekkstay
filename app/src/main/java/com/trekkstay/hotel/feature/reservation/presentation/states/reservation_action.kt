package com.trekkstay.hotel.feature.reservation.presentation.states

import com.trekkstay.hotel.feature.reservation.domain.entities.GuestInfo
import java.util.Date


sealed class ReservationAction

data class CreateReservationAction(val roomId: String,
                                   val checkIn: String,
                                   val checkOut: String,
                                   val quantity: Int,
                                   val guestInfo: GuestInfo
) : ReservationAction()

data class ListReservationAction(
    val hotelId: String,
    val status: String,
    val dayPicked: String
) : ReservationAction()