package com.trekkstay.hotel.feature.hotel.domain.usecases.hotel

import com.trekkstay.hotel.core.typedef.ResultFuture
import com.trekkstay.hotel.core.usecase.UseCaseWithParams
import com.trekkstay.hotel.feature.hotel.domain.entities.Hotel
import com.trekkstay.hotel.feature.hotel.domain.repositories.HotelRepo

class HotelDetailUseCase(private val repository: HotelRepo) :
    UseCaseWithParams<Hotel, HotelDetailUseCaseParams> {

    override suspend fun call(params: HotelDetailUseCaseParams): ResultFuture<Hotel> {
        return repository.hotelDetail(
            params.id,
        )
    }
}

data class HotelDetailUseCaseParams(val id: String)