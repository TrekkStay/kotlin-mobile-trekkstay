package com.trekkstay.hotel.feature.hotel.data.models

import com.google.android.gms.maps.model.LatLng
import com.trekkstay.hotel.core.typedef.DataMap
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import com.trekkstay.hotel.feature.hotel.data.models.HotelRoomModel.Companion.fromList
import com.trekkstay.hotel.feature.hotel.domain.entities.Hotel
import com.trekkstay.hotel.feature.hotel.domain.entities.HotelRoom
import org.json.JSONArray
import org.json.JSONObject

data class HotelModel(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("ownerId") val ownerId: String,
    @SerializedName("email") val email: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("checkInTime") val checkInTime: String,
    @SerializedName("checkOutTime") val checkOutTime: String,
    @SerializedName("addressDetail") val addressDetail: String,
    @SerializedName("description") val description: String,
    @SerializedName("status") val status: String,
    @SerializedName("room") val hotelRoom: List<HotelRoomModel>,
    @SerializedName("province") val province: LocationModel,
    @SerializedName("district") val district: LocationModel,
    @SerializedName("ward") val ward: LocationModel,
    @SerializedName("facilities") val facilities: HotelFacilitiesModel,
    @SerializedName("coordinates") val coordinates: LatLng,
    @SerializedName("videos") val videos: MediaModel,
    @SerializedName("images") val images: MediaModel,
) {
    companion object {

        private fun JSONObject.toMap(): Map<String, Any> {
            val map = mutableMapOf<String, Any>()
            val keysItr = keys()

            while (keysItr.hasNext()) {
                val key = keysItr.next()
                val value = getString(key)
                map[key] = value
            }

            return map
        }
        fun fromJson(source: String): HotelModel {
            val jsonObject = JSONObject(source)
            val map = jsonObject.toMap()
            return fromMap(map)
        }


        fun fromMap(map: DataMap): HotelModel {
            val type = object : TypeToken<Map<String, Any>>() {}.type
            val coordinates: Map<String, Any> = Gson().fromJson(map["coordinates"].toString(), type)
            return HotelModel(
                id = map["id"] as? String ?: "",
                name = map["name"] as? String ?: "",
                ownerId = map["owner_id"] as? String ?: "",
                email = map["email"] as? String ?: "",
                phone = map["phone"] as? String ?: "",
                checkInTime = map["check_in_time"] as? String ?: "",
                checkOutTime = map["check_out_time"] as? String ?: "",
                addressDetail = map["address_detail"] as? String ?: "",
                description = map["description"] as? String ?: "",
                status = map["status"] as? String ?: "",
                hotelRoom = fromList(map["rooms"].toString()),
                province = LocationModel.fromJson(map["province"].toString()),
                district = LocationModel.fromJson(map["district"].toString()),
                ward = LocationModel.fromJson(map["ward"].toString()),
                facilities = HotelFacilitiesModel.fromJson(map["facilities"].toString()),
                coordinates = LatLng((coordinates["lat"] as? Double )?: 0.0, (coordinates["lng"] as? Double ) ?: 0.0),
                videos = MediaModel.fromJson(map["videos"].toString()),
                images = MediaModel.fromJson(map["images"].toString())
                )


        }

    }
}


fun HotelModel.toEntity(): Hotel {
    return Hotel(
        id = id,
        name = name,
        ownerId = ownerId,
        email = email,
        phone = phone,
        checkInTime = checkInTime,
        checkOutTime = checkOutTime,
        addressDetail = addressDetail,
        description = description,
        status = status,
        room = hotelRoom.map { it.toEntity() },
        province = province.toEntity(),
        district = district.toEntity(),
        ward = ward.toEntity(),
        facilities = facilities.toEntity(),
        coordinates = coordinates,
        videos = videos.toEntity(),
        images = images.toEntity()
    )
}
