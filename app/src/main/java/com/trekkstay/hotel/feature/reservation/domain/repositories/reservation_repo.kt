package com.trekkstay.hotel.feature.reservation.domain.repositories
import com.trekkstay.hotel.core.typedef.ResultFuture
import com.trekkstay.hotel.feature.reservation.domain.entities.GuestInfo
import com.trekkstay.hotel.feature.reservation.domain.entities.Reservation

interface ReservationRepo {
    suspend fun createReservation(
        roomId: String,
        checkIn: String,
        checkOut: String,
        quantity: Int,
        guessInfo: GuestInfo,
    ): ResultFuture<Reservation>
}
