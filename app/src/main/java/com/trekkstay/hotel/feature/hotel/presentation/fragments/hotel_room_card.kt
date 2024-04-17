package com.trekkstay.hotel.feature.hotel.presentation.fragments

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.hotel.R
import com.google.gson.Gson
import com.trekkstay.hotel.feature.hotel.domain.entities.Room
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily
import com.trekkstay.hotel.ui.theme.TrekkStayBlue

@Composable
fun HotelRoomCard(
    room : Room, navController: NavHostController
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(10.dp))
            .border(2.dp, TrekkStayBlue, shape = RoundedCornerShape(10.dp))
            .padding(15.dp)
            .clickable {val gson = Gson()
                val roomJson = gson.toJson(room)
                navController.navigate("hotel_room_detail/$roomJson")
            }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .size(135.dp)
                .clip(RoundedCornerShape(10.dp))
        ) {
            AsyncImage(
                model = "https://www.usatoday.com/gcdn/-mm-/05b227ad5b8ad4e9dcb53af4f31d7fbdb7fa901b/c=0-64-2119-1259/local/-/media/USATODAY/USATODAY/2014/08/13/1407953244000-177513283.jpg?width=1320&height=746&fit=crop&format=pjpg&auto=webp",
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(135.dp)
            )
        }
        Text(
            room.type,
            fontFamily = PoppinsFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp
        )
        RoomInfoRow(
            icon = ImageVector.vectorResource(R.drawable.money_circle_ico),
            label = "Original price",
            value = room.originalPrice
        )
        RoomInfoRow(
            icon = ImageVector.vectorResource(R.drawable.discount_ico),
            label = "Discount rate",
            value = room.discountRate
        )
        RoomInfoRow(
            icon = ImageVector.vectorResource(R.drawable.box_ico),
            label = "Quantity",
            value = room.quantity
        )
    }
}

@Composable
private fun RoomInfoRow(
    icon: ImageVector,
    label: String,
    value: Int,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = TrekkStayBlue
        )
        Text(
            buildAnnotatedString {
                append("$label: ")
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(value.toString())
                }
            },
            fontFamily = PoppinsFontFamily,
            fontSize = 16.sp,
        )
    }
}