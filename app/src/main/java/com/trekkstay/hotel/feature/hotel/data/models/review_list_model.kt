package com.trekkstay.hotel.feature.hotel.data.models

import com.google.gson.annotations.SerializedName
import com.trekkstay.hotel.core.typedef.DataMap
import com.trekkstay.hotel.feature.hotel.domain.entities.ReviewList
import org.json.JSONArray
import org.json.JSONObject


data class ReviewListModel(
    @SerializedName("review_list") val reviewList: List<ReviewModel>,
) {
    companion object {
        fun empty(): AttractionListModel {
            return AttractionListModel(
                attractionList = emptyList(),
            )
        }

        private fun JSONObject.toMap(): Map<String, String> {
            val map = mutableMapOf<String, String>()
            val keysItr = keys()

            while (keysItr.hasNext()) {
                val key = keysItr.next()
                val value = getString(key)
                map[key] = value
            }

            return map
        }
        fun fromJson(source: String): ReviewListModel {
            val jsonArray = JSONArray(source)
            val list: MutableList<Map<String, String>> = mutableListOf()

            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val map = jsonObject.toMap()
                list.add(map)
            }
            return fromMap(list)
        }

        private fun fromMap(data: List<DataMap>): ReviewListModel {
            val attractions =
                data.map { ReviewModel.fromMap(it) }
            return ReviewListModel(reviewList = attractions)
        }
    }
}


fun ReviewListModel.toEntity(): ReviewList {
    val attraction = reviewList.map { it.toEntity() }
    return ReviewList(attraction)
}