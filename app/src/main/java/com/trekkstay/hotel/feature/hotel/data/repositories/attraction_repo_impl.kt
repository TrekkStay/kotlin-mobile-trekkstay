package com.trekkstay.hotel.feature.hotel.data.repositories

import arrow.core.left
import arrow.core.right
import com.trekkstay.hotel.core.errors.ApiException
import com.trekkstay.hotel.core.errors.ApiInvalid
import com.trekkstay.hotel.core.network.response.Response
import com.trekkstay.hotel.core.typedef.ResultFuture
import com.trekkstay.hotel.feature.hotel.data.datasources.AttractionRemoteDataSource
import com.trekkstay.hotel.feature.hotel.domain.entities.AttractionList
import com.trekkstay.hotel.feature.hotel.domain.repositories.AttractionRepo

class AttractionRepoImpl(private val remoteDataSource: AttractionRemoteDataSource) : AttractionRepo {
    override suspend fun attractionList(id:String): ResultFuture<AttractionList>{
        return when (val response = remoteDataSource.attractionList(id))
        {
            is Response.Success -> response.data!!.right()
            is Response.Invalid -> ApiInvalid(response.message ?: "Unknown error", response.status ?: "-1").left()
            is Response.Failure -> throw ApiException(response.message ?: "Unknown error", response.status ?: "-1")
        }

    }
}
