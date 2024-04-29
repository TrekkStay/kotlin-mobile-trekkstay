package com.trekkstay.hotel.feature.reservation.presentation.fragments

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.trekkstay.hotel.feature.shared.Utils.formatPrice
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily
import com.trekkstay.hotel.ui.theme.TrekkStayCyan

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomerReservationCard(
    hotelImg: String,
    hotelName: String,
    destination: String,
    type: String,
    price: Double
) {
    val formattedPrice = formatPrice(price)

    var isBotSheetVisible by remember { mutableStateOf(false) }
    val botSheetState = rememberModalBottomSheetState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .size(150.dp)
            .shadow(5.dp, shape = RoundedCornerShape(10.dp))
            .background(Color.White, shape = RoundedCornerShape(10.dp))
            .padding(5.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .size(80.dp)
        ) {
            AsyncImage(
                model = hotelImg,
                contentDescription = "Reservation's hotel image",
                modifier = Modifier
                    .size(170.dp, 80.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(top = 5.dp, bottom = 5.dp)
            ) {
                Text(
                    hotelName,
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 17.sp
                )
                Text(
                    destination,
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 13.sp,
                    color = Color(0xFF303030).copy(0.65f)
                )
                when (type) {
                    "Upcoming" -> {
                        Text(
                            "22/01/23 - 25/01/23",
                            fontFamily = PoppinsFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            color = TrekkStayCyan
                        )
                    }

                    "Completed" -> {
                        Text(
                            text = "$$formattedPrice",
                            fontSize = 15.sp,
                            fontFamily = PoppinsFontFamily,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    "Cancelled" -> {
                        Text(
                            "Cancelled & refunded",
                            fontFamily = PoppinsFontFamily,
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                            color = Color(0xFFC82222).copy(0.6f)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            when (type) {
                "Upcoming" -> {}
                "Completed" -> {
                    Icon(
                        Icons.Default.CheckCircle,
                        contentDescription = "type icon",
                        tint = Color(0xFF0FA958)
                    )
                }

                "Cancelled" -> {
                    Icon(
                        Icons.Default.Warning,
                        contentDescription = "type icon",
                        tint = Color(0xFFE83F28).copy(0.9f)
                    )
                }
            }
            Spacer(modifier = Modifier.width(5.dp))
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .size(70.dp)
                .padding(start = 10.dp, end = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            when (type) {
                "Upcoming" -> {
                    OutlinedButton(
                        border = BorderStroke(2.dp, TrekkStayCyan),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = TrekkStayCyan
                        ),
                        onClick = {
                            isBotSheetVisible = true
                        }
                    ) {
                        Text(
                            "Cancel",
                            fontFamily = PoppinsFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 13.sp
                        )
                    }
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = TrekkStayCyan,
                            contentColor = Color.White
                        ),
                        onClick = { }
                    ) {
                        Text(
                            "View Booking",
                            fontFamily = PoppinsFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 13.sp
                        )
                    }

                    if (isBotSheetVisible) {
                        ModalBottomSheet(
                            onDismissRequest = {
                                isBotSheetVisible = false
                            },
                            sheetState = botSheetState
                        ) {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(20.dp),
                                modifier = Modifier.fillMaxWidth().padding(top = 5.dp, bottom = 70.dp)
                            ) {
                                Text(
                                    "Cancel Booking",
                                    fontFamily = PoppinsFontFamily,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color(0xFFDA3D3D).copy((0.7f)),
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillMaxWidth()
                                )
                                HorizontalDivider(thickness = 2.dp, color = Color(0xFFC4C4C4).copy(0.2f), modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 20.dp))
                                Text(
                                    "Are you sure you want to cancel this hotel booking?",
                                    fontFamily = PoppinsFontFamily,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillMaxWidth()
                                )
                                Text(
                                    "Only 80% would be refunded according to our cancellation policy.",
                                    fontFamily = PoppinsFontFamily,
                                    fontSize = 16.sp,
                                    color = Color.Black.copy(0.7f),
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillMaxWidth()
                                )
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    modifier = Modifier.fillMaxWidth(),
                                ) {
                                    Button(
                                        onClick = { /*TODO*/ },
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = TrekkStayCyan,
                                            contentColor = Color.White
                                        ),
                                        contentPadding = PaddingValues(horizontal = 30.dp, vertical = 15.dp),
                                        shape = RoundedCornerShape(10.dp)
                                    ) {
                                        Text(
                                            text = "Yes, Continue",
                                            fontSize = 15.sp,
                                            fontFamily = PoppinsFontFamily,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                "Completed" -> {
                    Spacer(modifier = Modifier.weight(1f))
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = TrekkStayCyan.copy(0.4f),
                            contentColor = Color(0xFF068E9D)
                        ),
                        onClick = { }
                    ) {
                        Text(
                            "Review & Rating",
                            fontFamily = PoppinsFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 13.sp
                        )
                    }
                }

                "Cancelled" -> {
                    Text(
                        "You've cancelled this hotel booking",
                        textAlign = TextAlign.Center,
                        fontFamily = PoppinsFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp,
                        color = Color(0xFFC82222).copy(0.8f),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}