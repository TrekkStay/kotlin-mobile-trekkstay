package com.trekkstay.hotel.feature.hotel.domain.usecases.room


import com.trekkstay.hotel.core.typedef.ResultFuture
import com.trekkstay.hotel.core.usecase.UseCaseWithParams
import com.trekkstay.hotel.feature.hotel.domain.entities.Room
import com.trekkstay.hotel.feature.hotel.domain.repositories.RoomRepo

class RoomDetailUseCase(private val repository: RoomRepo) :
    UseCaseWithParams<Room, RoomDetailUseCaseParams> {

    override suspend fun call(params: RoomDetailUseCaseParams): ResultFuture<Room> {
        return repository.roomDetail(
            params.id,
        )
    }
}

data class RoomDetailUseCaseParams(val id: String)