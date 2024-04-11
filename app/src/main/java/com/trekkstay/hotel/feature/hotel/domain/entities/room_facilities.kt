package com.trekkstay.hotel.feature.hotel.domain.entities


data class RoomFacilities(
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
    val sleepRoom: SleepRoom
)