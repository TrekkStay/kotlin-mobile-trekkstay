package com.trekkstay.hotel.feature.hotel.domain.repositories

import com.trekkstay.hotel.core.typedef.ResultFuture
import com.trekkstay.hotel.feature.hotel.domain.entities.LocationList

interface LocationRepo {
    suspend fun viewProvince(): ResultFuture<LocationList>
    suspend fun viewDistrict(
        code: String,
    ): ResultFuture<LocationList>
    suspend fun viewWard(
        code: String,
    ): ResultFuture<LocationList>
}
