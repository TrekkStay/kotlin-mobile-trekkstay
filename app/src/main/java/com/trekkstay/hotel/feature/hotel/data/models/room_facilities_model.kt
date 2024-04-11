package com.trekkstay.hotel.feature.hotel.data.models


import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import com.trekkstay.hotel.core.typedef.DataMap
import com.trekkstay.hotel.feature.hotel.domain.entities.RoomFacilities


data class RoomFacilitiesModel(
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
    @SerializedName("sleep") val sleepRoom: SleepRoomModel
){
    companion object {

        fun fromJson(source: String): RoomFacilitiesModel {

            val type = object : TypeToken<Map<String, Any>>() {}.type
            val map: Map<String, Any> = Gson().fromJson(source, type)

            return fromMap(map)
        }


        private fun fromMap(map: DataMap): RoomFacilitiesModel {

            return RoomFacilitiesModel(
                roomSize = (map["room_size"] as? String )?.toInt() ?: 0,
                numBed = (map["number_of_bed"] as? String )?.toInt() ?: 0,
                view = (map["view"] as? String )?: "",
                balcony = (map["balcony"] as? Boolean ) ?: false,
                bathTub = (map["bath_tub"] as? Boolean ) ?: false,
                kitchen = (map["kitchen"] as? Boolean ) ?: false,
                television = (map["television"] as? Boolean ) ?: false,
                shower = (map["shower"] as? Boolean ) ?: false,
                nonSmoking = (map["non_smoking"] as? Boolean ) ?: false,
                hairDryer = (map["hair_dryer"] as? Boolean ) ?: false,
                airConditioner = (map["air_conditioner"] as? Boolean ) ?: false,
                slippers = (map["slippers"] as? Boolean ) ?: false,
                sleepRoom = SleepRoomModel.fromJson(map["sleeps"].toString()),
            )
        }

    }
}


fun RoomFacilitiesModel.toEntity(): RoomFacilities {
    return RoomFacilities(
        roomSize = roomSize,
        numBed =numBed,
        view =view,
        balcony =balcony,
        bathTub =  bathTub,
        kitchen =kitchen,
        television =television,
        shower= shower,
        nonSmoking =nonSmoking,
        hairDryer =hairDryer,
        airConditioner =airConditioner,
        slippers = slippers,
        sleepRoom = sleepRoom.toEntity(),
    )
}
