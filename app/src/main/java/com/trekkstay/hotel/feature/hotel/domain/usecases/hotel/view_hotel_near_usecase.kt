package com.trekkstay.hotel.feature.hotel.domain.usecases.hotel

import com.google.android.gms.maps.model.LatLng
import com.trekkstay.hotel.core.typedef.ResultFuture
import com.trekkstay.hotel.core.usecase.UseCaseWithParams
import com.trekkstay.hotel.feature.hotel.domain.entities.Hotel
import com.trekkstay.hotel.feature.hotel.domain.entities.HotelList
import com.trekkstay.hotel.feature.hotel.domain.repositories.HotelRepo

class ViewHotelNearUseCase(private val repository: HotelRepo) :
    UseCaseWithParams<List<Hotel>,ViewHotelNearUseCaseParams> {

    override suspend fun call(params: ViewHotelNearUseCaseParams): ResultFuture<List<Hotel>> {
        return repository.viewHotelNear(params.coordinate,params.maxRange)
    }
}
data class ViewHotelNearUseCaseParams(val coordinate: LatLng, val maxRange: Double)