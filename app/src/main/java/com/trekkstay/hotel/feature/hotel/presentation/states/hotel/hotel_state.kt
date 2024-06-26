package com.trekkstay.hotel.feature.hotel.presentation.states.hotel

import com.trekkstay.hotel.feature.hotel.domain.entities.Hotel
import com.trekkstay.hotel.feature.hotel.domain.entities.HotelList

sealed class HotelState {
    object CreateHotelCalling : HotelState()
    data class InvalidCreateHotel(val message: String) : HotelState()
    data class SuccessCreateHotel(val id:String) : HotelState()

    object GetHotelIdCalling : HotelState()
    data class InvalidGetHotelId(val message: String) : HotelState()
    data class SuccessGetHotelId(val id: String) : HotelState()

    object UpdateHotelCalling : HotelState()
    data class InvalidUpdateHotel(val message: String) : HotelState()
    object SuccessUpdateHotel : HotelState()


    object ViewHotelCalling : HotelState()
    data class InvalidViewHotel(val message: String) : HotelState()
    data class SuccessViewHotel(val list: HotelList) : HotelState()


    object SearchHotelCalling : HotelState()
    data class InvalidSearchHotel(val message: String) : HotelState()
    data class SuccessSearchHotel(val list: HotelList) : HotelState()


    object HotelDetailCalling : HotelState()
    data class InvalidHotelDetail(val message: String) : HotelState()
    data class SuccessHotelDetail(val hotel: Hotel) : HotelState()


    object ViewHotelNearCalling : HotelState()
    data class InvalidViewHotelNear(val message: String) : HotelState()
    data class SuccessViewHotelNear(val list: List<Hotel>) : HotelState()
}