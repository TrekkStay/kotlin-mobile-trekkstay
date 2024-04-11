package com.trekkstay.hotel.feature.hotel.data.models

import com.google.android.gms.maps.model.LatLng
import com.trekkstay.hotel.core.typedef.DataMap
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import com.trekkstay.hotel.feature.hotel.domain.entities.Hotel

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
    @SerializedName("roomId") val roomId: String,
    @SerializedName("roomType") val roomType: String,
    @SerializedName("roomDes") val roomDes: String,
    @SerializedName("roomQuantity") val roomQuantity: Int,
    @SerializedName("roomDiscount") val roomDiscount: Int,
    @SerializedName("originalPrice") val originalPrice: Int,
    @SerializedName("roomVideo") val roomVideo: List<String>,
    @SerializedName("roomImage") val roomImage: List<String>,
    @SerializedName("province") val province: LocationModel,
    @SerializedName("district") val district: LocationModel,
    @SerializedName("ward") val ward: LocationModel,
    @SerializedName("airportTransfer") val airportTransfer: Boolean,
    @SerializedName("conferenceRoom") val conferenceRoom: Boolean,
    @SerializedName("fitnessCenter") val fitnessCenter: Boolean,
    @SerializedName("foodService") val foodService: Boolean,
    @SerializedName("freeWifi") val freeWifi: Boolean,
    @SerializedName("laundryService") val laundryService: Boolean,
    @SerializedName("motorBikeRental") val motorBikeRental: Boolean,
    @SerializedName("parkingArea") val parkingArea: Boolean,
    @SerializedName("spaService") val spaService: Boolean,
    @SerializedName("swimmingPool") val swimmingPool: Boolean,
    @SerializedName("coordinates") val coordinates: LatLng,
    @SerializedName("videos") val videos: List<String>,
    @SerializedName("images") val images: List<String>,
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

        fun fromJson(source: String): HotelModel {
            val type = object : TypeToken<Map<String, Any>>() {}.type
            val map: Map<String, Any> = Gson().fromJson(source, type)

            return fromMap(map)
        }

        fun fromMap(map: DataMap): HotelModel {
            val roomsList = (map["rooms"] as? List<*>)?.mapNotNull { it as? DataMap } ?: emptyList()
            val roomMap = if (roomsList.isNotEmpty()) roomsList.first() else null

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
                roomId = roomMap?.get("id") as? String ?: "",
                roomType = roomMap?.get("type") as? String ?: "",
                roomDes = roomMap?.get("description") as? String ?: "",
                roomQuantity = roomMap?.get("quantity") as? Int ?: 0,
                roomDiscount = roomMap?.get("discount_rate") as? Int ?: 0,
                originalPrice = roomMap?.get("original_price") as? Int ?: 0,
                roomVideo = (roomMap?.get("videos") as? DataMap)?.let { (it["urls"] as? List<*>)?.mapNotNull { url -> url as? String } } ?: emptyList(),
                roomImage = (roomMap?.get("images") as? DataMap)?.let { (it["urls"] as? List<*>)?.mapNotNull { url -> url as? String } } ?: emptyList(),
                province = LocationModel.fromJson(map["province"].toString()),
                district = LocationModel.fromJson(map["district"].toString()),
                ward = LocationModel.fromJson(map["ward"].toString()),
                airportTransfer = map["facilities"]?.let { (it as? DataMap)?.get("airport_transfer") as? Boolean } ?: false,
                conferenceRoom = map["facilities"]?.let { (it as? DataMap)?.get("conference_room") as? Boolean } ?: false,
                fitnessCenter = map["facilities"]?.let { (it as? DataMap)?.get("fitness_center") as? Boolean } ?: false,
                foodService = map["facilities"]?.let { (it as? DataMap)?.get("food_service") as? Boolean } ?: false,
                freeWifi = map["facilities"]?.let { (it as? DataMap)?.get("free_wifi") as? Boolean } ?: false,
                laundryService = map["facilities"]?.let { (it as? DataMap)?.get("laundry_service") as? Boolean } ?: false,
                motorBikeRental = map["facilities"]?.let { (it as? DataMap)?.get("motor_bike_rental") as? Boolean } ?: false,
                parkingArea = map["facilities"]?.let { (it as? DataMap)?.get("parking_area") as? Boolean } ?: false,
                spaService = map["facilities"]?.let { (it as? DataMap)?.get("spa_service") as? Boolean } ?: false,
                swimmingPool = map["facilities"]?.let { (it as? DataMap)?.get("swimming_pool") as? Boolean } ?: false,
                coordinates = LatLng((map["coordinates"] as? DataMap)?.get("lat") as? Double ?: 0.0, (map["coordinates"] as? DataMap)?.get("lng") as? Double ?: 0.0),
                videos = (map["videos"] as? DataMap)?.let { (it["urls"] as? List<*>)?.mapNotNull { url -> url as? String } } ?: emptyList(),
                images = (map["images"] as? DataMap)?.let { (it["urls"] as? List<*>)?.mapNotNull { url -> url as? String } } ?: emptyList()
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
        roomId = roomId,
        roomType = roomType,
        roomDes = roomDes,
        roomQuantity = roomQuantity,
        roomDiscount = roomDiscount,
        originalPrice = originalPrice,
        roomVideo = roomVideo,
        roomImage = roomImage,
        province = province.toEntity(),
        district = district.toEntity(),
        ward = ward.toEntity(),
        airportTransfer = airportTransfer,
        conferenceRoom = conferenceRoom,
        fitnessCenter = fitnessCenter,
        foodService = foodService,
        freeWifi = freeWifi,
        laundryService = laundryService,
        motorBikeRental = motorBikeRental,
        parkingArea = parkingArea,
        spaService = spaService,
        swimmingPool = swimmingPool,
        coordinates = coordinates,
        videos = videos,
        images = images
    )
}
