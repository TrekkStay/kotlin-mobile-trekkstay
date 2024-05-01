package com.trekkstay.hotel.feature.hotel.presentation.states.location


sealed class LocationAction
data class ViewDistrictAction(
    val code: String,
) : LocationAction()

object ViewProvinceAction : LocationAction()

data class ViewWardAction(
    val code: String,
) : LocationAction()
