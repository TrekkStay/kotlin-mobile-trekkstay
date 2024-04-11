package com.trekkstay.hotel.feature.hotel.presentation.states.room

import com.google.android.gms.maps.model.LatLng

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



data class ViewRoomAction(val limit:String) : RoomAction()