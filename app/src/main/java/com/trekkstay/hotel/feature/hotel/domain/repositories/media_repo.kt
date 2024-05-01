package com.trekkstay.hotel.feature.hotel.domain.repositories

import com.trekkstay.hotel.core.typedef.ResultFuture
import com.trekkstay.hotel.feature.hotel.domain.entities.Media
import okhttp3.MultipartBody
import java.io.File

interface MediaRepo {
    suspend fun uploadMedia(
        media: List<File>,
        extensions: List<String>
    ): ResultFuture<Media>
}
