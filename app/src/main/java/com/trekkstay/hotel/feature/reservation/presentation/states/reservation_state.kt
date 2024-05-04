package com.trekkstay.hotel.feature.reservation.presentation.states

import com.trekkstay.hotel.feature.reservation.domain.entities.Reservation
import com.trekkstay.hotel.feature.reservation.domain.entities.ReservationList

sealed class ReservationState {
    object CreateReservationCalling : ReservationState()
    data class InvalidCreateReservation(val message: String) : ReservationState()
    data class SuccessCreateReservation(val reservation: Reservation) : ReservationState()

    object ListReservationCalling : ReservationState()
    data class InvalidListReservation(val message: String) : ReservationState()
    data class SuccessListReservation(val sendState: String, val reservation: ReservationList) :
        ReservationState()

    object ViewDetailReservationCalling : ReservationState()
    data class InvalidViewDetailReservation(val message: String) : ReservationState()
    data class SuccessViewDetailReservation(val reservation: Reservation) : ReservationState()

    object CreatePaymentCalling : ReservationState()
    data class InvalidCreatePayment(val message: String) : ReservationState()
    object SuccessCreatePayment : ReservationState()
}