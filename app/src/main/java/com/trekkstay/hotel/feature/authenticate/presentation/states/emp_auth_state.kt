package com.trekkstay.hotel.feature.authenticate.presentation.states


import com.trekkstay.hotel.feature.authenticate.domain.entities.Employee

sealed class EmpAuthState {
    object Idle : EmpAuthState()

    object EmpLoginCalling : EmpAuthState()
    data class InvalidEmpLogin(val message: String) : EmpAuthState()
    data class SuccessEmpLogin(val res: Employee) : EmpAuthState()

    object EmpRegisterCalling : EmpAuthState()
    data class InvalidEmpRegister(val message: String) : EmpAuthState()
    object SuccessEmpRegister : EmpAuthState()


    data class EmpAuthenticateError(val message: String) : EmpAuthState()
}