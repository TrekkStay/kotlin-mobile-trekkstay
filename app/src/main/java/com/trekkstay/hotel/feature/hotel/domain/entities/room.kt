package com.trekkstay.hotel.feature.hotel.domain.entities

data class Room(
    val id: String,
    val hotelId:String,
    val type: String,
    val description: String,
    val quantity: Int,
    val discountRate: Int,
    val originalPrice: Int,
    val video: List<String>,
    val image: List<String>,
    val roomSize: Int,
    val numBed: Int,
    val view: String,
    val balcony: Boolean,
    val bathTub: Boolean,
    val kitchen: Boolean,
    val television: Boolean,
    val shower: Boolean,
    val nonSmoking:Boolean,
    val hairDryer: Boolean,
    val airConditioner: Boolean,
    val slippers: Boolean,
    val adults: Boolean,
    val children: Boolean,
)