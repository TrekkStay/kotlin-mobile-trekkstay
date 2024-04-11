package com.trekkstay.hotel.feature.hotel.data.models


import com.google.android.gms.maps.model.LatLng
import com.trekkstay.hotel.core.typedef.DataMap
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import com.trekkstay.hotel.feature.hotel.domain.entities.Room

data class RoomModel(
    @SerializedName("id") val id: String,
    @SerializedName("hotel_id") val hotelId: String,
    @SerializedName("type") val type: String,
    @SerializedName("description") val description: String,
    @SerializedName("quantity") val quantity: Int,
    @SerializedName("discount_rate") val discountRate: Int,
    @SerializedName("original_price") val originalPrice: Int,
    @SerializedName("videos") val videos: MediaModel,
    @SerializedName("images") val images: MediaModel,
   @SerializedName("facilities") val facilities: RoomFacilitiesModel
) {
    companion object {


        fun fromJson(source: String): RoomModel {
            val type = object : TypeToken<Map<String, Any>>() {}.type
            val map: Map<String, Any> = Gson().fromJson(source, type)

            return fromMap(map)
        }

        fun fromMap(map: DataMap): RoomModel {

            return RoomModel(
                id = map["id"] as? String ?: "",
                hotelId = map["hotel_id"] as? String ?: "",
                type = map["type"] as? String ?: "",
                description = map["description"] as? String ?: "",
                quantity = (map["quantity"] as? String)?.toInt() ?: 0,
                discountRate = (map["discount_rate"] as? String)?.toInt() ?: 0,
                originalPrice = (map["original_price"] as? String)?.toInt() ?: 0,
                videos = MediaModel.fromJson(map["videos"].toString()),
                images = MediaModel.fromJson(map["images"].toString()),
                facilities =  RoomFacilitiesModel.fromJson(map["facilities"].toString()),
            )
        }


    }
}


fun RoomModel.toEntity(): Room {
    return Room(
        id = id,
        hotelId = hotelId,
        type = type,
        description = description,
        quantity = quantity,
        discountRate = discountRate,
        originalPrice = originalPrice,
        video = videos.toEntity(),
        image = images.toEntity(),
        facilities = facilities.toEntity()
    )
}
