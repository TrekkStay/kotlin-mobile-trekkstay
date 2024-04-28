package com.trekkstay.hotel.feature.reservation.presentation.states

import com.trekkstay.hotel.feature.reservation.domain.entities.GuestInfo


sealed class ReservationAction

data class CreateReservationAction(val roomId: String,
                                   val checkIn: String,
                                   val checkOut: String,
                                   val quantity: Int,
                                   val guestInfo: GuestInfo
) : ReservationAction()

