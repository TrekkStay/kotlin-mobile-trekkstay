package com.trekkstay.hotel.feature.hotel.data.models

import com.trekkstay.hotel.core.typedef.DataMap
import com.trekkstay.hotel.core.typedef.DataMapContainer
import com.trekkstay.hotel.feature.authenticate.domain.entities.LoginRes
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import com.trekkstay.hotel.feature.hotel.domain.entities.Location

data class LocationModel(
    @SerializedName("code") val code: String,
    @SerializedName("name_vi") val nameVi: String,
    @SerializedName("name_en") val nameEn: String,
    @SerializedName("full_name_vi") val fullNameVi:String,
    @SerializedName("full_name_en") val fullNameEn:String,
) {
    companion object {
        fun empty(): LocationModel {
            return LocationModel(
                code = "_empty.code",
                nameVi ="_empty.name_vi",
                nameEn ="_empty.name_en",
                fullNameVi = "_empty.full_name_vi",
                fullNameEn = "_empty.full_name_en"
            )
        }

        fun fromJson(source: String): LocationModel {
            val type = object : TypeToken<Map<String, Any>>() {}.type
            val map: Map<String, Any> = Gson().fromJson(source, type)
            return fromMap(map)
        }

        fun fromMap(map: DataMap): LocationModel {
            return LocationModel(
                code = map["code"] as String,
                nameVi = map["name_vi"] as String,
                nameEn = map["name_en"] as String,
                fullNameVi = map["full_name_vi"] as String,
                fullNameEn = map["full_name_en"] as String

            )
        }
    }
}


fun LocationModel.toEntity(): Location {
    return Location(code, nameVi,nameEn,fullNameVi, fullNameEn )
}
