package com.trekkstay.hotel.feature.hotel.domain.entities

data class HotelList(
    val limit: Int,
    val page: Int,
    val hotelList: List<Hotel>,
)