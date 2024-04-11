package com.trekkstay.hotel.feature.hotel.domain.usecases.room

import com.trekkstay.hotel.core.typedef.ResultFuture
import com.trekkstay.hotel.core.usecase.UseCaseWithParams
import com.trekkstay.hotel.core.usecase.UseCaseWithoutParams
import com.trekkstay.hotel.feature.hotel.domain.entities.HotelList
import com.trekkstay.hotel.feature.hotel.domain.entities.RoomList
import com.trekkstay.hotel.feature.hotel.domain.repositories.HotelRepo
import com.trekkstay.hotel.feature.hotel.domain.repositories.RoomRepo

class ViewRoomUseCase(private val repository: RoomRepo) :
    UseCaseWithParams<RoomList,ViewRoomUseCaseParams> {

    override suspend fun call(params: ViewRoomUseCaseParams): ResultFuture<RoomList> {
        return repository.viewRoom(params.hotelId)
    }
}

data class ViewRoomUseCaseParams(
    val hotelId: String,
)