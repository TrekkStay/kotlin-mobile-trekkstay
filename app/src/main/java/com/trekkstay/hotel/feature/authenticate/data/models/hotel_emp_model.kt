package com.trekkstay.hotel.feature.authenticate.data.models

import com.trekkstay.hotel.core.typedef.DataMap
import com.google.gson.annotations.SerializedName
import com.trekkstay.hotel.feature.authenticate.domain.entities.HotelEmp
import org.json.JSONObject

data class HotelEmpModel(
    @SerializedName("id") val id: String,
    @SerializedName("hotel_id") val hotelId: String,
    @SerializedName("full_name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("contract") val contract:String,
    @SerializedName("base_salary") val salary:Int,
    @SerializedName("role") val role: String,
    @SerializedName("status") val status:String,
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
        fun fromJson(source: String): HotelEmpModel {
            val jsonObject = JSONObject(source)
            val map = jsonObject.toMap()
            return fromMap(map)
        }

        fun fromMap(map: DataMap): HotelEmpModel {
            return HotelEmpModel(
                id = map["id"] as String,
                hotelId = map["hotel_id"] as String,
                name = map["full_name"] as String,
                email = map["email"] as String,
                phone = map["phone"] as String,
                contract = map["email"] as String,
                salary = (map["base_salary"] as String).toInt(),
                role = map["role"] as String,
                status = map["status"] as String,
            )
        }
    }
}


fun HotelEmpModel.toEntity(): HotelEmp {
    return HotelEmp(id,hotelId,name, email,phone,contract,salary,role,status )
}
