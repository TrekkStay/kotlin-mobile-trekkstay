package com.trekkstay.hotel.feature.hotel.presentation.states.search

import com.trekkstay.hotel.feature.hotel.domain.entities.LocationList


sealed class SearchState {
    object Idle : SearchState()


    object ViewDestinationCalling : SearchState()
    data class InvalidViewDestination(val message: String) : SearchState()
    data class SuccessViewDestination(val destinations: LocationList) : SearchState()


    data class SearchError(val message: String) : SearchState()
}