package com.trekkstay.hotel.feature.hotel.presentation.states.search

import com.trekkstay.hotel.feature.hotel.domain.entities.DestinationList
import com.trekkstay.hotel.feature.hotel.domain.entities.HotelList
import com.trekkstay.hotel.feature.hotel.domain.entities.LocationList
import com.trekkstay.hotel.feature.hotel.presentation.states.hotel.HotelState


sealed class SearchState {
    object Idle : SearchState()


    object ViewDestinationCalling : SearchState()
    data class InvalidViewDestination(val message: String) : SearchState()
    data class SuccessViewDestination(val destinations: DestinationList) : SearchState()

    object SearchHotelCalling : SearchState()
    data class InvalidSearchHotel(val message: String) : SearchState()
    data class SuccessSearchHotel(val list: HotelList) : SearchState()

    data class SearchError(val message: String) : SearchState()
}