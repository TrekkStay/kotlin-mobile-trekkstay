package com.trekkstay.hotel.feature.main.presentation.fragments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily

@Composable
fun DestinationCard(
    name: String,
    hotelNum: Int,
    imgUrl: String
) {
    Row(
        modifier = Modifier
            .size(160.dp, 130.dp)
            .shadow(2.dp, shape = RoundedCornerShape(10.dp))
            .background(Color.White, shape = RoundedCornerShape(10.dp))
            .padding(10.dp),
    ) {
        AsyncImage(
            model = imgUrl,
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .size(60.dp, 130.dp)
                .clip(RoundedCornerShape(12.dp))
        )
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(5.dp)
                .fillMaxHeight()
        ) {
            Text(
                "$hotelNum Hotels",
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF303030).copy(alpha = 0.55f),
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                "In",
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF303030).copy(alpha = 0.55f),
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                name,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                fontFamily = PoppinsFontFamily,
                color = Color.Black,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}