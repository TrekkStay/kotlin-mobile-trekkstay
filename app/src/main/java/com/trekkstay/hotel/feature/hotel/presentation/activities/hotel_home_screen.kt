package com.trekkstay.hotel.feature.hotel.presentation.activities

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.hotel.R
import com.trekkstay.hotel.feature.hotel.presentation.states.hotel.HotelViewModel
import com.trekkstay.hotel.ui.theme.NunitoFontFamily
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily
import com.trekkstay.hotel.ui.theme.TrekkStayBlue

@Composable
fun HotelHomeScreen(hotelViewModel: HotelViewModel,navController: NavHostController) {
    val ownerName = "Bao Pham"
    val bookingNum = 125
    val roomNum = 125
    val staffNum = 200
    Column(
        verticalArrangement = Arrangement.spacedBy(15.dp),
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
                color = TrekkStayBlue,
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
                        tint = TrekkStayBlue,
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
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp)
        ) {
            Icon(
                Icons.Default.AccountCircle,
                contentDescription = null,
                tint = TrekkStayBlue,
                modifier = Modifier
                    .size(64.dp)
            )
            Text(
                text = "Welcome back, $ownerName!",
                fontSize = 16.sp,
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.Medium
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(15.dp),
            modifier = Modifier.padding(horizontal = 10.dp)
        ) {
            HotelHomeCard(
                title = "Today's Reservations",
                value = "$bookingNum bookings",
                color = Color(0x0085FF).copy(alpha = 0.55f),
                icon = ImageVector.vectorResource(R.drawable.box_ico)
            ) {
                navController.navigate("hotel_reservations")
            }
            HotelHomeCard(
                title = "Hotel's Rooms",
                value = "$roomNum rooms",
                color = Color(0xFF41B06E).copy(alpha = 0.55f),
                icon = ImageVector.vectorResource(R.drawable.bed_ico)
            ) {
                navController.navigate("hotel_room_manage")
            }
            HotelHomeCard(
                title = "Hotel's Staffs",
                value = "$staffNum employees",
                color = Color(0x7365C7).copy(alpha = 0.55f),
                icon = ImageVector.vectorResource(R.drawable.people_ico)
            ) {
                navController.navigate("hotel_emp_list")
            }
        }
    }
}

@Composable
private fun HotelHomeCard(
    title: String,
    value: String,
    color: Color,
    icon: ImageVector,
    navigate: () -> Unit = {}
) {
    Column(
        verticalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .background(color, shape = RoundedCornerShape(10.dp))
            .padding(horizontal = 15.dp, vertical = 20.dp)
            .clickable {
                navigate()
            }
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                icon,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(30.dp)
            )
            Text(
                title,
                fontSize = 24.sp,
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.Medium,
                color = Color.White,
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            value,
            fontWeight = FontWeight.SemiBold,
            fontFamily = PoppinsFontFamily,
            color = Color.Black,
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                "View More",
                fontWeight = FontWeight.SemiBold,
                fontFamily = PoppinsFontFamily,
                color = Color.Black,
                modifier = Modifier.padding(10.dp, 0.dp)
            )
            Icon(
                painter = painterResource(id = R.drawable.more),
                tint = Color.White,
                contentDescription = null
            )
        }
    }
}

