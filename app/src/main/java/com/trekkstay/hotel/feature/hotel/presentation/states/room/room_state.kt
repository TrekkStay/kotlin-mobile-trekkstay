package com.trekkstay.hotel.feature.hotel.presentation.states.room

import com.trekkstay.hotel.feature.hotel.domain.entities.RoomList
import com.trekkstay.hotel.feature.hotel.presentation.states.hotel.HotelState

sealed class RoomState {
    object Idle : RoomState()

    object CreateRoomCalling : RoomState()
    data class InvalidCreateRoom(val message: String) : RoomState()
    object SuccessCreateRoom : RoomState()

    object ViewRoomCalling : RoomState()
    data class InvalidViewRoom(val message: String) : RoomState()
    data class SuccessViewRoom(val roomList: RoomList) : RoomState()

    object GetHotelRoomCalling : RoomState()
    data class InvalidGetHotelRoom(val message: String) : RoomState()
    data class SuccessGetHotelRoom(val id: String) : RoomState()


    data class AuthenticateError(val message: String) : RoomState()
}