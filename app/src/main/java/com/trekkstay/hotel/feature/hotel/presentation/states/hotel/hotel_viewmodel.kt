package com.trekkstay.hotel.feature.hotel.presentation.states.hotel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trekkstay.hotel.feature.hotel.domain.repositories.HotelRepo
import kotlinx.coroutines.launch

class HotelViewModel(private val hotelRepo: HotelRepo) : ViewModel() {
    private val _state = MutableLiveData<HotelState>()
    val state: LiveData<HotelState> = _state

    fun processAction(action: HotelAction) {
        viewModelScope.launch {
            when (action) {
                is CreateHotelAction -> {
                    _state.postValue(HotelState.CreateHotelCalling)
                    val result = hotelRepo.createHotel(action.name,
                        action.description,
                        action.airportTransfer,
                        action.conferenceRoom,
                        action.fitnessCenter,
                        action.foodService,
                        action.freeWifi,
                        action.laundryService,
                        action.motorBikeRental,
                        action.parkingArea,
                        action.spaService,
                        action.swimmingPool,
                        action.coordinates,
                        action.videos,
                        action.images,
                        action.email,
                        action.phone,
                        action.checkInTime,
                        action.checkOutTime,
                        action.provinceCode,
                        action.districtCode,
                        action.wardCode,
                        action.addressDetail)
                    result.fold(
                        { failure -> _state.postValue(HotelState.InvalidCreateHotel(failure.message)) },
                        { _->  _state.postValue(HotelState.SuccessCreateHotel) }
                    )
                }
                is ViewHotelAction ->{
                    _state.postValue(HotelState.ViewHotelCalling)
                    val result = hotelRepo.viewHotel(
                        action.name,
                        action.provinceCode,
                        action.districtCode,
                        action.wardCode,
                        action.priceOrder
                    )
                    result.fold(
                        { failure -> _state.postValue(HotelState.InvalidViewHotel(failure.message)) },
                        { success->  _state.postValue(HotelState.SuccessViewHotel(success)) }
                    )
                }
                is SearchHotelAction ->{
                    _state.postValue(HotelState.SearchHotelCalling)
                    val result = hotelRepo.searchHotel(
                        action.locationCode,
                        action.priceOrder,
                        action.checkInDate,
                        action.checkOutDate,
                        action.adults,
                        action.children,
                        action.numOfRoom,
                        action.limit,
                        action.page
                    )
                    result.fold(
                        { failure -> _state.postValue(HotelState.InvalidSearchHotel(failure.message)) },
                        { success->  _state.postValue(HotelState.SuccessSearchHotel(success)) }
                    )
                }
                is GetHotelIdAction ->{
                    _state.postValue(HotelState.GetHotelIdCalling)
                    val result = hotelRepo.getHotelId()
                    result.fold(
                        { failure -> _state.postValue(HotelState.InvalidGetHotelId(failure.message)) },
                        { success->  _state.postValue(HotelState.SuccessGetHotelId(success)) }
                    )
                }
                is HotelDetailAction ->{
                    _state.postValue(HotelState.HotelDetailCalling)
                    val result = hotelRepo.hotelDetail(action.id)
                    result.fold(
                        { failure -> _state.postValue(HotelState.InvalidHotelDetail(failure.message)) },
                        { success->  _state.postValue(HotelState.SuccessHotelDetail(success)) }
                    )
                }
            }
        }
    }
}


