package com.trekkstay.hotel.feature.authenticate.domain.entities

data class HotelEmp(
    val id: String,
    val hotelId: String,
    val name: String,
    val email: String,
    val phone: String,
    val contract: String,
    val salary: Int,
    val role: String,
    val status: String
)
