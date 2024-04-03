package com.trekkstay.hotel.feature.authenticate.presentation.states



sealed class EmpAuthAction {}

data class EmpLoginAction(val email: String, val pass: String) : EmpAuthAction()

data class EmpRegisterAction(val name:String,val email: String, val pass: String) : EmpAuthAction()
