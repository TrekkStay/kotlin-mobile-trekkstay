package com.trekkstay.hotel.payment

import android.app.Activity
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily
import com.trekkstay.hotel.ui.theme.TrekkStayCyan
import vn.momo.momo_partner.AppMoMoLib

@Composable
fun MoMoPayment(
    amount: String,
    orderID: String,
    onPaymentSuccess: (token: String, phoneNumber: String) -> Unit,
    onPaymentFailure: (message: String) -> Unit
) {
    val context = LocalContext.current

    DisposableEffect(key1 = context) {
        val environment = 1

        val appMoMoLib = AppMoMoLib.getInstance()

        when (environment) {
            0 -> appMoMoLib.setEnvironment(AppMoMoLib.ENVIRONMENT.DEBUG)
            1 -> appMoMoLib.setEnvironment(AppMoMoLib.ENVIRONMENT.DEVELOPMENT)
            2 -> appMoMoLib.setEnvironment(AppMoMoLib.ENVIRONMENT.PRODUCTION)
        }

        appMoMoLib.setAction(AppMoMoLib.ACTION.PAYMENT)
        appMoMoLib.setActionType(AppMoMoLib.ACTION_TYPE.GET_TOKEN)

        val eventValue: MutableMap<String, Any> = HashMap()
        eventValue["merchantname"] = "TrekkStay"
        eventValue["merchantcode"] = "CGV19072017"
        eventValue["amount"] = amount
        eventValue["orderId"] = orderID
        eventValue["orderLabel"] = "Mã đơn hàng"
        eventValue["merchantnamelabel"] = "Nhà cung cấp"
        eventValue["fee"] = 0
        eventValue["description"] = "Thanh toán hóa đơn"
        eventValue["requestId"] = "CGV19072017merchant_billId_${System.currentTimeMillis()}"
        eventValue["partnerCode"] = "CGV19072017"
        eventValue["extra"] = ""

        appMoMoLib.requestMoMoCallBack(context as Activity, eventValue)

        onDispose {
            // Cleanup if needed
        }
    }

    val onActivityResult: (requestCode: Int, resultCode: Int, data: Intent?) -> Unit =
        { requestCode, resultCode, data ->
            if (requestCode == AppMoMoLib.getInstance().REQUEST_CODE_MOMO && resultCode == -1) {
                if (data != null) {
                    when (data.getIntExtra("status", -1)) {
                        0 -> {
                            val token = data.getStringExtra("data")
                            val phoneNumber = data.getStringExtra("phonenumber")
                            onPaymentSuccess(token ?: "", phoneNumber ?: "")
                        }

                        1 -> {
                            val message = data.getStringExtra("message") ?: "Thất bại"
                            onPaymentFailure(message)
                        }

                        2 -> onPaymentFailure("Payment cancelled")
                        else -> onPaymentFailure("Unknown error")
                    }
                } else {
                    onPaymentFailure("Data is null")
                }
            } else {
                onPaymentFailure("Invalid request")
            }
        }

    val activityResultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
    ) { result ->
        onActivityResult(
            result.resultCode,
            result.resultCode,
            result.data
        )
    }
}