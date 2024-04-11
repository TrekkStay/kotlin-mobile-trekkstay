package com.trekkstay.hotel.feature.hotel.presentation.states.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trekkstay.hotel.feature.hotel.domain.repositories.RoomRepo
import kotlinx.coroutines.launch

class RoomViewModel(private val hotelRepo: RoomRepo) : ViewModel() {
    private val _state = MutableLiveData<RoomState>()
    val state: LiveData<RoomState> = _state

    fun processAction(action: RoomAction) {
        viewModelScope.launch {
            when (action) {
                is CreateRoomAction -> {
                    _state.postValue(RoomState.CreateRoomCalling)
                    val result = hotelRepo.createRoom(
                        action.hotelId,
                        action.type,
                        action.description,
                        action.airConditioner,
                        action.balcony,
                        action.bathTub,
                        action.hairDryer,
                        action.kitchen,
                        action.nonSmoking,
                        action.shower,
                        action.slippers,
                        action.television,
                        action.numberOfBed,
                        action.roomSize,
                        action.adults,
                        action.children,
                        action.view,
                        action.videos,
                        action.images,
                        action.quantities,
                        action.discountRate,
                        action.originalPrice
                    )
                    result.fold(
                        { failure -> _state.postValue(RoomState.InvalidCreateRoom(failure.message)) },
                        { _->  _state.postValue(RoomState.SuccessCreateRoom) }
                    )
                }
                is ViewRoomAction ->{
                    _state.postValue(RoomState.ViewRoomCalling)
                    val result = hotelRepo.viewRoom(
                        action.hotelId,
                    )
                    result.fold(
                        { failure -> _state.postValue(RoomState.InvalidViewRoom(failure.message)) },
                        { success->  _state.postValue(RoomState.SuccessViewRoom(success)) }
                    )
                }
            }
        }
    }
}


