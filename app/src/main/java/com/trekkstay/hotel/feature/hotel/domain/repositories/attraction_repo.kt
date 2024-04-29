package com.trekkstay.hotel.feature.hotel.domain.repositories

import com.trekkstay.hotel.core.typedef.ResultFuture
import com.trekkstay.hotel.feature.hotel.domain.entities.AttractionList

interface AttractionRepo{
    suspend fun attractionList(
        id: String
    ): ResultFuture<AttractionList>
}