package com.trekkstay.hotel.feature.hotel.domain.repositories

import com.trekkstay.hotel.core.typedef.ResultFuture
import com.trekkstay.hotel.feature.hotel.domain.entities.LocationList

interface SearchRepo {suspend fun viewDestination(): ResultFuture<LocationList>}