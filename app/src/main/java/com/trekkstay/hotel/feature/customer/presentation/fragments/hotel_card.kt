package com.trekkstay.hotel.feature.customer.presentation.fragments

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.google.gson.Gson
import com.trekkstay.hotel.feature.hotel.domain.entities.Hotel
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily

@Composable
fun HotelCard(
    hotel: Hotel? = null,
    navController: NavHostController,
) {
    if(hotel != null) {
        val roomPrice = if (hotel.room.isNotEmpty()) {
            hotel.room.first().originalPrice
        } else {

            1
        }.toString()
        Column(

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .size(200.dp, 170.dp)
                .shadow(2.dp, shape = RoundedCornerShape(10.dp))
                .background(Color.White, shape = RoundedCornerShape(10.dp))
                .padding(10.dp)
                .clickable {
                    navController.navigate("hotel_detail/${hotel.id}")
                }
        ) {
            AsyncImage(
                model = "https://www.usatoday.com/gcdn/-mm-/05b227ad5b8ad4e9dcb53af4f31d7fbdb7fa901b/c=0-64-2119-1259/local/-/media/USATODAY/USATODAY/2014/08/13/1407953244000-177513283.jpg?width=1320&height=746&fit=crop&format=pjpg&auto=webp",
                contentDescription = "This is an example image",
                modifier = Modifier
                    .size(180.dp, 100.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    hotel.name,
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 17.sp
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Icon(
                        Icons.Default.Star,
                        tint = Color(0xFFF3B95F),
                        modifier = Modifier.size(20.dp),
                        contentDescription = null
                    )
                    Text(
                        "5.0",
                        fontSize = 17.sp,
                        fontFamily = PoppinsFontFamily,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    Icon(
                        Icons.Outlined.LocationOn,
                        tint = Color(0xFF238C98),
                        contentDescription = null,
                        modifier = Modifier.size(15.dp)
                    )
                    Text(
                        text = hotel.description,
                        fontSize = 11.sp,
                        fontFamily = PoppinsFontFamily,
                        fontWeight = FontWeight.Medium
                    )
                }
                Text(
                    text = "$$roomPrice/night",
                    fontSize = 11.sp,
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
    else{
        Column(

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .size(200.dp, 170.dp)
                .shadow(2.dp, shape = RoundedCornerShape(10.dp))
                .background(Color.White, shape = RoundedCornerShape(10.dp))
                .padding(10.dp)
        ) {
            AsyncImage(
                model = "https://www.usatoday.com/gcdn/-mm-/05b227ad5b8ad4e9dcb53af4f31d7fbdb7fa901b/c=0-64-2119-1259/local/-/media/USATODAY/USATODAY/2014/08/13/1407953244000-177513283.jpg?width=1320&height=746&fit=crop&format=pjpg&auto=webp",
                contentDescription = "This is an example image",
                modifier = Modifier
                    .size(180.dp, 100.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "...",
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 17.sp
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Icon(
                        Icons.Default.Star,
                        tint = Color(0xFFF3B95F),
                        modifier = Modifier.size(20.dp),
                        contentDescription = null
                    )
                    Text(
                        "5.0",
                        fontSize = 17.sp,
                        fontFamily = PoppinsFontFamily,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    Icon(
                        Icons.Outlined.LocationOn,
                        tint = Color(0xFF238C98),
                        contentDescription = null,
                        modifier = Modifier.size(15.dp)
                    )
                    Text(
                        text = "...",
                        fontSize = 11.sp,
                        fontFamily = PoppinsFontFamily,
                        fontWeight = FontWeight.Medium
                    )
                }
                Text(
                    text = "$$0/night",
                    fontSize = 11.sp,
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}