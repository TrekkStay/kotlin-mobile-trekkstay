package com.trekkstay.hotel.feature.hotel.domain.usecases.hotel



import com.google.android.gms.maps.model.LatLng
import com.trekkstay.hotel.core.typedef.ResultFuture
import com.trekkstay.hotel.core.typedef.ResultVoid
import com.trekkstay.hotel.core.usecase.UseCaseWithParams
import com.trekkstay.hotel.feature.hotel.domain.repositories.HotelRepo

class CreateHotelUseCase(private val repository: HotelRepo) :
    UseCaseWithParams<String, CreateHotelUseCaseParams> {

    override suspend fun call(params: CreateHotelUseCaseParams): ResultFuture<String> {
        return repository.createHotel(
            params.name,
            params.description,
            params.airportTransfer,
            params.conferenceRoom,
            params.fitnessCenter,
            params.foodService,
            params.freeWifi,
            params.laundryService,
            params.motorBikeRental,
            params.parkingArea,
            params.spaService,
            params.swimmingPool,
            params.coordinates,
            params.videos,
            params.images,
            params.email,
            params.phone,
            params.checkInTime,
            params.checkOutTime,
            params.provinceCode,
            params.districtCode,
            params.wardCode,
            params.addressDetail
        )
    }
}
data class CreateHotelUseCaseParams(val name: String,
                                    val description: String,
                                    val airportTransfer: Boolean,
                                    val conferenceRoom: Boolean,
                                    val fitnessCenter: Boolean,
                                    val foodService: Boolean,
                                    val freeWifi: Boolean,
                                    val laundryService: Boolean,
                                    val motorBikeRental: Boolean,
                                    val parkingArea: Boolean,
                                    val spaService: Boolean,
                                    val swimmingPool: Boolean,
                                    val coordinates: LatLng,
                                    val videos: List<String>,
                                    val images: List<String>,
                                    val email: String,
                                    val phone: String,
                                    val checkInTime: String,
                                    val checkOutTime: String,
                                    val provinceCode: String,
                                    val districtCode: String,
                                    val wardCode: String,
                                    val addressDetail: String)