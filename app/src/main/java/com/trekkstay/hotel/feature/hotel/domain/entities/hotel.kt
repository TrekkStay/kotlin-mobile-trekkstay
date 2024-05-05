package com.trekkstay.hotel.feature.hotel.domain.entities

import com.google.android.gms.maps.model.LatLng

data class Hotel(

    val id:String,
    val name: String,
    val ownerId: String,
    val email: String,
    val phone: String,
    val checkInTime: String,
    val checkOutTime: String,
    val addressDetail: String,
    val description: String,
    val status: String,
    val room: List<HotelRoom>,
    val province: Location,
    val district: Location,
    val ward: Location,
    val facilities: HotelFacilities,
    val coordinates: LatLng,
    val videos: Media,
    val images: Media,
    val neighbourhood: Neighbourhood? = null,
    val rating: Rating
    )