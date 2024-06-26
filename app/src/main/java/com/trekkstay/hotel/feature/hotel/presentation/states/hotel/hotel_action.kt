package com.trekkstay.hotel.feature.hotel.presentation.states.hotel

import com.google.android.gms.maps.model.LatLng

sealed class HotelAction
data class CreateHotelAction(
    val name: String,
    val description: String,
    val airportTransfer: Boolean,
    val conferenceRoom: Boolean,
    val fitnessCenter: Boolean,
    val foodService: Boolean,
    val freeWifi: Boolean,
    val laundryService: Boolean,
    val motorBikeRental: Boolean,
    val parkingArea: Boolean,
    val spaService: Boolean,
    val swimmingPool: Boolean,
    val coordinates: LatLng,
    val videos: List<String>,
    val images: List<String>,
    val email: String,
    val phone: String,
    val checkInTime: String,
    val checkOutTime: String,
    val provinceCode: String,
    val districtCode: String,
    val wardCode: String,
    val addressDetail: String
) : HotelAction()

data class UpdateHotelAction(
    val name: String,
    val description: String,
    val airportTransfer: Boolean,
    val conferenceRoom: Boolean,
    val fitnessCenter: Boolean,
    val foodService: Boolean,
    val freeWifi: Boolean,
    val laundryService: Boolean,
    val motorBikeRental: Boolean,
    val parkingArea: Boolean,
    val spaService: Boolean,
    val swimmingPool: Boolean,
    val coordinates: LatLng,
    val videos: List<String>,
    val images: List<String>,
    val email: String,
    val phone: String,
    val checkInTime: String,
    val checkOutTime: String,
    val provinceCode: String,
    val districtCode: String,
    val wardCode: String,
    val addressDetail: String
) : HotelAction()

data class ViewHotelAction(
    val name: String?= null,val provinceCode: String?= null,val districtCode: String?= null,val wardCode: String?= null, val priceOrder: String?= null
) : HotelAction()

data class SearchHotelAction(val locationCode: String?,val attractionLat: Double?,val attractionLng: Double?,val attractionName: String?, val priceOrder: String?,val checkInDate: String?,val checkOutDate: String?,val adults: Int?,val children: Int?,val numOfRoom: Int?,val limit: Int?,val page: Int?
) : HotelAction()

object GetHotelIdAction:HotelAction()

data class HotelDetailAction(
    val id: String
):HotelAction()

data class ViewHotelNearAction(val coordinate: LatLng, val maxRange: Double) : HotelAction()
