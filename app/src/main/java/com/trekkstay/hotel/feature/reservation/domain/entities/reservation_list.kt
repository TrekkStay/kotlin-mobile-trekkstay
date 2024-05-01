package com.trekkstay.hotel.feature.reservation.domain.entities


data class ReservationList(
    val sendState: String,
    val reservationList: List<Reservation>
)