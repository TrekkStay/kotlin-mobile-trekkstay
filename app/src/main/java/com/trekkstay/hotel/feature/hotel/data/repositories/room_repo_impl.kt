package com.trekkstay.hotel.feature.hotel.data.repositories

import com.trekkstay.hotel.core.errors.ApiException
import com.trekkstay.hotel.core.errors.ApiInvalid
import com.trekkstay.hotel.core.network.response.Response
import arrow.core.right
import arrow.core.left
import com.google.android.gms.maps.model.LatLng
import com.trekkstay.hotel.core.typedef.ResultFuture
import com.trekkstay.hotel.core.typedef.ResultVoid
import com.trekkstay.hotel.feature.hotel.data.datasources.RoomRemoteDataSource
import com.trekkstay.hotel.feature.hotel.domain.entities.Hotel
import com.trekkstay.hotel.feature.hotel.domain.entities.Room
import com.trekkstay.hotel.feature.hotel.domain.entities.RoomList
import com.trekkstay.hotel.feature.hotel.domain.repositories.RoomRepo


class RoomRepoImpl(private val remoteDataSource: RoomRemoteDataSource) : RoomRepo {

    override suspend fun createRoom(
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
    ): ResultVoid {
        return when (val response = remoteDataSource.createRoom(
            hotelId,
            type,
            description,
            airConditioner,
            balcony,
            bathTub,
            hairDryer,
            kitchen,
            nonSmoking,
            shower,
            slippers,
            television,
            numberOfBed,
            roomSize,
            adults,
            children,
            view,
            videos,
            images,
            quantities,
            discountRate,
            originalPrice
        )) {
            is Response.Success -> Unit.right()
            is Response.Invalid -> ApiInvalid(response.message ?: "Unknown error", response.status ?: "-1").left()
            is Response.Failure -> throw ApiException(response.message ?: "Unknown error", response.status ?: "-1")
        }
    }
    override suspend fun viewRoom(
        hotelId: String,
    ): ResultFuture<RoomList>{
        return when (val response = remoteDataSource.viewRoom(
            hotelId,
        ) ){
            is Response.Success -> response.data!!.right()
            is Response.Invalid -> ApiInvalid(response.message ?: "Unknown error", response.status ?: "-1").left()
            is Response.Failure -> throw ApiException(response.message ?: "Unknown error", response.status ?: "-1")
        }
    }
    override suspend fun getHotelRoom(): ResultFuture<String>{
        return when (val response = remoteDataSource.getHotelRoom())
        {
            is Response.Success -> response.data!!.right()
            is Response.Invalid -> ApiInvalid(response.message ?: "Unknown error", response.status ?: "-1").left()
            is Response.Failure -> throw ApiException(response.message ?: "Unknown error", response.status ?: "-1")
        }

    }
    override suspend fun roomDetail(id:String): ResultFuture<Room>{
        return when (val response = remoteDataSource.roomDetail(id))
        {
            is Response.Success -> response.data!!.right()
            is Response.Invalid -> ApiInvalid(response.message ?: "Unknown error", response.status ?: "-1").left()
            is Response.Failure -> throw ApiException(response.message ?: "Unknown error", response.status ?: "-1")
        }

    }

}


