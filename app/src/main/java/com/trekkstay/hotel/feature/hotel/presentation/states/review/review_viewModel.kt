package com.trekkstay.hotel.feature.hotel.presentation.states.review

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trekkstay.hotel.feature.hotel.domain.repositories.ReviewRepo
import kotlinx.coroutines.launch


class ReviewViewModel(private val reviewRepo: ReviewRepo) : ViewModel() {
    private val _state = MutableLiveData<ReviewState>()
    val state: LiveData<ReviewState> = _state

    fun processAction(action: ReviewAction) {
        viewModelScope.launch {
            when (action) {
                is CreateReview ->{
                    _state.postValue(ReviewState.CreateReviewCalling)
                    val result = reviewRepo.createReview(action.hotelId, action.title, action.typeOfTraveller, action.point, action.summary)
                    result.fold(
                        { failure -> _state.postValue(ReviewState.InvalidCreateReview(failure.message)) },
                        { success->  _state.postValue(ReviewState.SuccessCreateReview) }
                    )
                }
            }
        }
    }
}