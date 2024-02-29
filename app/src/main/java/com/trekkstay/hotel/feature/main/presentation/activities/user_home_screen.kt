package com.trekkstay.hotel.feature.main.presentation.activities

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.trekkstay.hotel.feature.main.presentation.fragments.BotNavBar
import com.trekkstay.hotel.feature.main.presentation.fragments.DestinationCard
import com.trekkstay.hotel.feature.main.presentation.fragments.HotelCard
import com.trekkstay.hotel.ui.theme.NunitoFontFamily
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily
import com.trekkstay.hotel.ui.theme.TrekkStayCyan

@Composable
fun UserHomeScreen() {
    Scaffold(
        bottomBar = {
            BotNavBar()
        }
    ) { _ ->
        Column(
            modifier = Modifier
                .padding(top = 15.dp, bottom = 70.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp),
            ) {
                Text(
                    text = "TrekkStay",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = TrekkStayCyan,
                    fontFamily = NunitoFontFamily
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(5.dp),
                    modifier = Modifier
                        .padding(end = 10.dp)
                ) {
                    Text(
                        text = "Current Location",
                        fontFamily = PoppinsFontFamily,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF303030),
                        modifier = Modifier.alpha(0.24f)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Outlined.LocationOn,
                            tint = TrekkStayCyan,
                            contentDescription = null
                        )
                        Text(
                            text = "Ho Chi Minh",
                            fontFamily = PoppinsFontFamily,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(15.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp)
                    .border(1.dp, Color(0xFFE3E3E4), RoundedCornerShape(20.dp))
                    .padding(15.dp),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    AsyncImage(
                        model = "https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/880ae3c6-0414-481b-aead-5968a48a560d/dfuky40-bbfeb6ac-8675-4cf7-a0b0-4bdbbc0299d4.png/v1/fill/w_512,h_512,q_80,strp/a_i__art___pretty_girl_by_draxionmufara_dfuky40-fullview.jpg?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7ImhlaWdodCI6Ijw9NTEyIiwicGF0aCI6IlwvZlwvODgwYWUzYzYtMDQxNC00ODFiLWFlYWQtNTk2OGE0OGE1NjBkXC9kZnVreTQwLWJiZmViNmFjLTg2NzUtNGNmNy1hMGIwLTRiZGJiYzAyOTlkNC5wbmciLCJ3aWR0aCI6Ijw9NTEyIn1dXSwiYXVkIjpbInVybjpzZXJ2aWNlOmltYWdlLm9wZXJhdGlvbnMiXX0.CYceDA6vX83Rkq_rhdV3Q4ObSpHylffYnDMvoY6i2x8",
                        contentDescription = "User Avatar",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(64.dp)
                            .clip(CircleShape)
                    )
                    Text(
                        text = "Hello User",
                        fontSize = 16.sp,
                        fontFamily = PoppinsFontFamily,
                        fontWeight = FontWeight.Medium
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .size(280.dp, 45.dp)
                            .background(
                                Color(0xFFC4C4C4).copy(alpha = 0.65f),
                                RoundedCornerShape(15.dp)
                            )
                            .padding(10.dp)
                            .clickable { }
                    ) {
                        Text(
                            text = "Search For Hotels",
                            color = Color(0xFF303030).copy(alpha = 0.24f),
                            fontSize = 16.sp,
                            fontFamily = PoppinsFontFamily,
                            fontWeight = FontWeight.Medium
                        )
                        Icon(Icons.Default.Search, contentDescription = null)
                    }
                    IconButton(
                        modifier = Modifier.border(2.dp, Color(0xFFB8B8B9).copy(alpha = 0.42f)),
                        onClick = { /*TODO*/ }) {
                        Icon(
                            Icons.Default.Menu,
                            contentDescription = null,
                            tint = TrekkStayCyan
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text("Near me")
                Text("Recommended")
                Text("Popular")
            }
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(10.dp)
            ) {
                items(5) {
                    HotelCard(
                        name = "Muong Thanh",
                        destination = "Ho Chi Minh",
                        rating = 5.0,
                        price = 250.0,
                        imgUrl = "https://www.usatoday.com/gcdn/-mm-/05b227ad5b8ad4e9dcb53af4f31d7fbdb7fa901b/c=0-64-2119-1259/local/-/media/USATODAY/USATODAY/2014/08/13/1407953244000-177513283.jpg?width=1320&height=746&fit=crop&format=pjpg&auto=webp"
                    )
                }
            }
            Text(
                text = "See all",
                textAlign = TextAlign.End,
                color = TrekkStayCyan,
                fontSize = 18.sp,
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 10.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Explore",
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp)
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(10.dp)
            ) {
                items(3) {
                    DestinationCard(
                        name = "Da Lat",
                        hotelNum = 186,
                        imgUrl = "https://www.usatoday.com/gcdn/-mm-/05b227ad5b8ad4e9dcb53af4f31d7fbdb7fa901b/c=0-64-2119-1259/local/-/media/USATODAY/USATODAY/2014/08/13/1407953244000-177513283.jpg?width=1320&height=746&fit=crop&format=pjpg&auto=webp"
                    )
                }
            }
            Text(
                text = "See all",
                textAlign = TextAlign.End,
                color = TrekkStayCyan,
                fontSize = 18.sp,
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 10.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MyAppPreview() {
    UserHomeScreen()
}
