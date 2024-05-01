package com.trekkstay.hotel.feature.hotel.domain.usecases.room

import com.google.android.gms.maps.model.LatLng
import com.trekkstay.hotel.core.typedef.ResultVoid
import com.trekkstay.hotel.core.usecase.UseCaseWithParams
import com.trekkstay.hotel.feature.hotel.domain.repositories.RoomRepo

class CreateRoomUseCase(private val repository: RoomRepo) :
    UseCaseWithParams<Unit, CreateRoomUseCaseParams> {

    override suspend fun call(params: CreateRoomUseCaseParams): ResultVoid {
        return repository.createRoom(
            params.hotelId,
            params.type,
            params.description,
            params.airConditioner,
            params.balcony,
            params.bathTub,
            params.hairDryer,
            params.kitchen,
            params.nonSmoking,
            params.shower,
            params.slippers,
            params.television,
            params.numberOfBed,
            params.roomSize,
            params.adults,
            params.children,
            params.view,
            params.videos,
            params.images,
            params.quantities,
            params.discountRate,
            params.originalPrice
        )

    }
}
data class CreateRoomUseCaseParams(
    val hotelId: String,
    val type: String,
    val description: String,
    val airConditioner: Boolean,
    val balcony: Boolean,
    val bathTub: Boolean,
    val hairDryer: Boolean,
    val kitchen: Boolean,
    val nonSmoking: Boolean,
    val shower: Boolean,
    val slippers: Boolean,
    val television: Boolean,
    val numberOfBed: Int,
    val roomSize: Int,
    val adults: Int,
    val children: Int,
    val view: String,
    val videos: List<String>,
    val images: List<String>,
    val quantities: Int,
    val discountRate: Int,
    val originalPrice: Int,
    )