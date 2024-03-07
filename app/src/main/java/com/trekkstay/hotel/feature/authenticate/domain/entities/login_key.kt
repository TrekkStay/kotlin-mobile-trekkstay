package com.trekkstay.hotel.feature.authenticate.domain.entities


import kotlinx.parcelize.Parcelize
import android.os.Parcelable

data class JWTKey(
    val jwtToken: String,
    val role: String
)
