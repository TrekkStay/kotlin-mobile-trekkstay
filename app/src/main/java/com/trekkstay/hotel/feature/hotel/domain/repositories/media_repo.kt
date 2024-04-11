package com.trekkstay.hotel.feature.hotel.domain.repositories

import com.trekkstay.hotel.core.typedef.ResultFuture
import com.trekkstay.hotel.feature.hotel.domain.entities.Media
import okhttp3.MultipartBody

interface MediaRepo {
    suspend fun uploadMedia(
        media: List<MultipartBody.Part>,
    ): ResultFuture<Media>
}
