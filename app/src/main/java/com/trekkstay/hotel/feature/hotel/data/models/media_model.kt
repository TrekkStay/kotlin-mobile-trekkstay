package com.trekkstay.hotel.feature.hotel.data.models

import com.trekkstay.hotel.core.typedef.DataMap
import com.trekkstay.hotel.core.typedef.DataMapContainer
import com.trekkstay.hotel.feature.authenticate.domain.entities.LoginRes
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import com.trekkstay.hotel.feature.hotel.domain.entities.Location
import com.trekkstay.hotel.feature.hotel.domain.entities.Media

data class MediaModel(
    @SerializedName("media") val media: List<String>,
) {
    companion object {
        fun empty(): MediaModel {
            return MediaModel(
                media = emptyList(),
            )
        }

        fun fromJson(source: String): MediaModel {
            val type = object : TypeToken<Map<String, Any>>() {}.type
            val map: Map<String, Any> = Gson().fromJson(source, type)
            return fromMap(map)
        }

        private fun fromMap(map: DataMap): MediaModel {
            return MediaModel(
                media = (map["urls"] as? List<String>)?.toList() ?: emptyList()
            )
        }
    }
}


fun MediaModel.toEntity(): Media {
    return Media(media)
}
