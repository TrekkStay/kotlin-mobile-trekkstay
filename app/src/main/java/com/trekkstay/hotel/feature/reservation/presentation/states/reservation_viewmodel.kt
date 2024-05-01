package com.trekkstay.hotel.feature.reservation.presentation.states

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

                is ListReservationAction -> {
                    _state.postValue(ReservationState.ListReservationCalling)
                    val result = reservationRepo.listReservation(action.hotelId, action.status, action.dayPicked)
                    result.fold(
                        { failure -> _state.postValue(ReservationState.InvalidListReservation(failure.message)) },
                        { success->  _state.postValue(ReservationState.SuccessListReservation(success)) }
                    )
                }

                is ViewDetailReservationAction -> {
                    _state.postValue(ReservationState.ViewDetailReservationCalling)
                    val result = reservationRepo.viewDetailReservation(action.reservationId)
                    result.fold(
                        { failure -> _state.postValue(ReservationState.InvalidViewDetailReservation(failure.message)) },
                        { success->  _state.postValue(ReservationState.SuccessViewDetailReservation(success)) }
                    )
                }
            }
        }
    }
}

