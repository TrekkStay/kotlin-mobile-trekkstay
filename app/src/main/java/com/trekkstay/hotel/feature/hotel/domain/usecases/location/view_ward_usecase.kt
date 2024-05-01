package com.trekkstay.hotel.feature.hotel.domain.usecases.location

import com.trekkstay.hotel.core.typedef.ResultFuture
import com.trekkstay.hotel.core.usecase.UseCaseWithParams
import com.trekkstay.hotel.feature.hotel.domain.entities.LocationList
import com.trekkstay.hotel.feature.hotel.domain.repositories.LocationRepo

class ViewWardUseCase(private val repository: LocationRepo) :
    UseCaseWithParams<LocationList, ViewWardUseCaseParams> {

    override suspend fun call(params: ViewWardUseCaseParams): ResultFuture<LocationList> {
        return repository.viewDistrict(
            params.code,
        )
    }
}
data class ViewWardUseCaseParams(val code: String)