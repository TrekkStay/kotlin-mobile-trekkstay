package com.trekkstay.hotel.feature.reservation.domain.entities

data class GuestInfo(
    val name:String,
    val contact:String,
    val adult:Int,
    val children:Int,

    )