package com.trekkstay.hotel.feature.authenticate.data.datasources

import android.content.Context
import com.trekkstay.hotel.core.network.client.Client
import com.trekkstay.hotel.core.network.method.RequestMethod
import com.trekkstay.hotel.core.network.request.RequestQuery
import com.trekkstay.hotel.core.network.response.Response
import com.trekkstay.hotel.core.storage.LocalStore
import com.trekkstay.hotel.feature.authenticate.data.models.EmployeeModel
import com.trekkstay.hotel.feature.authenticate.data.models.HotelEmpListModel
import com.trekkstay.hotel.feature.authenticate.data.models.LoginResModel
import com.trekkstay.hotel.feature.authenticate.data.models.toEmployee
import com.trekkstay.hotel.feature.authenticate.data.models.toEntity
import com.trekkstay.hotel.feature.authenticate.data.models.toLoginRes
import com.trekkstay.hotel.feature.authenticate.domain.entities.Employee
import com.trekkstay.hotel.feature.authenticate.domain.entities.HotelEmpList
import com.trekkstay.hotel.feature.authenticate.domain.entities.LoginRes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import kotlin.random.Random

interface AuthRemoteDataSource {
    suspend fun login(email: String, pass: String): Response<LoginRes>
    suspend fun register(email: String, name: String, pass: String): Response<Unit>
    suspend fun empLogin(email: String, pass: String): Response<Employee>
    suspend fun empRegister(email: String, name: String, pass: String): Response<Unit>
    suspend fun empCreate(
        email: String,
        name: String,
        phone: String,
        contract: String,
        salary: Int
    ): Response<Unit>

    suspend fun changePassword(oldPw: String, newPw: String, newRePw: String): Response<Unit>
    suspend fun viewEmp(hotelId: String): Response<HotelEmpList>

}

const val loginEndpoint = "user/login"
const val registerEndpoint = "user/signup"
const val empLoginEndpoint = "hotel-emp/login"
const val empRegisterEndpoint = "hotel-emp/create-owner"
const val empCreateEndpoint = "hotel-emp/create-emp"
const val viewEmpEndpoint = "hotel-emp/filter"
const val changePasswordEndpoint = "user/change-password"


class AuthRemoteDataSourceImpl(private val client: Client, private val context: Context) :
    AuthRemoteDataSource {

    override suspend fun login(email: String, pass: String): Response<LoginRes> {
        return withContext(Dispatchers.IO) {
            val requestBodyJson = JSONObject().apply {
                put("email", email)
                put("password", pass)
            }


            val request = RequestQuery(
                method = RequestMethod.POST,
                path = "http://52.163.61.213:8888/api/v1/$loginEndpoint",
                requestBody = requestBodyJson.toString()
            )

            val response = client.execute<LoginRes>(
                request = request,
                parser = { responseData ->
                    if (responseData is String) {
                        parseResponse(LoginResModel.fromJson(responseData))
                    } else {
                        null
                    }
                }


            )
            response
        }
    }

    override suspend fun register(email: String, name: String, pass: String): Response<Unit> {
        return withContext(Dispatchers.IO) {
            val requestBodyJson = JSONObject().apply {
                put("full_name", name)
                put("email", email)
                put("password", pass)
            }

            val request = RequestQuery(
                method = RequestMethod.POST,
                path = "http://52.163.61.213:8888/api/v1/$registerEndpoint",
                requestBody = requestBodyJson.toString()
            )

            val response = client.execute<Unit>(
                request = request
            )

            response
        }
    }

    override suspend fun empLogin(email: String, pass: String): Response<Employee> {
        return withContext(Dispatchers.IO) {
            val requestBodyJson = JSONObject().apply {
                put("email", email)
                put("password", pass)
            }

            val request = RequestQuery(
                method = RequestMethod.POST,
                path = "http://52.163.61.213:8888/api/v1/$empLoginEndpoint",
                requestBody = requestBodyJson.toString()
            )

            val response = client.execute<Employee>(
                request = request,
                parser = { responseData ->
                    if (responseData is String) {
                        parseResponse(EmployeeModel.fromJson(responseData))
                    } else {
                        null
                    }
                }


            )
            response
        }
    }

    override suspend fun empRegister(email: String, name: String, pass: String): Response<Unit> {
        return withContext(Dispatchers.IO) {
            val requestBodyJson = JSONObject().apply {
                put("full_name", name)
                put("email", email)
                put("phone", "0949222${Random.nextInt(100, 999)}")
                put("password", pass)
            }

            val request = RequestQuery(
                method = RequestMethod.POST,
                path = "http://52.163.61.213:8888/api/v1/$empRegisterEndpoint",
                requestBody = requestBodyJson.toString()
            )

            val response = client.execute<Unit>(
                request = request
            )

            response
        }
    }

    override suspend fun empCreate(
        email: String,
        name: String,
        phone: String,
        contract: String,
        salary: Int
    ): Response<Unit> {
        return withContext(Dispatchers.IO) {
            val jwtKey = LocalStore.getKey(context, "jwtKey", "")
            val requestBodyJson = JSONObject().apply {
                put("full_name", name)
                put("email", email)
                put("phone", phone)
                put("contract", contract)
                put("base_salary", salary)
            }

            val request = RequestQuery(
                method = RequestMethod.POST,
                path = "http://52.163.61.213:8888/api/v1/$empCreateEndpoint",
                headers = mapOf("Authorization" to "Bearer $jwtKey"),
                requestBody = requestBodyJson.toString()
            )

            val response = client.execute<Unit>(
                request = request
            )

            response
        }
    }


    override suspend fun viewEmp(hotelId: String): Response<HotelEmpList> {
        return withContext(Dispatchers.IO) {

            val jwtKey = LocalStore.getKey(context, "jwtKey", "")


            val request = RequestQuery(
                method = RequestMethod.POST,
                path = "http://52.163.61.213:8888/api/v1/$viewEmpEndpoint?hotel_id=$hotelId",
                headers = mapOf("Authorization" to "Bearer $jwtKey"),
                requestBody = null
            )

            val response = client.execute<HotelEmpList>(
                request = request,
                parser = { responseData ->
                    if (responseData is String) {
                        parseResponse(HotelEmpListModel.fromJson(responseData))
                    } else {
                        null
                    }
                }


            )
            response
        }
    }

    override suspend fun changePassword(
        oldPw: String,
        newPw: String,
        newRePw: String
    ): Response<Unit> {
        return withContext(Dispatchers.IO) {

            val jwtKey = LocalStore.getKey(context, "jwtKey", "")
            val requestBodyJson = JSONObject().apply {
                put("old_pwd", oldPw)
                put("new_pwd", newPw)
            }

            val request = RequestQuery(
                method = RequestMethod.POST,
                path = "http://52.163.61.213:8888/api/v1/$changePasswordEndpoint",
                headers = mapOf("Authorization" to "Bearer $jwtKey"),
                requestBody = requestBodyJson.toString()
            )

            val response = client.execute<Unit>(
                request = request
            )
            response
        }
    }

    private inline fun <reified T : Any> parseResponse(responseData: Any?): T? {
        return when (responseData) {
            is LoginResModel -> responseData.toLoginRes() as? T
            is EmployeeModel -> responseData.toEmployee() as T
            is HotelEmpListModel -> responseData.toEntity() as T
            else -> null
        }
    }

}
