package com.trekkstay.hotel.feature.hotel.data.models

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import com.trekkstay.hotel.core.typedef.DataMap
import com.trekkstay.hotel.feature.hotel.domain.entities.UserReview


data class ReviewUserModel(
    @SerializedName("id") val id: String,
    @SerializedName("full_name") val fullName: String,
    @SerializedName("email") val email: String,
    ) {
    companion object {
        fun fromJson(source: String): ReviewUserModel {
            val type = object : TypeToken<Map<String, Any>>() {}.type
            val map: Map<String, Any> = Gson().fromJson(source, type)
            return fromMap(map)
        }

        fun fromMap(map: DataMap): ReviewUserModel {
            return ReviewUserModel(
                id = map["id"] as String,
                fullName = map["full_name"] as String,
                email = map["email"] as String,
            )
        }
    }
}


fun ReviewUserModel.toEntity(): UserReview {
    return UserReview(id, fullName, email)
}
