package com.trekkstay.hotel.feature.hotel.data.models


import com.trekkstay.hotel.core.typedef.DataMap
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import com.trekkstay.hotel.feature.hotel.domain.entities.Destination

data class DestinationModel(
    @SerializedName("code") val code: String,
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("unit") val unit:String,
) {
    companion object {
        fun empty(): DestinationModel {
            return DestinationModel(
                code = "_empty.code",
                id = "_empty.id",
                name = "_empty.name",
                unit = "_empty.unit"
            )
        }

        fun fromJson(source: String): DestinationModel {
            val type = object : TypeToken<Map<String, Any>>() {}.type
            val map: Map<String, Any> = Gson().fromJson(source, type)
            return fromMap(map)
        }

        fun fromMap(map: DataMap): DestinationModel {
            return DestinationModel(
                code = map["code"] as String,
                id = map["id"] as String,
                name = map["name"] as String,
                unit = map["unit"] as String,

            )
        }
    }
}


fun DestinationModel.toEntity(): Destination {
    return Destination(code,id,name,unit )
}
