package com.trekkstay.hotel.feature.hotel.domain.usecases.search

import com.trekkstay.hotel.core.typedef.ResultFuture
import com.trekkstay.hotel.core.usecase.UseCaseWithParams
import com.trekkstay.hotel.core.usecase.UseCaseWithoutParams
import com.trekkstay.hotel.feature.hotel.domain.entities.LocationList
import com.trekkstay.hotel.feature.hotel.domain.repositories.SearchRepo

class ViewDestinationUseCase(private val repository: SearchRepo) :
    UseCaseWithoutParams<LocationList> {

    override suspend fun call(): ResultFuture<LocationList> {
        return repository.viewDestination()
    }
}
