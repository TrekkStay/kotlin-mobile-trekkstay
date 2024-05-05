package com.trekkstay.hotel.feature.hotel.presentation.states.review

sealed class ReviewState {
    object Idle : ReviewState()
    object CreateReviewCalling : ReviewState()
    data class InvalidCreateReview(val message: String) : ReviewState()
    object SuccessCreateReview : ReviewState()

}