package com.trekkstay.hotel.feature.reservation.data.models


import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import com.trekkstay.hotel.core.typedef.DataMap
import com.trekkstay.hotel.feature.hotel.data.models.MediaModel
import com.trekkstay.hotel.feature.hotel.data.models.toEntity
import com.trekkstay.hotel.feature.reservation.domain.entities.GuestInfo
import com.trekkstay.hotel.feature.reservation.domain.entities.ReservationRoom

data class ReservationRoomModel(
    @SerializedName("hotel_id") val hotelId: String,
    @SerializedName("hotel_name") val hotelName: String,
    @SerializedName("location") val location: String,
    @SerializedName("type") val type: String,
    @SerializedName("original_price") val originalPrice: Int,
    @SerializedName("booking_price") val bookingPrice: Int,
    @SerializedName("images") val images: MediaModel,
) {
    companion object {

        fun fromJson(source: String): ReservationRoomModel {
            val type = object : TypeToken<Map<String, Any>>() {}.type
            val map: Map<String, Any> = Gson().fromJson(source, type)
            return fromMap(map)
        }

        fun fromMap(map: DataMap): ReservationRoomModel {
            return ReservationRoomModel(
                hotelId = map["hotel_id"] as String,
                hotelName = map["hotel_name"] as String,
                location = map["location"] as String,
                type = map["type"] as String,
                originalPrice = (map["original_price"] as Double).toInt(),
                bookingPrice = (map["booking_price"] as Double).toInt(),
                images = MediaModel.fromJson(Gson().toJson(map["images"])),

                )
        }
    }
}


fun ReservationRoomModel.toEntity(): ReservationRoom {
    return ReservationRoom(
        hotelId,
        hotelName,
        location,
        type,
        originalPrice,
        bookingPrice,
        images.toEntity()
    )
}
