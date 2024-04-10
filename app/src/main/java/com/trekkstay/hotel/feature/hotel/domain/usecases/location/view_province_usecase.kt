package com.trekkstay.hotel.feature.hotel.domain.usecases.location

import com.trekkstay.hotel.core.typedef.ResultFuture
import com.trekkstay.hotel.core.usecase.UseCaseWithoutParams
import com.trekkstay.hotel.feature.hotel.domain.entities.LocationList
import com.trekkstay.hotel.feature.hotel.domain.repositories.LocationRepo

class ViewProvinceUseCase(private val repository: LocationRepo) :
    UseCaseWithoutParams<LocationList> {

    override suspend fun call(): ResultFuture<LocationList> {
        return repository.viewProvince()
    }
}