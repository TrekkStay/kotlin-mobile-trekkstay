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


data class ViewHotelAction(val name:String) : HotelAction()

object GetHotelIdAction:HotelAction()
