package com.trekkstay.hotel.feature.hotel.domain.repositories

import com.trekkstay.hotel.core.typedef.ResultVoid
import com.trekkstay.hotel.feature.hotel.domain.entities.AttractionList

interface ReviewRepo{
    suspend fun createReview(
        hotelId: String,
        title: String,
        typeOfTraveller: String,
        point: Int,
        summary: String
    ): ResultVoid
}