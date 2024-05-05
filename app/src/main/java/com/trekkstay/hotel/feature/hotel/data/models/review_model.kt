package com.trekkstay.hotel.feature.hotel.data.models

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import com.trekkstay.hotel.core.typedef.DataMap
import com.trekkstay.hotel.feature.hotel.domain.entities.Review
import com.trekkstay.hotel.feature.hotel.domain.entities.UserReview


data class ReviewModel(
    @SerializedName("id") val id: String,
    @SerializedName("user_id") val userId: String,
    @SerializedName("hotel_id") val hotelId: String,
    @SerializedName("title") val title :String,
    @SerializedName("type_of_traveler") val typeOfTraveler :String,
    @SerializedName("title") val point :Int,
    @SerializedName("title") val summary :String,
    @SerializedName("title") val user : ReviewUserModel,

) {
    companion object {
        fun fromJson(source: String): ReviewModel {
            val type = object : TypeToken<Map<String, Any>>() {}.type
            val map: Map<String, Any> = Gson().fromJson(source, type)
            return fromMap(map)
        }

        fun fromMap(map: DataMap): ReviewModel {
            return ReviewModel(
                id = map["id"] as String,
                userId = map["user_id"] as String,
                hotelId = map["hotel_id"] as String,
                title = map["title"] as String,
                typeOfTraveler = map["type_of_traveler"] as String,
                point = (map["point"] as String).toInt(),
                summary = map["summary"] as String,
                user = ReviewUserModel.fromJson(map["user"].toString())
                )
        }
    }
}

fun ReviewModel.toEntity(): Review {
    return Review(id, userId, hotelId, title, typeOfTraveler, point, summary, user.toEntity())
}
