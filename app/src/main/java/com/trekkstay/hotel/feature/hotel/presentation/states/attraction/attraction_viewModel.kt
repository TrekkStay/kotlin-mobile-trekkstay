package com.trekkstay.hotel.feature.hotel.presentation.states.attraction


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trekkstay.hotel.feature.hotel.domain.repositories.AttractionRepo
import com.trekkstay.hotel.feature.hotel.domain.repositories.HotelRepo
import com.trekkstay.hotel.feature.hotel.presentation.states.hotel.CreateHotelAction
import com.trekkstay.hotel.feature.hotel.presentation.states.hotel.GetHotelIdAction
import com.trekkstay.hotel.feature.hotel.presentation.states.hotel.HotelAction
import com.trekkstay.hotel.feature.hotel.presentation.states.hotel.HotelDetailAction
import com.trekkstay.hotel.feature.hotel.presentation.states.hotel.HotelState
import com.trekkstay.hotel.feature.hotel.presentation.states.hotel.SearchHotelAction
import com.trekkstay.hotel.feature.hotel.presentation.states.hotel.UpdateHotelAction
import com.trekkstay.hotel.feature.hotel.presentation.states.hotel.ViewHotelAction
import com.trekkstay.hotel.feature.hotel.presentation.states.hotel.ViewHotelNearAction
import kotlinx.coroutines.launch

class AttractionViewModel(private val attractionRepo: AttractionRepo) : ViewModel() {
    private val _state = MutableLiveData<AttractionState>()
    val state: LiveData<AttractionState> = _state

    fun processAction(action: AttractionAction) {
        viewModelScope.launch {
            when (action) {
                is ViewAttractionAction ->{
                    _state.postValue(AttractionState.AttractionListCalling)
                    val result = attractionRepo.attractionList(action.id)
                    result.fold(
                        { failure -> _state.postValue(AttractionState.InvalidAttractionList(failure.message)) },
                        { success->  _state.postValue(AttractionState.SuccessAttractionList(success)) }
                    )
                }
            }
        }
    }
}
