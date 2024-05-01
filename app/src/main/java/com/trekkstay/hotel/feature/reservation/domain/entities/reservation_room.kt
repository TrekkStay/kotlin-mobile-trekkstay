package com.trekkstay.hotel.feature.reservation.domain.entities

import com.trekkstay.hotel.feature.hotel.domain.entities.Media


data class ReservationRoom(
    val hotelId:String,
    val hotelName: String,
    val location: String,
    val type:String,
    val originalPrice:Int,
    val bookingPrice: Int,
    val images: Media,
)