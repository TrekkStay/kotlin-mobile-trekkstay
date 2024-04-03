package com.trekkstay.hotel.feature.authenticate.domain.entities

data class Employee(
    val name: String,
    val email: String,
    val phone: String,
    val refreshToken: String,
    val jwtToken: String
)
