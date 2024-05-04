package com.trekkstay.hotel.feature.reservation.domain.entities

data class Payment(
    val reservationId: String,
    val amount: Int,
    val userId: String,
    val method: String,
    val status: String
    
)