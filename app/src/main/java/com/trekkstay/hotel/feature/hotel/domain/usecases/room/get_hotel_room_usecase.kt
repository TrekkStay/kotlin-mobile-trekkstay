package com.trekkstay.hotel.feature.hotel.domain.usecases.room



import com.trekkstay.hotel.core.typedef.ResultFuture
import com.trekkstay.hotel.core.typedef.ResultVoid
import com.trekkstay.hotel.core.usecase.UseCaseWithoutParams
import com.trekkstay.hotel.feature.hotel.domain.repositories.RoomRepo

class GetHotelRoomUseCase(private val repository: RoomRepo) :
    UseCaseWithoutParams<String> {

    override suspend fun call(): ResultFuture<String> {
        return repository.getHotelRoom()
    }
}