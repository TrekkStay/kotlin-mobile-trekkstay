package com.trekkstay.hotel.feature.hotel.domain.usecases.hotel

import com.trekkstay.hotel.core.typedef.ResultFuture
import com.trekkstay.hotel.core.typedef.ResultVoid
import com.trekkstay.hotel.core.usecase.UseCaseWithoutParams
import com.trekkstay.hotel.feature.hotel.domain.entities.HotelList
import com.trekkstay.hotel.feature.hotel.domain.repositories.HotelRepo

class ViewHotelUseCase(private val repository: HotelRepo) :
    UseCaseWithoutParams<HotelList> {

    override suspend fun call(): ResultFuture<HotelList> {
        return repository.viewHotel()
    }
}