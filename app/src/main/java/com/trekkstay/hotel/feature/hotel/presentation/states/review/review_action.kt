package com.trekkstay.hotel.feature.hotel.presentation.states.review

sealed class ReviewAction()

data class CreateReview(
    val hotelId: String,
    val title: String,
    val typeOfTraveller: String,
    val point: Int,
    val summary: String
) : ReviewAction()
