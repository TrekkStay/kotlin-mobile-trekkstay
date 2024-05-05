package com.trekkstay.hotel.feature.hotel.presentation.states.review

import com.trekkstay.hotel.feature.hotel.domain.entities.ReviewList


sealed class ReviewState {
    object Idle : ReviewState()
    object CreateReviewCalling : ReviewState()
    data class InvalidCreateReview(val message: String) : ReviewState()
    object SuccessCreateReview : ReviewState()

    object ReviewListCalling : ReviewState()
    data class InvalidReviewList(val message: String) : ReviewState()
    data class SuccessReviewList(val reviewList: ReviewList) : ReviewState()

}