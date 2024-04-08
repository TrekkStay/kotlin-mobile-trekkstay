package com.trekkstay.hotel.feature.hotel.presentation.states.hotel

sealed class HotelState {
    object Idle : HotelState()

    object CreateHotelCalling : HotelState()
    data class InvalidCreateHotel(val message: String) : HotelState()
    object SuccessCreateHotel : HotelState()


    data class AuthenticateError(val message: String) : HotelState()
}