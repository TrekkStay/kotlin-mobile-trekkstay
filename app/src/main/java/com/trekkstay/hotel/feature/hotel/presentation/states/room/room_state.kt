package com.trekkstay.hotel.feature.hotel.presentation.states.room

import com.trekkstay.hotel.feature.hotel.domain.entities.Room
import com.trekkstay.hotel.feature.hotel.domain.entities.RoomList

sealed class RoomState {
    object Idle : RoomState()

    object CreateRoomCalling : RoomState()
    data class InvalidCreateRoom(val message: String) : RoomState()
    object SuccessCreateRoom : RoomState()

    object UpdateRoomCalling : RoomState()
    data class InvalidUpdateRoom(val message: String) : RoomState()
    object SuccessUpdateRoom : RoomState()

    object ViewRoomCalling : RoomState()
    data class InvalidViewRoom(val message: String) : RoomState()
    data class SuccessViewRoom(val roomList: RoomList) : RoomState()

    object GetHotelRoomCalling : RoomState()
    data class InvalidGetHotelRoom(val message: String) : RoomState()
    data class SuccessGetHotelRoom(val id: String) : RoomState()

    object RoomDetailCalling : RoomState()
    data class InvalidRoomDetail(val message: String) : RoomState()
    data class SuccessRoomDetail(val room: Room) : RoomState()

    data class AuthenticateError(val message: String) : RoomState()
}