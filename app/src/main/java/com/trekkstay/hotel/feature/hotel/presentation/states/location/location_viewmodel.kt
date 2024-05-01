package com.trekkstay.hotel.feature.hotel.presentation.states.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trekkstay.hotel.feature.hotel.domain.repositories.LocationRepo
import kotlinx.coroutines.launch

class LocationViewModel(private val locationRepo: LocationRepo) : ViewModel() {
    private val _state = MutableLiveData<LocationState>()
    val state: LiveData<LocationState> = _state

    fun processAction(action: LocationAction) {
        viewModelScope.launch {
            when (action) {
                is ViewProvinceAction -> {
                    _state.postValue(LocationState.ViewProvinceCalling)
                    val result = locationRepo.viewProvince()
                    result.fold(
                        { failure -> _state.postValue(LocationState.InvalidViewProvince(failure.message)) },
                        { locationList ->  _state.postValue(LocationState.SuccessViewProvince(locationList)) }
                    )
                }
                is ViewDistrictAction -> {
                    _state.postValue(LocationState.ViewDistrictCalling)
                    val result = locationRepo.viewDistrict(action.code)
                    result.fold(
                        { failure -> _state.postValue(LocationState.InvalidViewDistrict(failure.message)) },
                        { locationList ->  _state.postValue(LocationState.SuccessViewDistrict(locationList)) }
                    )
                }
                is ViewWardAction -> {
                    _state.postValue(LocationState.ViewWardCalling)
                    val result = locationRepo.viewWard(action.code)
                    result.fold(
                        { failure -> _state.postValue(LocationState.InvalidViewWard(failure.message)) },
                        { locationList ->  _state.postValue(LocationState.SuccessViewWard(locationList)) }
                    )
                }
            }
        }
    }
}


