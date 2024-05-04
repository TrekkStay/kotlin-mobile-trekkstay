package com.trekkstay.hotel.feature.hotel.presentation.activities

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.trekkstay.hotel.feature.authenticate.presentation.states.EmpAuthState
import com.trekkstay.hotel.feature.reservation.presentation.states.CreatePaymentAction
import com.trekkstay.hotel.feature.reservation.presentation.states.ReservationState
import com.trekkstay.hotel.feature.reservation.presentation.states.ReservationViewModel
import com.trekkstay.hotel.feature.shared.TextDialog
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily
import com.trekkstay.hotel.ui.theme.TrekkStayCyan
import vn.momo.momo_partner.AppMoMoLib

@Composable
fun PaymentScreen(reservationId:String,amount:String,reservationViewModel: ReservationViewModel,navController: NavHostController) {

    val context = LocalContext.current
    var paymentSelected by remember { mutableStateOf("") }
    val radioOptions = listOf("Pay Online with Momo","Pay At Hotel")
    val reservationState by reservationViewModel.state.observeAsState()

        when (reservationState) {
            is ReservationState.SuccessCreatePayment -> {
                navController.navigate("customer_reservations"){
                    launchSingleTop = true
                }
            }
            is ReservationState.InvalidCreatePayment -> {
                println("failed")
            }
            is ReservationState.CreatePaymentCalling -> {}
            else -> {
            }
        }

    Box(
        modifier = Modifier.background(Color.White).padding(bottom = 70.dp)
    ) {
        Column () {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp, vertical = 20.dp)
            ) {
                IconButton(onClick = {}) {
                    Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = null)
                }
                Text(
                    text = "Payment",
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp,
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp, vertical = 5.dp)
            ) {
                Text(
                    text = "Please select a payment method to pay for your reservation",
                    textAlign = TextAlign.Center,
                    fontFamily = PoppinsFontFamily,
                    color = Color.Gray,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    overflow = TextOverflow.Ellipsis
                )
                radioOptions.forEach { method ->
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            paymentSelected = if (paymentSelected == method) {
                                ""
                            } else {
                                method
                            }
                        }
                    ) {
                        RadioButton(
                            colors = RadioButtonDefaults.colors(
                                selectedColor = TrekkStayCyan,
                                unselectedColor = TrekkStayCyan
                            ),
                            selected = (paymentSelected == method),
                            onClick = {
                                if (paymentSelected == method) {
                                    paymentSelected = ""
                                } else {
                                    paymentSelected = method
                                }
                            }
                        )
                        Text(
                            text = method,
                            fontFamily = PoppinsFontFamily,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 20.dp, horizontal = 50.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        if(paymentSelected == "Pay Online with Momo") {
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
                            eventValue["amount"] = "${(amount.toDouble() * 24500.0).toInt()}"
                            eventValue["orderId"] = reservationId
                            eventValue["orderLabel"] = "Mã đơn hàng"
                            eventValue["merchantnamelabel"] = "Nhà cung cấp"
                            eventValue["fee"] = 0
                            eventValue["description"] = "Thanh toán hóa đơn"
                            eventValue["requestId"] =
                                "$reservationId.${(amount.toDouble() * 24500.0).toInt()}"
                            eventValue["partnerCode"] = "CGV19072017"
                            eventValue["extra"] = ""

                            appMoMoLib.requestMoMoCallBack(context as Activity, eventValue)
                        }
                        else{
                            val reservationAction = CreatePaymentAction(
                                amount = (amount.toDouble() * 30000.0).toInt().toString(),
                                method = "PAY_AT_HOTEL",
                                reservationId = reservationId,
                                status = "PENDING",
                            )
                            reservationViewModel.processAction(reservationAction)
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = TrekkStayCyan),
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(50.dp),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Continue",
                        color = Color.White,
                        fontWeight = FontWeight.Medium,
                        fontSize = 15.sp,
                        fontFamily = PoppinsFontFamily
                    )
                }
            }
        }
    }
}