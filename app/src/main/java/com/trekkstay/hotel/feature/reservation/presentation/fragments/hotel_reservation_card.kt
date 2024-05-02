package com.trekkstay.hotel.feature.reservation.presentation.fragments

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.hotel.R
import com.trekkstay.hotel.feature.shared.Utils
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily
import com.trekkstay.hotel.ui.theme.TrekkStayBlue

@Composable
fun HotelReservationCard(
    reservationId: String,
    roomImg: String,
    customerId: String,
    customerName: String,
    roomType: String,
    checkIn: String,
    checkOut: String,
    price: Double,
    type: String,
    navController: NavController
) {
    val formattedPrice = Utils.formatPrice(price)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .size(180.dp)
            .background(Color.White, shape = RoundedCornerShape(10.dp))
            .border(1.dp, Color.Black.copy(0.2f), shape = RoundedCornerShape(10.dp))
            .padding(5.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .size(100.dp)
        ) {
            AsyncImage(
                model = roomImg,
                contentDescription = "Reservation's hotel image",
                modifier = Modifier
                    .size(150.dp, 100.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(top = 5.dp, bottom = 5.dp)
            ) {
                Text(
                    customerId,
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 17.sp
                )
                Text(
                    customerName,
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 13.sp,
                    color = Color(0xFF303030).copy(0.65f)
                )
                Text(
                    roomType,
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 13.sp,
                    color = Color(0xFF303030).copy(0.65f)
                )
                Text(
                    "${formatDate(checkIn)} - ${formatDate(checkOut)}",
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 13.sp,
                    color = Color(0xFF303030).copy(0.65f)
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            when (type) {
                "Upcoming" -> {
                    Icon(
                        ImageVector.vectorResource(R.drawable.filled_circle_ico),
                        contentDescription = "type icon",
                        tint = TrekkStayBlue
                    )
                }
                "Completed" -> {
                    Icon(
                        Icons.Default.CheckCircle,
                        contentDescription = "type icon",
                        tint = TrekkStayBlue
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
                if (type == "Upcoming" || type == "Completed") {
                Text(
                    "$$formattedPrice",
                    fontSize = 18.sp,
                    fontFamily = PoppinsFontFamily,
                    color = TrekkStayBlue,
                    fontWeight = FontWeight.Medium
                )
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = TrekkStayBlue,
                        contentColor = Color.White
                    ),
                    onClick = {
                        navController.navigate("hotel_reservation_detail/${reservationId}")
                    }
                ) {
                    Text(
                        "View Booking",
                        fontFamily = PoppinsFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp
                    )
                }
            } else if (type == "Cancelled") {
                Text(
                    "$customerName cancelled this hotel booking",
                    textAlign = TextAlign.Center,
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp,
                    color = Color(0xFFC82222).copy(0.8f),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
