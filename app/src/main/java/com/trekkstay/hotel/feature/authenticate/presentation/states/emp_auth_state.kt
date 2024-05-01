package com.trekkstay.hotel.feature.authenticate.presentation.states


import com.trekkstay.hotel.feature.authenticate.domain.entities.Employee
import com.trekkstay.hotel.feature.authenticate.domain.entities.HotelEmpList

sealed class EmpAuthState {
    object Idle : EmpAuthState()

    object EmpLoginCalling : EmpAuthState()
    data class InvalidEmpLogin(val message: String) : EmpAuthState()
    data class SuccessEmpLogin(val res: Employee) : EmpAuthState()

    object EmpRegisterCalling : EmpAuthState()
    data class InvalidEmpRegister(val message: String) : EmpAuthState()
    object SuccessEmpRegister : EmpAuthState()

    object EmpCreateCalling : EmpAuthState()
    data class InvalidEmpCreate(val message: String) : EmpAuthState()
    object SuccessEmpCreate : EmpAuthState()

    object ViewEmpCalling : EmpAuthState()
    data class InvalidViewEmp(val message: String) : EmpAuthState()
    data class SuccessViewEmp(val res: HotelEmpList) : EmpAuthState()


    data class EmpAuthenticateError(val message: String) : EmpAuthState()
}