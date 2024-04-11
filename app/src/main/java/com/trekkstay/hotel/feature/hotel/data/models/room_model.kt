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
    @SerializedName("videos") val videos: List<String>,
    @SerializedName("images") val images: List<String>,
    @SerializedName("room_size") val roomSize: Int,
    @SerializedName("num_bed") val numBed: Int,
    @SerializedName("view") val view: String,
    @SerializedName("balcony") val balcony: Boolean,
    @SerializedName("bath_tub") val bathTub: Boolean,
    @SerializedName("kitchen") val kitchen: Boolean,
    @SerializedName("television") val television: Boolean,
    @SerializedName("shower") val shower: Boolean,
    @SerializedName("non_smoking") val nonSmoking: Boolean,
    @SerializedName("hair_dryer") val hairDryer: Boolean,
    @SerializedName("air_conditioner") val airConditioner: Boolean,
    @SerializedName("slippers") val slippers: Boolean,
    @SerializedName("adults") val adults: Boolean,
    @SerializedName("children") val children: Boolean,
) {
    companion object {


        fun fromJson(source: String): RoomModel {
            val type = object : TypeToken<Map<String, Any>>() {}.type
            val map: Map<String, Any> = Gson().fromJson(source, type)

            return fromMap(map)
        }

        fun fromMap(map: DataMap): RoomModel {
            val facilitiesMap = map["facilities"] as? DataMap
            val sleepsMap = facilitiesMap?.get("sleeps") as? DataMap

            return RoomModel(
                id = map["id"] as? String ?: "",
                hotelId = map["hotel_id"] as? String ?: "",
                type = map["type"] as? String ?: "",
                description = map["description"] as? String ?: "",
                quantity = (map["quantity"] as? Double)?.toInt() ?: 0,
                discountRate = (map["discount_rate"] as? Double)?.toInt() ?: 0,
                originalPrice = (map["original_price"] as? Double)?.toInt() ?: 0,
                videos = (map["videos"] as? DataMap)?.get("urls") as? List<String> ?: emptyList(),
                images = (map["images"] as? DataMap)?.get("urls") as? List<String> ?: emptyList(),
                roomSize = facilitiesMap?.get("room_size") as? Int ?: 0,
                numBed = facilitiesMap?.get("number_of_bed") as? Int ?: 0,
                view = facilitiesMap?.get("view") as? String ?: "",
                balcony = facilitiesMap?.get("balcony") as? Boolean ?: false,
                bathTub = facilitiesMap?.get("bath_tub") as? Boolean ?: false,
                kitchen = facilitiesMap?.get("kitchen") as? Boolean ?: false,
                television = facilitiesMap?.get("television") as? Boolean ?: false,
                shower = facilitiesMap?.get("shower") as? Boolean ?: false,
                nonSmoking = facilitiesMap?.get("non_smoking") as? Boolean ?: false,
                hairDryer = facilitiesMap?.get("hair_dryer") as? Boolean ?: false,
                airConditioner = facilitiesMap?.get("air_conditioner") as? Boolean ?: false,
                slippers = facilitiesMap?.get("slippers") as? Boolean ?: false,
                adults = sleepsMap?.get("adults") as? Boolean ?: false,
                children = sleepsMap?.get("children") as? Boolean ?: false
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
        video = videos,
        image = images,
        roomSize = roomSize,
        numBed = numBed,
        view = view,
        balcony = balcony,
        bathTub = bathTub,
        kitchen = kitchen,
        television = television,
        shower = shower,
        nonSmoking = nonSmoking,
        hairDryer = hairDryer,
        airConditioner = airConditioner,
        slippers = slippers,
        adults = adults,
        children = children
    )
}
