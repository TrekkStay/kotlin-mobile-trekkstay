package com.trekkstay.hotel.feature.hotel.domain.repositories

import com.google.android.gms.maps.model.LatLng
import com.trekkstay.hotel.core.typedef.ResultFuture
import com.trekkstay.hotel.core.typedef.ResultVoid

interface HotelRepo {
    suspend fun createHotel(
        name: String,
        description: String,
        airportTransfer: Boolean,
        conferenceRoom: Boolean,
        fitnessCenter: Boolean,
        foodService: Boolean,
        freeWifi: Boolean,
        laundryService: Boolean,
        motorBikeRental: Boolean,
        parkingArea: Boolean,
        spaService: Boolean,
        swimmingPool: Boolean,
        coordinates: LatLng,
        videos: List<String>,
        images: List<String>,
        email: String,
        phone: String,
        checkInTime: String,
        checkOutTime: String,
        provinceCode: String,
        districtCode: String,
        wardCode: String,
        addressDetail: String
    ): ResultVoid

    suspend fun getHotelId(): ResultFuture<String>
}
