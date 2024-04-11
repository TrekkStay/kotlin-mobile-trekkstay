package com.trekkstay.hotel.feature.hotel.presentation.states.room

import com.trekkstay.hotel.feature.hotel.domain.entities.RoomList

sealed class RoomState {
    object Idle : RoomState()

    object CreateRoomCalling : RoomState()
    data class InvalidCreateRoom(val message: String) : RoomState()
    object SuccessCreateRoom : RoomState()

    object ViewRoomCalling : RoomState()
    data class InvalidViewRoom(val message: String) : RoomState()
    data class SuccessViewRoom(val roomList: RoomList) : RoomState()


    data class AuthenticateError(val message: String) : RoomState()
}