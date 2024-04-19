package com.trekkstay.hotel.feature.hotel.presentation.activities

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hotel.R
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily

@Composable
fun BookingDetailScreen(navController: NavController) {
    val hotelName = "Estabeez Hotel"
    val bookingID = "004Wf32QR"
    val customerName = "Bao Pham"
    val customerEmail = "vCqg6@example.com"
    val customerPhone = "+84 123 456 789"
    Column(
        modifier = Modifier
            .padding(top = 25.dp, bottom = 80.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Icon(
                Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = null,
                Modifier.clickable {
                    navController.popBackStack()
                })
            Text(
                text = "Booking Detail",
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 30.dp)
        ) {
            Text(
                text = hotelName,
                fontFamily = PoppinsFontFamily,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
            Image(
                painter = painterResource(id = R.drawable.qr_scanner),
                modifier = Modifier
                    .size(200.dp),
                contentDescription = "QR"
            )
            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color.Gray)) {
                        append("Booking ID: ")
                    }
                    append(bookingID)
                },
                fontFamily = PoppinsFontFamily,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .border(1.dp, Color(0xFFC4C4C4), shape = RoundedCornerShape(20.dp))
                    .padding(vertical = 20.dp, horizontal = 30.dp)
            ) {
                BookingInfoRow("Name", customerName)
                BookingInfoRow("Email", customerEmail)
                BookingInfoRow("Phone", customerPhone)
                Row() {
                    Column {
                        Text(
                            "Check In",
                        )
                        Text(
                            "Apr 17",
                        )
                    }
                    Column {
                        Text(
                            "Check Out",
                        )
                        Text(
                            "Apr 20",
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun BookingInfoRow(
    label: String,
    value: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = label,
            fontFamily = PoppinsFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 13.sp,
            color = Color.Gray,
            modifier = Modifier.width(120.dp)
        )
        Text(
            text = value,
            fontFamily = PoppinsFontFamily,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .weight(1f)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true, device = "spec:width=411dp,height=891dp")
@Composable
fun BookingDetailPreview() {
    BookingDetailScreen(rememberNavController())
}