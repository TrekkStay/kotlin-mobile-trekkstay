package com.trekkstay.hotel.feature.hotel.domain.usecases.location

import com.trekkstay.hotel.core.typedef.ResultFuture
import com.trekkstay.hotel.core.usecase.UseCaseWithParams
import com.trekkstay.hotel.feature.hotel.domain.entities.LocationList
import com.trekkstay.hotel.feature.hotel.domain.repositories.LocationRepo

class ViewDistrictUseCase(private val repository: LocationRepo) :
    UseCaseWithParams<LocationList, ViewDistrictUseCaseParams> {

    override suspend fun call(params: ViewDistrictUseCaseParams): ResultFuture<LocationList> {
        return repository.viewDistrict(
            params.code,
        )
    }
}
data class ViewDistrictUseCaseParams(val code: String)