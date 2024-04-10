package com.trekkstay.hotel.feature.hotel.presentation.activities

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hotel.R
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily
import com.trekkstay.hotel.ui.theme.TrekkStayCyan

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HotelDetailScreen() {
    var checkInTime = "14:00"
    var checkOutTime = "12:00"
    var expandedDesc by remember { mutableStateOf(false) }
    Box() {

    }
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .size(200.dp)
        ) {
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp, vertical = 10.dp)
        ) {
            Text(
                text = "Ruby Saigon Hotel - Ben Thanh mkwam madwkd ",
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
                modifier = Modifier.weight(1f)
            )
            Icon(Icons.Default.Favorite, contentDescription = null)
        }
        HorizontalDivider(color = Color(0xFFB8B8B9), thickness = 2.dp)
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp, vertical = 20.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "What they offer",
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
            }

            FlowRow(
                verticalArrangement = Arrangement.spacedBy(15.dp),
                horizontalArrangement = Arrangement.Absolute.SpaceAround,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
            ) {
                FacilityBullet(label = "Free Wifi")
                FacilityBullet(label = "Food Service")
                FacilityBullet(label = "Spa Service")
                FacilityBullet(label = "Motorbike Rental")
                FacilityBullet(label = "Fitness Center")
                FacilityBullet(label = "Parking Area")
            }
        }
        HorizontalDivider(color = Color(0xFFB8B8B9), thickness = 2.dp)
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp, vertical = 20.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "Hotel description",
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
                ClickableText(
                    text = AnnotatedString("Show more"),
                    style = TextStyle(
                        color = TrekkStayCyan,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = PoppinsFontFamily
                    ),
                    onClick = {
                        expandedDesc = !expandedDesc
                    }
                )
            }
            Text(
                "The car parking and the Wi-Fi are always free, so you can stay in touch and come and go as you please. Conveniently situated in the Phú Nhuận part of Ho Chi Minh City, this property puts you close to attractions and interesting dining options. Don't leave before paying a visit to the famous War Remnants Museum. Rated with 5 stars, this high-quality property provides guests with access to massage, restaurant and hot tub on-site.",
                textAlign = TextAlign.Justify,
                fontFamily = PoppinsFontFamily,
                fontSize = 12.sp,
                maxLines = if (expandedDesc) Int.MAX_VALUE else 3,
                overflow = TextOverflow.Ellipsis
            )
        }
        HorizontalDivider(color = Color(0xFFB8B8B9), thickness = 2.dp)
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp, vertical = 20.dp)
        ) {
            Text(
                "Some helpful facts",
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                FactRow(
                    icon = ImageVector.vectorResource(R.drawable.time_ico),
                    label = "Check-in from",
                    value = checkInTime
                )
                FactRow(
                    icon = ImageVector.vectorResource(R.drawable.time_ico),
                    label = "Check-out until",
                    value = checkOutTime
                )
                FactRow(
                    icon = ImageVector.vectorResource(R.drawable.airplane_ticket_ico),
                    label = "Airport transfer fee",
                    value = "$15.99"
                )
                FactRow(
                    icon = ImageVector.vectorResource(R.drawable.city_ico),
                    label = "Distance from city center",
                    value = "2 km"
                )
                FactRow(
                    icon = ImageVector.vectorResource(R.drawable.time_ico),
                    label = "Travel time to airport",
                    value = "20 minutes"
                )
            }
        }
    }
}

@Composable
fun FacilityBullet(label: String) {
    val icon: ImageVector? = when (label) {
        "Fitness Center" -> ImageVector.vectorResource(R.drawable.fitness_ico)
        "Conference Room" -> ImageVector.vectorResource(R.drawable.conference_room_ico)
        "Parking Area" -> ImageVector.vectorResource(R.drawable.parking_ico)
        "Swimming Pool" -> ImageVector.vectorResource(R.drawable.pool_ico)
        "Free Wifi" -> ImageVector.vectorResource(R.drawable.wifi_ico)
        "Airport Transfer" -> ImageVector.vectorResource(R.drawable.airplane_ticket_ico)
        "Motorbike Rental" -> ImageVector.vectorResource(R.drawable.motor_ico)
        "Spa Service" -> ImageVector.vectorResource(R.drawable.spa_ico)
        "Food Service" -> ImageVector.vectorResource(R.drawable.food_ico)
        "Laundry Service" -> ImageVector.vectorResource(R.drawable.laundry_ico)
        else -> null
    }
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (icon != null) {
            Icon(icon, contentDescription = null, tint = TrekkStayCyan)
        }
        Text(
            text = label,
            fontFamily = PoppinsFontFamily,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black.copy(0.6f),
            fontSize = 12.sp
        )
    }
}

@Composable
fun FactRow(
    icon: ImageVector,
    label: String,
    value: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = TrekkStayCyan
        )
        Text(
            buildAnnotatedString {
                append("$label: ")
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(value)
                }
            },
            fontFamily = PoppinsFontFamily,
            fontSize = 16.sp,
        )
    }
}

@Preview(showBackground = true, showSystemUi = true, device = "spec:width=411dp,height=891dp")
@Composable
fun HotelDetailPreview() {
    HotelDetailScreen()
}