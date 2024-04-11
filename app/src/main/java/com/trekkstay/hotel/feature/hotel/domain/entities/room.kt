package com.trekkstay.hotel.feature.hotel.domain.entities

data class Room(
    val id: String,
    val hotelId:String,
    val type: String,
    val description: String,
    val quantity: Int,
    val discountRate: Int,
    val originalPrice: Int,
    val video: Media,
    val image: Media,
    val facilities: RoomFacilities
)