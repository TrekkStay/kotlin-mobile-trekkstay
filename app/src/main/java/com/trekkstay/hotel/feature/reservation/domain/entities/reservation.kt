package com.trekkstay.hotel.feature.reservation.domain.entities

import android.provider.MediaStore.Audio.Media
import com.trekkstay.hotel.feature.hotel.domain.entities.Room

data class Reservation(
    val id:String,
    val roomId:String,
    val userId: String,
    val qrCodeUrl: String,
    val quantity:Double,
    val totalPrice: Double,
    val checkIn:String,
    val checkOut:String,
    val status: String,
    val guestInfo: GuestInfo,
    val room: ReservationRoom,
)