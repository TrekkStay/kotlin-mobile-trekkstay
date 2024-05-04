package com.trekkstay.hotel.feature.reservation.presentation.states

import com.trekkstay.hotel.feature.reservation.domain.entities.GuestInfo


sealed class ReservationAction

data class CreateReservationAction(
    val roomId: String,
    val checkIn: String,
    val checkOut: String,
    val quantity: Int,
    val guestInfo: GuestInfo
) : ReservationAction()

data class ListReservationAction(
    val hotelId: String?,
    val status: String,
    val dayPicked: String
) : ReservationAction()

data class ViewDetailReservationAction(val reservationId: String) : ReservationAction()
data class CreatePaymentAction(
    val amount: String,
    val method: String,
    val reservationId: String,
    val status: String
) : ReservationAction()