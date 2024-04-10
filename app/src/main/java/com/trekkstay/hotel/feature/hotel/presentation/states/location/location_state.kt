package com.trekkstay.hotel.feature.hotel.presentation.states.location

import com.trekkstay.hotel.feature.hotel.domain.entities.LocationList


sealed class LocationState {
    object Idle : LocationState()

    object ViewProvinceCalling : LocationState()
    data class InvalidViewProvince(val message: String) : LocationState()
    data class SuccessViewProvince(val locationList: LocationList) : LocationState()

    object ViewDistrictCalling : LocationState()
    data class InvalidViewDistrict(val message: String) : LocationState()
    data class SuccessViewDistrict(val locationList: LocationList) : LocationState()

    object ViewWardCalling : LocationState()
    data class InvalidViewWard(val message: String) : LocationState()
    data class SuccessViewWard(val locationList: LocationList) : LocationState()


    data class AuthenticateError(val message: String) : LocationState()
}