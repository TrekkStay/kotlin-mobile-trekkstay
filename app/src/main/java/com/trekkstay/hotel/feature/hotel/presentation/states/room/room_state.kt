package com.trekkstay.hotel.feature.hotel.presentation.states.room

sealed class RoomState {
    object Idle : RoomState()

    object CreateRoomCalling : RoomState()
    data class InvalidCreateRoom(val message: String) : RoomState()
    object SuccessCreateRoom : RoomState()


    data class AuthenticateError(val message: String) : RoomState()
}