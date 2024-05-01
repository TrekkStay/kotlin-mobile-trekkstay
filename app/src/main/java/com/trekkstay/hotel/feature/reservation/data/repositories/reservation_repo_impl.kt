package com.trekkstay.hotel.feature.reservation.data.repositories

import com.trekkstay.hotel.core.errors.ApiException
import com.trekkstay.hotel.core.errors.ApiInvalid
import com.trekkstay.hotel.core.network.response.Response
import arrow.core.right
import arrow.core.left
import com.trekkstay.hotel.core.typedef.ResultFuture
import com.trekkstay.hotel.feature.reservation.data.datasources.ReservationRemoteDataSource
import com.trekkstay.hotel.feature.reservation.domain.entities.GuestInfo
import com.trekkstay.hotel.feature.reservation.domain.entities.Reservation
import com.trekkstay.hotel.feature.reservation.domain.entities.ReservationList
import com.trekkstay.hotel.feature.reservation.domain.repositories.ReservationRepo

class ReservationRepoImpl(private val remoteDataSource: ReservationRemoteDataSource) :
    ReservationRepo {

    override suspend fun createReservation(
        roomId: String,
        checkIn: String,
        checkOut: String,
        quantity: Int,
        guessInfo: GuestInfo,
    ): ResultFuture<Reservation> {
        return when (val response = remoteDataSource.createReservation(
            roomId,
            checkIn, checkOut, quantity, guessInfo
        )) {
            is Response.Success -> response.data!!.right()
            is Response.Invalid -> ApiInvalid(
                response.message ?: "Unknown error",
                response.status ?: "-1"
            ).left()

            is Response.Failure -> throw ApiException(
                response.message ?: "Unknown error",
                response.status ?: "-1"
            )
        }
    }

    override suspend fun listReservation(
        hotelId: String,
        status: String,
        dayPicked: String
    ): ResultFuture<ReservationList> {
        return when (val response = remoteDataSource.listReservation(
            hotelId, status, dayPicked
        )) {
            is Response.Success -> response.data!!.right()
            is Response.Invalid -> ApiInvalid(
                response.message ?: "Unknown error",
                response.status ?: "-1"
            ).left()

            is Response.Failure -> throw ApiException(
                response.message ?: "Unknown error",
                response.status ?: "-1"
            )
        }
    }
}

