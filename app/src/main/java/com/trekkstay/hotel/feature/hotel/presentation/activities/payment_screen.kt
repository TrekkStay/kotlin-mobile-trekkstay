package com.trekkstay.hotel.feature.hotel.presentation.activities

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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily
import com.trekkstay.hotel.ui.theme.TrekkStayCyan

@Composable
fun PaymentScreen(navHostController: NavHostController) {
    var paymentSelected by remember { mutableStateOf("") }
    val radioOptions = listOf("Pay Online with Momo","Pay At Hotel")
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
                            if (paymentSelected == method) {
                                paymentSelected = ""
                            } else {
                                paymentSelected = method
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
                    onClick = { },
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

@Preview(showBackground = true, showSystemUi = true, device = "spec:width=411dp,height=891dp")
@Composable
fun PaymentPreview() {
    PaymentScreen(rememberNavController())
}