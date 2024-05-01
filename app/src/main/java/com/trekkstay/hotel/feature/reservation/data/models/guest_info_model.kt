package com.trekkstay.hotel.feature.reservation.data.models

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import com.trekkstay.hotel.core.typedef.DataMap
import com.trekkstay.hotel.feature.reservation.domain.entities.GuestInfo

data class GuestInfoModel(
    @SerializedName("full_name") val name: String,
    @SerializedName("contact") val contact: String,
    @SerializedName("adults") val adults: Int,
    @SerializedName("children") val children: Int,
) {
    companion object {
        fun empty(): GuestInfoModel {
            return GuestInfoModel(
                name = "_empty.name",
                contact = "_empty.contact",
                adults = 0,
                children = 0,
            )
        }

        fun fromJson(source: String): GuestInfoModel {
            val type = object : TypeToken<Map<String, Any>>() {}.type
            val map: Map<String, Any> = Gson().fromJson(source, type)
            return fromMap(map)
        }

        fun fromMap(map: DataMap): GuestInfoModel {
            return GuestInfoModel(

                name = map["full_name"] as String,
                contact = map["contact"] as String,
                adults = (map["adults"] as Double).toInt(),
                children = (map["children"] as Double).toInt()

            )
        }
    }
}


fun GuestInfoModel.toEntity(): GuestInfo {
    return GuestInfo(name,contact,adults,children)
}