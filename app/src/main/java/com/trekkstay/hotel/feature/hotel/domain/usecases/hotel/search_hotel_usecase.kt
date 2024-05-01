package com.trekkstay.hotel.feature.hotel.domain.usecases.hotel

import com.trekkstay.hotel.core.typedef.ResultFuture
import com.trekkstay.hotel.core.usecase.UseCaseWithParams
import com.trekkstay.hotel.feature.hotel.domain.entities.HotelList
import com.trekkstay.hotel.feature.hotel.domain.repositories.HotelRepo

class SearchHotelUseCase(private val repository: HotelRepo) :
    UseCaseWithParams<HotelList,SearchHotelUseCaseParams> {

    override suspend fun call(params: SearchHotelUseCaseParams): ResultFuture<HotelList> {
        return repository.searchHotel(params.locationCode,params.priceOrder,params.checkInDate,params.checkOutDate,params.adults,params.children,params.numOfRoom,params.limit,params.page)
    }
}
data class SearchHotelUseCaseParams(val locationCode: String?, val priceOrder: String?,val checkInDate: String?,val checkOutDate: String?,val adults: Int?,val children: Int?,val numOfRoom: Int?,val limit: Int?,val page: Int?)