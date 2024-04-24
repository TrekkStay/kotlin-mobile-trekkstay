package com.trekkstay.hotel.feature.authenticate.domain.repositories

import com.trekkstay.hotel.core.typedef.ResultFuture
import com.trekkstay.hotel.core.typedef.ResultVoid
import com.trekkstay.hotel.feature.authenticate.domain.entities.Employee
import com.trekkstay.hotel.feature.authenticate.domain.entities.HotelEmpList
import com.trekkstay.hotel.feature.authenticate.domain.entities.LoginRes


interface AuthRepo {
    suspend fun login(email: String, pass: String): ResultFuture<LoginRes>
    suspend fun register(email: String, name: String, pass: String): ResultVoid
    suspend fun empLogin(email: String, pass: String): ResultFuture<Employee>
    suspend fun empRegister(email: String, name: String, pass: String): ResultVoid
    suspend fun empCreate(email: String, name: String, phone: String,contract: String,salary: Int): ResultVoid
    suspend fun viewEmp(hotelId:String):ResultFuture<HotelEmpList>
    suspend fun logout(): ResultVoid
}
