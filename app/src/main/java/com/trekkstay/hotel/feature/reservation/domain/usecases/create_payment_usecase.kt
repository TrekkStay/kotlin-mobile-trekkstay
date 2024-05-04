package com.trekkstay.hotel.feature.reservation.domain.usecases

import com.trekkstay.hotel.core.typedef.ResultVoid
import com.trekkstay.hotel.core.usecase.UseCaseWithParams
import com.trekkstay.hotel.feature.reservation.domain.repositories.ReservationRepo

class CreatePaymentUseCase(private val repository: ReservationRepo) :
    UseCaseWithParams<Unit, CreatePaymentUseCaseParams> {

    override suspend fun call(params: CreatePaymentUseCaseParams): ResultVoid{
        return repository.createPayment(
            params.amount,
            params.method,
            params.reservationId,
            params.status
        )
    }
}

data class CreatePaymentUseCaseParams(val amount: String,
                                          val method: String,
                                          val reservationId: String,
                                          val status: String
)