package com.trekkstay.hotel.feature.hotel.presentation.fragments

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import coil.compose.AsyncImage
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily
import com.trekkstay.hotel.ui.theme.TrekkStayCyan

@Composable
fun HotelDetail(
    imgUrl: String,
    name: String,
    destination: String,
    rating: Double,
    price: Double,
    salePrice: Double,
    comment: String,
    roomAvailable: Int,

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
            .size(380.dp, 400.dp)
            .shadow(2.dp, shape = RoundedCornerShape(10.dp))
            .background(Color.White, shape = RoundedCornerShape(10.dp))
            .padding(5.dp)
            .border(1.dp, TrekkStayCyan, shape = RoundedCornerShape(10.dp))
    ) {
        AsyncImage(
            model = imgUrl,
            contentDescription = "This is an example image",
            modifier = Modifier
                .size(180.dp, 50.dp)
                .clip(RoundedCornerShape(12.dp))
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    name,
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                    color = Color("#000000".toColorInt()),
                )
                Icon(
                    Icons.Default.Star,
                    tint = Color(0xFFF3B95F),
                    modifier = Modifier
                        .size(20.dp)
                        .padding(start = 0.dp),
                    contentDescription = null
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Row {
                    Icon(
                        Icons.Outlined.LocationOn,
                        tint = Color(0xFF238C98),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = destination,
                        fontSize = 14.sp,
                        fontFamily = PoppinsFontFamily,
                        fontWeight = FontWeight.Medium,
                        color = Color("#303030".toColorInt()),
                    )
                }
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier.padding(start = 10.dp)
            ) {
                Icon(
                    Icons.Default.Star,
                    tint = Color(0xFFF3B95F),
                    modifier = Modifier.size(20.dp),
                    contentDescription = null
                )
                Text(
                    rating.toString(),
                    fontSize = 14.sp,
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.Bold,
                    color = TrekkStayCyan
                )
                Text(
                    comment.toString(),
                    fontSize = 14.sp,
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.Medium
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Box(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .height(40.dp)
                        .width(150.dp)
                        .background(
                            color = TrekkStayCyan,
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Only 2 Room lefts",
                        modifier = Modifier.padding(end = 8.dp),
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(end=20.dp),
                horizontalArrangement = Arrangement.End,

            ) {
                Row {
                    Text(
                        text = "đ $price",
                        fontSize = 14.sp,
                        fontFamily = PoppinsFontFamily,
                        fontWeight = FontWeight.Medium,
                        textDecoration = TextDecoration.LineThrough,
                        modifier = Modifier.padding(end=10.dp),
                        color = Color.Red
                    )
                }
                Text(
                    text = "đ $salePrice",
                    fontSize = 24.sp,
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.Medium,
                    color = TrekkStayCyan
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Preview
@Composable
fun HotelCardPreview() {
    HotelDetail(
        imgUrl = "https://www.navadahotel.com/FileStorage/Room/Thumbnail/DSC_2755-HDR.jpg",
        name = "Ruby Saigon Hotel - Ben Thanh",
        destination = "District 1 - Ho Chi Minh",
        rating = 8.1,
        price = 1000000.0,
        salePrice = 425000.0,
        comment = "Very good",
        roomAvailable = 2,
    )
}