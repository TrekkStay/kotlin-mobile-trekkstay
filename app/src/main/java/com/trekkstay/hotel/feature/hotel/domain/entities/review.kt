package com.trekkstay.hotel.feature.hotel.domain.entities

data class Review(
    val id:String,
    val userId: String,
    val hotelId: String,
    val title: String,
    val typeOfTraveler: String,
    val point: Int,
    val summary: String,
    val user: UserReview
)