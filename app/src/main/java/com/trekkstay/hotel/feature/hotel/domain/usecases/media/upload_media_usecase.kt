package com.trekkstay.hotel.feature.hotel.domain.usecases.media


import com.trekkstay.hotel.core.typedef.ResultFuture
import com.trekkstay.hotel.core.usecase.UseCaseWithParams
import com.trekkstay.hotel.feature.hotel.domain.entities.Media
import com.trekkstay.hotel.feature.hotel.domain.repositories.MediaRepo
import okhttp3.MultipartBody

class UploadMediaUseCase(private val repository: MediaRepo) :
    UseCaseWithParams<Media, UploadMediaUseCaseParams> {

    override suspend fun call(params: UploadMediaUseCaseParams): ResultFuture<Media> {
        return repository.uploadMedia(
            params.media,
        )
    }
}
data class UploadMediaUseCaseParams(val media:List<MultipartBody.Part> )