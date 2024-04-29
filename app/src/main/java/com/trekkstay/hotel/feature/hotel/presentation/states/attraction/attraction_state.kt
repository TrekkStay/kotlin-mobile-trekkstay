package com.trekkstay.hotel.feature.hotel.presentation.states.attraction

import com.trekkstay.hotel.feature.hotel.domain.entities.AttractionList

sealed class AttractionState {
    object Idle : AttractionState()
    object AttractionListCalling : AttractionState()
    data class InvalidAttractionList(val message: String) : AttractionState()
    data class SuccessAttractionList(val attractionList: AttractionList) : AttractionState()

}