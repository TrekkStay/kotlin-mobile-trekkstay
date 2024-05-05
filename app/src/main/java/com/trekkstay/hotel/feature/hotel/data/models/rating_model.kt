package com.trekkstay.hotel.feature.hotel.data.models


import com.trekkstay.hotel.core.typedef.DataMap
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import com.trekkstay.hotel.feature.hotel.domain.entities.Rating

data class RatingModel(
    @SerializedName("total_review") val totalReview: Int,
    @SerializedName("avg_point") val avgPoint: Double,
) {
    companion object {
        fun empty(): RatingModel {
            return RatingModel(
                totalReview = 0,
                avgPoint = 0.0,
            )
        }

        fun fromJson(source: String): RatingModel {
            val type = object : TypeToken<Map<String, Any>>() {}.type
            val map: Map<String, Any> = Gson().fromJson(source, type)
            return fromMap(map)
        }

        fun fromMap(map: DataMap): RatingModel {
            return RatingModel(
                totalReview = (map["total_review"] as Double).toInt(),
                avgPoint = map["avg_point"] as Double,

            )
        }
    }
}


fun RatingModel.toEntity(): Rating {
    return Rating(totalReview, avgPoint)
}
