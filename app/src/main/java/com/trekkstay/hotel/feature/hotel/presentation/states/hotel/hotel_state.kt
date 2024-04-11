package com.trekkstay.hotel.feature.hotel.presentation.states.hotel

import com.trekkstay.hotel.feature.hotel.domain.entities.LocationList

sealed class HotelState {
    object Idle : HotelState()

    object CreateHotelCalling : HotelState()
    data class InvalidCreateHotel(val message: String) : HotelState()
    object SuccessCreateHotel : HotelState()

    object GetHotelIdCalling : HotelState()
    data class InvalidGetHotelId(val message: String) : HotelState()
    data class SuccessGetHotelId(val id: String) : HotelState()



    data class AuthenticateError(val message: String) : HotelState()
}