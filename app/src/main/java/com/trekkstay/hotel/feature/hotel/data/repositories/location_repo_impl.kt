package com.trekkstay.hotel.feature.hotel.data.repositories

import com.trekkstay.hotel.core.errors.ApiException
import com.trekkstay.hotel.core.errors.ApiInvalid
import com.trekkstay.hotel.core.network.response.Response
import arrow.core.right
import arrow.core.left
import com.trekkstay.hotel.core.typedef.ResultFuture
import com.trekkstay.hotel.feature.hotel.data.datasources.LocationRemoteDataSource
import com.trekkstay.hotel.feature.hotel.domain.entities.LocationList
import com.trekkstay.hotel.feature.hotel.domain.repositories.LocationRepo


class LocationRepoImpl(private val remoteDataSource: LocationRemoteDataSource) : LocationRepo {

    override suspend fun viewProvince(
    ): ResultFuture<LocationList> {
        return when (val response = remoteDataSource.viewProvince()) {
            is Response.Success -> response.data!!.right()
            is Response.Invalid -> ApiInvalid(response.message ?: "Unknown error", response.status ?: "-1").left()
            is Response.Failure -> throw ApiException(response.message ?: "Unknown error", response.status ?: "-1")
        }
    }
    override suspend fun viewDistrict(code:String
    ): ResultFuture<LocationList> {
        return when (val response = remoteDataSource.viewDistrict(code)) {
            is Response.Success -> response.data!!.right()
            is Response.Invalid -> ApiInvalid(response.message ?: "Unknown error", response.status ?: "-1").left()
            is Response.Failure -> throw ApiException(response.message ?: "Unknown error", response.status ?: "-1")
        }
    }
    override suspend fun viewWard(code:String
    ): ResultFuture<LocationList> {
        return when (val response = remoteDataSource.viewWard(code)) {
            is Response.Success -> response.data!!.right()
            is Response.Invalid -> ApiInvalid(response.message ?: "Unknown error", response.status ?: "-1").left()
            is Response.Failure -> throw ApiException(response.message ?: "Unknown error", response.status ?: "-1")
        }
    }
}


