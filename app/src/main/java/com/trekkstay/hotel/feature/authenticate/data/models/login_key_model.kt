package com.example.hotel.feature.authenticate.data.models

import com.example.hotel.core.typedef.DataMap
import com.example.hotel.core.typedef.DataMapContainer
import com.example.hotel.feature.authenticate.domain.entities.JWTKey
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class JWTKeyModel(
    @SerializedName("jwtToken") val jwtToken: String,
    @SerializedName("role") val role: String
) {
    companion object {
        fun empty(): JWTKeyModel {
            return JWTKeyModel(
                jwtToken = "_empty.jwtToken",
                role = "_empty.role"
            )
        }

        fun fromJson(source: String): JWTKeyModel {
            val map = Gson().fromJson(source, DataMapContainer::class.java)
            return fromMap(map.data)
        }

        private fun fromMap(map: DataMap): JWTKeyModel {
            return JWTKeyModel(
                jwtToken = map["token"] as String,
                role = map["role"] as String
            )
        }
    }
}

fun JWTKeyModel.toJWTKey(): JWTKey {
    return JWTKey(jwtToken, role)
}