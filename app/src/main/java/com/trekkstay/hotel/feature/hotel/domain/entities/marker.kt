package com.trekkstay.hotel.feature.hotel.domain.entities

import com.google.android.gms.maps.model.LatLng

data class MarkerInfo(val position: LatLng, val price: Double,
    val name:String)