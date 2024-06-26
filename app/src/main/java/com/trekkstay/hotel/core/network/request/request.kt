package com.trekkstay.hotel.core.network.request

import com.trekkstay.hotel.core.network.method.RequestMethod
import org.json.JSONObject

data class RequestQuery(
    val method: RequestMethod,
    val path: String,
    val requestBody: String? = null,
    val queryParams: Map<String, Any>? = null,
    val headers: Map<String, String>? = null
)

data class PreparedRequest<T>(
    val request: RequestQuery,
    val parser: ((Any?) -> T?)?,
    val onSendProgress: ((Long, Long) -> Unit)?,
    val onReceiveProgress: (() -> Unit)?,
    val headers: Map<String, String>?
)


