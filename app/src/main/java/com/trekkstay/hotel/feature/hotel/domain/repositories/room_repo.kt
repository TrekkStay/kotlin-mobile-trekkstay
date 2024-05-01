package com.trekkstay.hotel.feature.hotel.domain.repositories


import com.trekkstay.hotel.core.typedef.ResultFuture
import com.trekkstay.hotel.core.typedef.ResultVoid
import com.trekkstay.hotel.feature.hotel.domain.entities.Hotel
import com.trekkstay.hotel.feature.hotel.domain.entities.Room
import com.trekkstay.hotel.feature.hotel.domain.entities.RoomList

interface RoomRepo {
    suspend fun createRoom(
        hotelId: String,
        type: String,
        description: String,
        airConditioner: Boolean,
        balcony: Boolean,
        bathTub: Boolean,
        hairDryer: Boolean,
        kitchen: Boolean,
        nonSmoking: Boolean,
        shower: Boolean,
        slippers: Boolean,
        television: Boolean,
        numberOfBed: Int,
        roomSize: Int,
        adults: Int,
        children: Int,
        view: String,
        videos: List<String>,
        images: List<String>,
        quantities: Int,
        discountRate: Int,
        originalPrice: Int
    ): ResultVoid

    suspend fun viewRoom(
        hotelId: String,
    ): ResultFuture<RoomList>

    suspend fun getHotelRoom(): ResultFuture<String>

    suspend fun roomDetail(
        id:String
    ): ResultFuture<Room>
}

