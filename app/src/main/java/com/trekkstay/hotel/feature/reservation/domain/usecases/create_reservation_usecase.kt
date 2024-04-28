package com.trekkstay.hotel.feature.reservation.domain.usecases

import com.trekkstay.hotel.core.typedef.ResultFuture
import com.trekkstay.hotel.core.usecase.UseCaseWithParams
import com.trekkstay.hotel.feature.reservation.domain.entities.GuestInfo
import com.trekkstay.hotel.feature.reservation.domain.entities.Reservation
import com.trekkstay.hotel.feature.reservation.domain.repositories.ReservationRepo

class CreateReservationUseCase(private val repository: ReservationRepo) :
    UseCaseWithParams<Reservation, CreateReservationUseCaseParams> {

    override suspend fun call(params: CreateReservationUseCaseParams): ResultFuture<Reservation> {
        return repository.createReservation(
            params.roomId,
            params.checkIn,
            params.checkOut,
            params.quantity,
            params.guessInfo,
        )
    }
}

data class CreateReservationUseCaseParams(val roomId: String,
    val checkIn: String,
    val checkOut: String,
    val quantity: Int,
    val guessInfo: GuestInfo
)