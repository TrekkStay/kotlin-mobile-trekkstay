package com.trekkstay.hotel.feature.reservation.presentation.fragments

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily
import com.trekkstay.hotel.ui.theme.TrekkStayCyan

@Composable
fun ReservationCard(
    hotelName: String,
    destination: String,
    type: String,
    price: Double
) {
    val formattedPrice = if (price % 1 == 0.0) {
        price.toInt().toString()
    } else {
        price.toString()
    }
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
                model = "https://www.usatoday.com/gcdn/-mm-/05b227ad5b8ad4e9dcb53af4f31d7fbdb7fa901b/c=0-64-2119-1259/local/-/media/USATODAY/USATODAY/2014/08/13/1407953244000-177513283.jpg?width=1320&height=746&fit=crop&format=pjpg&auto=webp",
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
                "Upcoming" -> { }
                "Completed" -> {
                    Icon(Icons.Default.CheckCircle, contentDescription = "type icon", tint = Color(0xFF0FA958))
                }
                "Cancelled" -> {
                    Icon(Icons.Default.Warning, contentDescription = "type icon", tint = Color(0xFFE83F28).copy(0.9f))
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
                        onClick = { }
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