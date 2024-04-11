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
import coil.compose.AsyncImage
import com.example.hotel.R
import com.trekkstay.hotel.feature.hotel.domain.entities.Room
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily
import com.trekkstay.hotel.ui.theme.TrekkStayBlue

@Composable
fun HotelRoomCard(
    room : Room
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(10.dp))
            .border(2.dp, TrekkStayBlue, shape = RoundedCornerShape(10.dp))
            .padding(15.dp)
            .clickable {
                //Navigate to edit room
            }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .size(135.dp)
                .clip(RoundedCornerShape(10.dp))
        ) {
            AsyncImage(
                model = "https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/880ae3c6-0414-481b-aead-5968a48a560d/dfuky40-bbfeb6ac-8675-4cf7-a0b0-4bdbbc0299d4.png/v1/fill/w_512,h_512,q_80,strp/a_i__art___pretty_girl_by_draxionmufara_dfuky40-fullview.jpg?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7ImhlaWdodCI6Ijw9NTEyIiwicGF0aCI6IlwvZlwvODgwYWUzYzYtMDQxNC00ODFiLWFlYWQtNTk2OGE0OGE1NjBkXC9kZnVreTQwLWJiZmViNmFjLTg2NzUtNGNmNy1hMGIwLTRiZGJiYzAyOTlkNC5wbmciLCJ3aWR0aCI6Ijw9NTEyIn1dXSwiYXVkIjpbInVybjpzZXJ2aWNlOmltYWdlLm9wZXJhdGlvbnMiXX0.CYceDA6vX83Rkq_rhdV3Q4ObSpHylffYnDMvoY6i2x8",
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