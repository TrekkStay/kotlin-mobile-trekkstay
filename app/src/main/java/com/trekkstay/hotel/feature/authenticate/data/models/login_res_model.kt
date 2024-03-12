package com.trekkstay.hotel.feature.authenticate.data.models

import com.trekkstay.hotel.core.typedef.DataMap
import com.trekkstay.hotel.core.typedef.DataMapContainer
import com.trekkstay.hotel.feature.authenticate.domain.entities.LoginRes
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class LoginResModel(
    @SerializedName("full_name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("refresh_token") val refreshToken:String,
    @SerializedName("access_token") val accessToken:String,
) {
    companion object {
        fun empty(): LoginResModel {
            return LoginResModel(
                name = "_empty.name",
                email ="_empty.email",
                phone ="_empty.phone",
                refreshToken = "_empty.refresh_token",
                accessToken = "_empty.access_token"
            )
        }

        fun fromJson(source: String): LoginResModel {
            println("$source fromJson")
            val map = Gson().fromJson(source, DataMapContainer::class.java)
            return fromMap(map.data)
        }

        private fun fromMap(map: DataMap): LoginResModel {
            println("$map fromMap")
            val tokenMap = map["token"] as DataMap
            return LoginResModel(
                name = map["full_name"] as String,
                email = map["email"] as String,
                phone = map["phone"] as String,
                accessToken = tokenMap["access_token"] as String,
                refreshToken = tokenMap["refresh_token"] as String

            )
        }
    }
}


fun LoginResModel.toLoginRes(): LoginRes {
    return LoginRes(name, email,phone,refreshToken, accessToken )
}
