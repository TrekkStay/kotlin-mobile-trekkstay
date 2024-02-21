package com.trekkstay.hotel.feature.authenticate.domain.entities

import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class VerifyKey(
    val key: String
) : Parcelable