package com.trekkstay.hotel.feature.hotel.domain.entities

data class HotelRoom(
    val id: String,
    val hotelId:String,
    val type: String,
    val description: String,
    val quantity: Int,
    val discountRate: Int,
    val originalPrice: Int,
    val videos: List<String>,
    val images: List<String>,
)