package com.trekkstay.hotel.feature.reservation.domain.entities

import com.trekkstay.hotel.feature.hotel.domain.entities.Media


data class ReservationRoom(
    val hotelId:String,
    val type:String,
    val originalPrice:Double,
    val bookingPrice: Double,
    val images: Media,

    )