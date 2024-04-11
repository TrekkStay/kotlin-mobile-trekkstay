package com.trekkstay.hotel.feature.hotel.domain.usecases.hotel




import com.trekkstay.hotel.core.typedef.ResultFuture
import com.trekkstay.hotel.core.typedef.ResultVoid
import com.trekkstay.hotel.core.usecase.UseCaseWithoutParams
import com.trekkstay.hotel.feature.hotel.domain.repositories.HotelRepo

class GetHotelIdUseCase(private val repository: HotelRepo) :
    UseCaseWithoutParams<String> {

    override suspend fun call(): ResultFuture<String> {
        return repository.getHotelId()
    }
}