package com.trekkstay.hotel.feature.hotel.presentation.states.search

import com.trekkstay.hotel.feature.hotel.presentation.states.hotel.HotelAction
import java.io.File


sealed class SearchAction
object ViewDestinationAction : SearchAction()

data class SearchHotelAction(
    val name: String?= null,val provinceCode: String?= null,val districtCode: String?= null,val wardCode: String?= null, val priceOrder: String?= null
) : SearchAction()


