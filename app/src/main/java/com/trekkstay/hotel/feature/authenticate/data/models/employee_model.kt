package com.trekkstay.hotel.feature.authenticate.data.models


import com.trekkstay.hotel.core.typedef.DataMap
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import com.trekkstay.hotel.feature.authenticate.domain.entities.Employee

data class EmployeeModel(
    @SerializedName("full_name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("refresh_token") val refreshToken:String,
    @SerializedName("access_token") val accessToken:String,
) {
    companion object {
        fun empty(): EmployeeModel {
            return EmployeeModel(
                name = "_empty.name",
                email ="_empty.email",
                phone ="_empty.phone",
                refreshToken = "_empty.refresh_token",
                accessToken = "_empty.access_token"
            )
        }

        fun fromJson(source: String): EmployeeModel {
            val type = object : TypeToken<Map<String, Any>>() {}.type
            val map: Map<String, Any> = Gson().fromJson(source, type)
            return fromMap(map)
        }

        private fun fromMap(map: DataMap): EmployeeModel {
            println("$map fromMap")
            val tokenMap = map["token"] as DataMap
            return EmployeeModel(
                name = map["full_name"] as String,
                email = map["email"] as String,
                phone = map["phone"] as String,
                accessToken = tokenMap["access_token"] as String,
                refreshToken = tokenMap["refresh_token"] as String

            )
        }
    }
}


fun EmployeeModel.toEmployee(): Employee {
    return Employee(name, email,phone,refreshToken, accessToken )
}
