package com.example.hotel.feature.authenticate.domain.entities


import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class JWTKey(
    val jwtToken: String,
    val role: String
) : Parcelable
