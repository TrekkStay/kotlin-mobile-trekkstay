package com.trekkstay.hotel.feature.hotel.presentation.states.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trekkstay.hotel.feature.hotel.domain.repositories.SearchRepo
import kotlinx.coroutines.launch

class SearchViewModel(private val searchRepo: SearchRepo) : ViewModel() {
    private val _state = MutableLiveData<SearchState>()
    val state: LiveData<SearchState> = _state

    fun processAction(action: SearchAction) {
        viewModelScope.launch {
            when (action) {
                is ViewDestinationAction -> {
                    _state.postValue(SearchState.ViewDestinationCalling)
                    val result = searchRepo.viewDestination()
                    result.fold(
                        { failure -> _state.postValue(SearchState.InvalidViewDestination(failure.message)) },
                        { search ->  _state.postValue(SearchState.SuccessViewDestination(search)) }
                    )
                }

            }
        }
    }
}


