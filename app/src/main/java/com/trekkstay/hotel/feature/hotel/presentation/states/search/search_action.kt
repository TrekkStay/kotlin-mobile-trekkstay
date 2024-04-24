package com.trekkstay.hotel.feature.hotel.presentation.states.search

import com.trekkstay.hotel.feature.hotel.presentation.states.hotel.HotelAction
import java.io.File


sealed class SearchAction
object ViewDestinationAction : SearchAction()

data class SearchHotelAction(val locationCode: String?= null, val priceOrder: String?= null,val checkInDate: String?= null,val checkOutDate: String?= null,val adults: Int?= null,val children: Int?= null,val numOfRoom: Int?= null,val limit: Int?= null,val page: Int?= null
) : SearchAction()


