package com.trekkstay.hotel.feature.hotel.data.repositories


import com.trekkstay.hotel.core.errors.ApiException
import com.trekkstay.hotel.core.errors.ApiInvalid
import com.trekkstay.hotel.core.network.response.Response
import arrow.core.right
import arrow.core.left
import com.trekkstay.hotel.core.typedef.ResultFuture
import com.trekkstay.hotel.feature.hotel.data.datasources.MediaRemoteDataSource
import com.trekkstay.hotel.feature.hotel.domain.entities.Media
import com.trekkstay.hotel.feature.hotel.domain.repositories.MediaRepo
import okhttp3.MultipartBody
import java.io.File


class MediaRepoImpl(private val remoteDataSource: MediaRemoteDataSource) : MediaRepo {

    override suspend fun uploadMedia(
        media: List<File>,
        extensions: List<String>
    ): ResultFuture<Media> {
        return when (val response = remoteDataSource.uploadMedia(media,extensions)) {
            is Response.Success -> response.data!!.right()
            is Response.Invalid -> ApiInvalid(response.message ?: "Unknown error", response.status ?: "-1").left()
            is Response.Failure -> throw ApiException(response.message ?: "Unknown error", response.status ?: "-1")
        }
    }
}


