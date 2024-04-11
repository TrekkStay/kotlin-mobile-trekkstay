package com.trekkstay.hotel.feature.hotel.presentation.states.hotel

import com.trekkstay.hotel.feature.hotel.domain.entities.HotelList
import com.trekkstay.hotel.feature.hotel.domain.entities.LocationList

sealed class HotelState {
    object Idle : HotelState()

    object CreateHotelCalling : HotelState()
    data class InvalidCreateHotel(val message: String) : HotelState()
    object SuccessCreateHotel : HotelState()

    object GetHotelIdCalling : HotelState()
    data class InvalidGetHotelId(val message: String) : HotelState()
    data class SuccessGetHotelId(val id: String) : HotelState()

    object ViewHotelCalling : HotelState()
    data class InvalidViewHotel(val message: String) : HotelState()
    data class SuccessViewHotel(val list: HotelList) : HotelState()



    data class AuthenticateError(val message: String) : HotelState()
}