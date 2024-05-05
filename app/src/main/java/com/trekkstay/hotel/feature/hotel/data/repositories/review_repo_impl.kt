package com.trekkstay.hotel.feature.hotel.data.repositories

import arrow.core.left
import arrow.core.right
import com.trekkstay.hotel.core.errors.ApiException
import com.trekkstay.hotel.core.errors.ApiInvalid
import com.trekkstay.hotel.core.network.response.Response
import com.trekkstay.hotel.core.typedef.ResultFuture
import com.trekkstay.hotel.core.typedef.ResultVoid
import com.trekkstay.hotel.feature.hotel.data.datasources.ReviewRemoteDataSource
import com.trekkstay.hotel.feature.hotel.domain.entities.AttractionList
import com.trekkstay.hotel.feature.hotel.domain.entities.ReviewList
import com.trekkstay.hotel.feature.hotel.domain.repositories.ReviewRepo

class ReviewRepoImpl(private val remoteDataSource: ReviewRemoteDataSource) :
    ReviewRepo {
    override suspend fun createReview(
        hotelId: String,
        title: String,
        typeOfTraveller: String,
        point: Int,
        summary: String
    ): ResultVoid {
        return when (val response = remoteDataSource.createReview(hotelId, title, typeOfTraveller,point, summary))
        {
            is Response.Success -> Unit.right()
            is Response.Invalid -> ApiInvalid(response.message ?: "Unknown error", response.status ?: "-1").left()
            is Response.Failure -> throw ApiException(response.message ?: "Unknown error", response.status ?: "-1")
        }

    }

    override suspend fun reviewList(hotelId: String): ResultFuture<ReviewList>{
        return when (val response = remoteDataSource.reviewList(hotelId))
        {
            is Response.Success -> response.data!!.right()
            is Response.Invalid -> ApiInvalid(response.message ?: "Unknown error", response.status ?: "-1").left()
            is Response.Failure -> throw ApiException(response.message ?: "Unknown error", response.status ?: "-1")
        }

    }
}
