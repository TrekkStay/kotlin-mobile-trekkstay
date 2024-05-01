package com.trekkstay.hotel.feature.reservation.data.models

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import com.trekkstay.hotel.core.typedef.DataMap
import com.trekkstay.hotel.feature.hotel.data.models.LocationModel
import com.trekkstay.hotel.feature.hotel.data.models.RoomModel
import com.trekkstay.hotel.feature.hotel.data.models.toEntity
import com.trekkstay.hotel.feature.hotel.domain.entities.Location
import com.trekkstay.hotel.feature.reservation.domain.entities.Reservation

data class ReservationModel(
    @SerializedName("id") val id: String,
    @SerializedName("room_id") val roomId: String,
    @SerializedName("user_id") val userId: String,
    @SerializedName("qr_code_url") val qrCodeUrl: String,
    @SerializedName("quantity") val quantity: Double,
    @SerializedName("total_price") val totalPrice: Double,
    @SerializedName("check_in") val checkIn: String,
    @SerializedName("check_out") val checkOut: String,
    @SerializedName("status") val status: String,
    @SerializedName("guess_info") val guestInfo: GuestInfoModel,
    @SerializedName("room") val room: ReservationRoomModel,
) {
    companion object {

        fun fromJson(source: String): ReservationModel {
            val type = object : TypeToken<Map<String, Any>>() {}.type
            val map: Map<String, Any> = Gson().fromJson(source, type)
            return fromMap(map)
        }

        fun fromMap(map: DataMap): ReservationModel {
            return ReservationModel(
                id = map["id"] as String,
                roomId = map["room_id"] as String,
                userId = map["user_id"] as String,
                qrCodeUrl = map["qr_code_url"] as String? ?: "",
                quantity = (map["quantity"] as String).toDouble(),
                totalPrice = (map["total_price"] as String).toDouble(),
                checkIn = map["check_in_date"] as String,
                checkOut = map["check_out_date"] as String,
                status = map["status"] as String,
                guestInfo = GuestInfoModel.fromJson(map["guest_info"].toString()),
                room = ReservationRoomModel.fromJson(map["room"].toString()),

                )
        }
    }
}


fun ReservationModel.toEntity(): Reservation {
    return Reservation(
        id,
        roomId,
        userId,
        qrCodeUrl,
        quantity,
        totalPrice,
        checkIn,
        checkOut,
        status,
        guestInfo.toEntity(),
        room.toEntity()
    )
}