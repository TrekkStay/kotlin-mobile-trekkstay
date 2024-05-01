package com.trekkstay.hotel.feature.reservation.domain.repositories
import com.trekkstay.hotel.core.typedef.ResultFuture
import com.trekkstay.hotel.feature.reservation.domain.entities.GuestInfo
import com.trekkstay.hotel.feature.reservation.domain.entities.Reservation
import com.trekkstay.hotel.feature.reservation.domain.entities.ReservationList

interface ReservationRepo {
    suspend fun createReservation(
        roomId: String,
        checkIn: String,
        checkOut: String,
        quantity: Int,
        guessInfo: GuestInfo,
    ): ResultFuture<Reservation>

    suspend fun listReservation(
        hotelId: String,
        status:String,
        dayPicked: String
    ): ResultFuture<ReservationList>
}