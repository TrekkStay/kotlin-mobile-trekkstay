package com.trekkstay.hotel.feature.hotel.data.models

import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import com.trekkstay.hotel.core.typedef.DataMap
import com.trekkstay.hotel.feature.hotel.domain.entities.Hotel
import com.trekkstay.hotel.feature.hotel.domain.entities.HotelFacilities


data class HotelFacilitiesModel(
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
){
    companion object {

        fun fromJson(source: String): HotelFacilitiesModel {

            val type = object : TypeToken<Map<String, Any>>() {}.type
            val map: Map<String, Any> = Gson().fromJson(source, type)

            return fromMap(map)
        }


        private fun fromMap(map: DataMap): HotelFacilitiesModel {

            return HotelFacilitiesModel(
                airportTransfer = (map["airport_transfer"] as? Boolean ) ?: false,
                conferenceRoom = (map["conference_room"] as? Boolean ) ?: false,
                fitnessCenter = (map["fitness_center"] as? Boolean ) ?: false,
                foodService = (map["food_service"] as? Boolean ) ?: false,
                freeWifi = (map["free_wifi"] as? Boolean ) ?: false,
                laundryService = (map["laundry_service"] as? Boolean ) ?: false,
                motorBikeRental = (map["motor_bike_rental"] as? Boolean ) ?: false,
                parkingArea = (map["parking_area"] as? Boolean ) ?: false,
                spaService = (map["spa_service"] as? Boolean ) ?: false,
                swimmingPool = (map["swimming_pool"] as? Boolean ) ?: false,
            )
        }

    }
}


fun HotelFacilitiesModel.toEntity(): HotelFacilities {
    return HotelFacilities(
        
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
    )
}
