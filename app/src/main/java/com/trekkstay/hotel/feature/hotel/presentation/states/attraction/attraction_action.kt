package com.trekkstay.hotel.feature.hotel.presentation.states.attraction


sealed class AttractionAction

data class ViewAttractionAction(
    val id: String
) : AttractionAction()
