package com.trekkstay.hotel.feature.hotel.domain.usecases.hotel

import com.trekkstay.hotel.core.typedef.ResultFuture
import com.trekkstay.hotel.core.typedef.ResultVoid
import com.trekkstay.hotel.core.usecase.UseCaseWithParams
import com.trekkstay.hotel.core.usecase.UseCaseWithoutParams
import com.trekkstay.hotel.feature.hotel.domain.entities.HotelList
import com.trekkstay.hotel.feature.hotel.domain.repositories.HotelRepo

class ViewHotelUseCase(private val repository: HotelRepo) :
    UseCaseWithParams<HotelList,ViewHotelUseCaseParams> {

    override suspend fun call(params: ViewHotelUseCaseParams): ResultFuture<HotelList> {
        return repository.viewHotel(params.name,params.provinceCode,params.districtCode,params.wardCode,params.priceOrder)
    }
}
data class ViewHotelUseCaseParams(val name: String?,val provinceCode: String?,val districtCode: String?,val wardCode: String?, val priceOrder: String?)