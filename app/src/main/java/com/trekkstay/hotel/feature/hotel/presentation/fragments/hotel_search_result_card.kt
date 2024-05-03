package com.trekkstay.hotel.feature.hotel.presentation.fragments

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.trekkstay.hotel.feature.hotel.domain.entities.Hotel
import com.trekkstay.hotel.feature.shared.Utils.formatPrice
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily
import com.trekkstay.hotel.ui.theme.TrekkStayCyan

@Composable
fun HotelSearchResultCard(hotel: Hotel, navController: NavController) {
    val hotelRating = "7.2"
    val originalPrice = if(hotel.room.isNotEmpty())hotel.room.first().originalPrice else 0
    val discountRate = if(hotel.room.isNotEmpty())hotel.room.first().discountRate else 0
    var liked by remember { mutableStateOf(false) }
    val likedTint = (if (liked) TrekkStayCyan else Color(0xFFB8B8B9))
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(10.dp))
            .border(1.5.dp, TrekkStayCyan, shape = RoundedCornerShape(10.dp))
            .padding(vertical = 15.dp)
            .clickable {
                navController.navigate("hotel_detail/${hotel.id}") {
                    launchSingleTop = true
                }
            }
    ) {
        AsyncImage(
            model = if(hotel.images.media.isNotEmpty())hotel.images.media.first() else {
                "https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/880ae3c6-0414-481b-aead-5968a48a560d/dfuky40-bbfeb6ac-8675-4cf7-a0b0-4bdbbc0299d4.png/v1/fill/w_512,h_512,q_80,strp/a_i__art___pretty_girl_by_draxionmufara_dfuky40-fullview.jpg?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7ImhlaWdodCI6Ijw9NTEyIiwicGF0aCI6IlwvZlwvODgwYWUzYzYtMDQxNC00ODFiLWFlYWQtNTk2OGE0OGE1NjBkXC9kZnVreTQwLWJiZmViNmFjLTg2NzUtNGNmNy1hMGIwLTRiZGJiYzAyOTlkNC5wbmciLCJ3aWR0aCI6Ijw9NTEyIn1dXSwiYXVkIjpbInVybjpzZXJ2aWNlOmltYWdlLm9wZXJhdGlvbnMiXX0.CYceDA6vX83Rkq_rhdV3Q4ObSpHylffYnDMvoY6i2x8"
            },
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
                .size(150.dp)
                .clip(RoundedCornerShape(10.dp))
                .border(1.dp, Color(0xFFB8B8B9), shape = RoundedCornerShape(10.dp))
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 15.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(3.dp),
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .weight(1f)
            ) {
                Text(
                    hotel.name,
                    fontSize = 17.sp,
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 2,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.fillMaxWidth()
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        Icons.Default.Place,
                        contentDescription = null,
                        tint = Color(0xFF303030).copy(alpha = 0.5f),
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        hotel.addressDetail,
                        fontSize = 13.sp,
                        fontFamily = PoppinsFontFamily,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF303030).copy(alpha = 0.5f),
                    )
                }

            }
            IconButton(onClick = { liked = !liked }) {
                Icon(
                    Icons.Default.Favorite,
                    contentDescription = null,
                    tint = likedTint,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
        Box {
            HorizontalDivider(color = TrekkStayCyan, thickness = 1.5.dp, modifier = Modifier.align(
                Alignment.Center))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp)
            ) {
                Text(
                    hotelRating,
                    fontSize = 12.sp,
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    modifier = Modifier
                        .background(
                            TrekkStayCyan,
                            shape = CircleShape
                        )
                        .padding(8.dp)
                )
                Text(
                    "Very good",
                    fontSize = 12.sp,
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    color = TrekkStayCyan,
                    modifier = Modifier.background(Color.White)
                )
            }
        }
        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
        ) {
            Text(
                if(hotel.neighbourhood!= null){"${if(hotel.neighbourhood.distance<1.0){"${(hotel.neighbourhood.distance*1000.0).toInt()} m"}else{"${String.format("%.1f", hotel.neighbourhood.distance)} km"}} to ${hotel.neighbourhood.name}"} else {
                    ""
                },
                color = TrekkStayCyan,
                fontFamily = PoppinsFontFamily,
                fontSize = 12.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                "$ ${formatPrice(originalPrice.toDouble())}",
                fontFamily = PoppinsFontFamily,
                fontSize = 12.sp,
                textDecoration = TextDecoration.LineThrough
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                "$ ${formatPrice(originalPrice - (originalPrice * (discountRate / 100.0)))}",
                color = TrekkStayCyan,
                fontWeight = FontWeight.SemiBold,
                fontFamily = PoppinsFontFamily,
                fontSize = 20.sp
            )
        }
    }
}
