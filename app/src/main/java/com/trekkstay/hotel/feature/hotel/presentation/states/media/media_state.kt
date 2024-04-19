package com.trekkstay.hotel.feature.hotel.presentation.states.media

import com.trekkstay.hotel.feature.hotel.domain.entities.Media


sealed class MediaState {
    object Idle : MediaState()

    object UploadMediaCalling : MediaState()
    data class InvalidUploadMedia(val message: String) : MediaState()
    data class SuccessUploadMedia(val media: Media) : MediaState()

    object UploadVideoCalling : MediaState()
    data class InvalidUploadVideo(val message: String) : MediaState()
    data class SuccessUploadVideo(val media: Media) : MediaState()


    data class AuthenticateError(val message: String) : MediaState()
}