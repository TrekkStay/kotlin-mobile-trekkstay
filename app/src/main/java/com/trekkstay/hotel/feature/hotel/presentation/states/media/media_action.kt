package com.trekkstay.hotel.feature.hotel.presentation.states.media

import java.io.File


sealed class MediaAction
data class UploadMediaAction(
    val media: List<File>,
    val extension: List<String>
) : MediaAction()

data class UploadVideoAction(
    val media: List<File>,
    val extension: List<String>
) : MediaAction()

