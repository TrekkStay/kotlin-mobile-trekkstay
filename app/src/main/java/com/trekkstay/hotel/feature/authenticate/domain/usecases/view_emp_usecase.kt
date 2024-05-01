package com.trekkstay.hotel.feature.authenticate.domain.usecases

import com.trekkstay.hotel.core.typedef.ResultFuture
import com.trekkstay.hotel.core.usecase.UseCaseWithParams
import com.trekkstay.hotel.feature.authenticate.domain.entities.HotelEmpList
import com.trekkstay.hotel.feature.authenticate.domain.repositories.AuthRepo


class ViewEmpUseCase(private val repository: AuthRepo) :
    UseCaseWithParams<HotelEmpList, ViewEmpUseCaseParams> {

    override suspend fun call(params: ViewEmpUseCaseParams): ResultFuture<HotelEmpList> {
        return repository.viewEmp(
            params.hotelId,
        )
    }
}

data class ViewEmpUseCaseParams(val hotelId: String)