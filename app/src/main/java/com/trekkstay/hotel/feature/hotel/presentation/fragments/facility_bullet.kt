package com.trekkstay.hotel.feature.hotel.presentation.fragments

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hotel.R
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily
import com.trekkstay.hotel.ui.theme.TrekkStayCyan

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
        "Air Condition" -> ImageVector.vectorResource(R.drawable.air_ico)
        "Balcony" -> ImageVector.vectorResource(R.drawable.balcony_ico)
        "Bathtub" -> ImageVector.vectorResource(R.drawable.bathtub_ico)
        "Hairdryer" -> ImageVector.vectorResource(R.drawable.dry_ico)
        "Kitchen" -> ImageVector.vectorResource(R.drawable.kitchen_ico)
        "Slippers" -> ImageVector.vectorResource(R.drawable.slippers_ico)
        "Shower" -> ImageVector.vectorResource(R.drawable.shower_ico)
        "Non Smoking" -> ImageVector.vectorResource(R.drawable.non_smoke_ico)
        "Television" -> ImageVector.vectorResource(R.drawable.tv_ico)
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