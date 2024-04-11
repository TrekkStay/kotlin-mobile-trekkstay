package com.trekkstay.hotel.feature.hotel.data.repositories


import com.trekkstay.hotel.core.errors.ApiException
import com.trekkstay.hotel.core.errors.ApiInvalid
import com.trekkstay.hotel.core.network.response.Response
import arrow.core.right
import arrow.core.left
import com.google.android.gms.maps.model.LatLng
import com.trekkstay.hotel.core.typedef.ResultFuture
import com.trekkstay.hotel.core.typedef.ResultVoid
import com.trekkstay.hotel.feature.hotel.data.datasources.HotelRemoteDataSource
import com.trekkstay.hotel.feature.hotel.domain.repositories.HotelRepo


class HotelRepoImpl(private val remoteDataSource: HotelRemoteDataSource) : HotelRepo {

    override suspend fun createHotel(
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
    ): ResultVoid {
        return when (val response = remoteDataSource.createHotel(
            name,
            description,
            airportTransfer,
            conferenceRoom,
            fitnessCenter,
            foodService,
            freeWifi,
            laundryService,
            motorBikeRental,
            parkingArea,
            spaService,
            swimmingPool,
            coordinates,
            videos,
            images,
            email,
            phone,
            checkInTime,
            checkOutTime,
            provinceCode,
            districtCode,
            wardCode,
            addressDetail
        )) {
            is Response.Success -> Unit.right()
            is Response.Invalid -> ApiInvalid(response.message ?: "Unknown error", response.status ?: "-1").left()
            is Response.Failure -> throw ApiException(response.message ?: "Unknown error", response.status ?: "-1")
        }
    }

    override suspend fun getHotelId(): ResultFuture<String>{
        return when (val response = remoteDataSource.getHotelId())
        {
            is Response.Success -> response.data!!.right()
            is Response.Invalid -> ApiInvalid(response.message ?: "Unknown error", response.status ?: "-1").left()
            is Response.Failure -> throw ApiException(response.message ?: "Unknown error", response.status ?: "-1")
        }

    }
}


