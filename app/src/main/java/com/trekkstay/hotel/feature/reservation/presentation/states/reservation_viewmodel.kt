package com.trekkstay.hotel.feature.reservation.presentation.states

import com.trekkstay.hotel.feature.hotel.presentation.states.hotel.CreateHotelAction
import com.trekkstay.hotel.feature.hotel.presentation.states.hotel.GetHotelIdAction
import com.trekkstay.hotel.feature.hotel.presentation.states.hotel.HotelAction
import com.trekkstay.hotel.feature.hotel.presentation.states.hotel.HotelDetailAction
import com.trekkstay.hotel.feature.hotel.presentation.states.hotel.HotelState
import com.trekkstay.hotel.feature.hotel.presentation.states.hotel.SearchHotelAction
import com.trekkstay.hotel.feature.hotel.presentation.states.hotel.UpdateHotelAction
import com.trekkstay.hotel.feature.hotel.presentation.states.hotel.ViewHotelAction
import com.trekkstay.hotel.feature.hotel.presentation.states.hotel.ViewHotelNearAction

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trekkstay.hotel.feature.hotel.domain.repositories.HotelRepo
import com.trekkstay.hotel.feature.reservation.domain.repositories.ReservationRepo
import kotlinx.coroutines.launch

class ReservationViewModel(private val reservationRepo: ReservationRepo) : ViewModel() {
    private val _state = MutableLiveData<ReservationState>()
    val state: LiveData<ReservationState> = _state

    fun processAction(action: ReservationAction) {
        viewModelScope.launch {
            when (action) {

                is CreateReservationAction ->{
                    _state.postValue(ReservationState.CreateReservationCalling)
                    val result = reservationRepo.createReservation(action.roomId,action.checkIn,action.checkOut, action.quantity,action.guestInfo)
                    result.fold(
                        { failure -> _state.postValue(ReservationState.InvalidCreateReservation(failure.message)) },
                        { success->  _state.postValue(ReservationState.SuccessCreateReservation(success)) }
                    )
                }
            }
        }
    }
}


