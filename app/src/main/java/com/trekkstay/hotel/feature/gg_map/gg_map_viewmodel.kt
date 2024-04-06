package com.trekkstay.hotel.feature.gg_map

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.libraries.maps.model.LatLng

class GGMapActivityViewModel:ViewModel(){
    private var _isNewLocationSelected = MutableLiveData(false)
    var isNewLocationSelected : MutableLiveData<Boolean> = _isNewLocationSelected

    private var _selectedLat = mutableDoubleStateOf(0.0)
    var selectedLat: MutableState<Double> = _selectedLat

    private var _selectedLng = mutableDoubleStateOf(0.0)
    var selectedLng: MutableState<Double> = _selectedLng

    private var _userCurrentLat = mutableDoubleStateOf(0.0)
    var userCurrentLat: MutableState<Double> = _userCurrentLat

    private var _userCurrentLng = mutableDoubleStateOf(0.0)
    var userCurrentLng: MutableState<Double> = _userCurrentLng

    val pickUp = LatLng(userCurrentLat.value,userCurrentLng.value)

    private var _locationPermissionGranted = MutableLiveData(false)
    var locationPermissionGranted : LiveData<Boolean> = _locationPermissionGranted

    fun currentUserGeoCord(latLng: LatLng){
        _userCurrentLat.value = latLng.latitude
        _userCurrentLng.value = latLng.longitude
    }

    fun updateSelectedLocation(status: Boolean){
        _isNewLocationSelected.value = status
    }

    fun permissionGrand(setGranted: Boolean){
        _locationPermissionGranted.value =  setGranted
    }
}