package com.trekkstay.hotel.feature.hotel.presentation.states.room

sealed class RoomAction
data class CreateRoomAction(
    val hotelId: String,
    val type: String,
    val description: String,
    val airConditioner: Boolean,
    val balcony: Boolean,
    val bathTub: Boolean,
    val hairDryer: Boolean,
    val kitchen: Boolean,
    val nonSmoking: Boolean,
    val shower: Boolean,
    val slippers: Boolean,
    val television: Boolean,
    val numberOfBed: Int,
    val roomSize: Int,
    val adults: Int,
    val children: Int,
    val view: String,
    val videos: List<String>,
    val images: List<String>,
    val quantities: Int,
    val discountRate: Int,
    val originalPrice: Int
) : RoomAction()

data class UpdateRoomAction(
    val id: String,
    val hotelId: String,
    val type: String,
    val description: String,
    val airConditioner: Boolean,
    val balcony: Boolean,
    val bathTub: Boolean,
    val hairDryer: Boolean,
    val kitchen: Boolean,
    val nonSmoking: Boolean,
    val shower: Boolean,
    val slippers: Boolean,
    val television: Boolean,
    val numberOfBed: Int,
    val roomSize: Int,
    val adults: Int,
    val children: Int,
    val view: String,
    val videos: List<String>,
    val images: List<String>,
    val quantities: Int,
    val discountRate: Int,
    val originalPrice: Int
) : RoomAction()


data class ViewRoomAction(val hotelId: String) : RoomAction()

object GetHotelRoomAction : RoomAction()

data class RoomDetailAction(
    val id: String
) : RoomAction()
