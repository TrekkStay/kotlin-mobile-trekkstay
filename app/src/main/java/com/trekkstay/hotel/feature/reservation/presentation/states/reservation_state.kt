package com.trekkstay.hotel.feature.reservation.presentation.states

import com.trekkstay.hotel.feature.hotel.presentation.states.hotel.HotelState
import com.trekkstay.hotel.feature.reservation.domain.entities.Reservation
import com.trekkstay.hotel.feature.reservation.domain.entities.ReservationList

sealed class ReservationState {
    object CreateReservationCalling : ReservationState()
    data class InvalidCreateReservation(val message: String) : ReservationState()
    data class SuccessCreateReservation(val reservation: Reservation) : ReservationState()

    object ListReservationCalling : ReservationState()
    data class InvalidListReservation(val message: String) : ReservationState()
    data class SuccessListReservation(val reservation: ReservationList) : ReservationState()
}