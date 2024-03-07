package com.trekkstay.hotel.feature.authenticate.domain.entities

import kotlinx.parcelize.Parcelize
import android.os.Parcelable

data class VerifyKey(
    val key: String
)