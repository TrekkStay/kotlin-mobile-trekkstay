package com.example.hotel.feature.authenticate.data.models

import com.example.hotel.core.typedef.DataMapContainer
import com.example.hotel.core.typedef.DataMap
import com.example.hotel.feature.authenticate.domain.entities.VerifyKey
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class VerifyKeyModel(
    @SerializedName("key") val key: String
) {
    companion object {
        fun empty(): VerifyKeyModel {
            return VerifyKeyModel(
                key = "_empty.verifyKey"
            )
        }

        fun fromJson(source: String): VerifyKeyModel {
            val map = Gson().fromJson(source, DataMapContainer::class.java)
            return fromMap(map.data)
        }

        private fun fromMap(map: DataMap): VerifyKeyModel {
            return VerifyKeyModel(
                key = map["token"] as String
            )
        }
    }
}

fun VerifyKeyModel.toVerifyKey(): VerifyKey {
    return VerifyKey(key)
}
