package com.trekkstay.hotel.feature.hotel.presentation.activities

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

import com.example.hotel.R
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily
import com.trekkstay.hotel.ui.theme.TrekkStayBlue

@Composable
fun HotelProfileScreen(navController: NavHostController) {
    Column {
        Spacer(modifier = Modifier.height(25.dp))
        Text(
            text = "My account",
            fontSize = 20.sp,
            fontFamily = PoppinsFontFamily,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 25.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(Color(0xFFE4E4E4))
        )
        Column(
            modifier = Modifier.padding(horizontal = 25.dp,vertical = 20.dp)
        ) {
            HotelProfileButton("Edit My Hotel Information", Icons.Default.Info, onClick = {
                navController.navigate("hotel_create") {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            })
            Spacer(modifier = Modifier.height(20.dp))
            HotelProfileButton("Room Management", ImageVector.vectorResource(R.drawable.bed_ico), onClick = {
                navController.navigate("hotel_room_manage") {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            })
            Spacer(modifier = Modifier.height(20.dp))
            HotelProfileButton("Staff Management", Icons.Default.AccountBox)
            Spacer(modifier = Modifier.height(20.dp))
            HotelProfileButton("Statistic Report", ImageVector.vectorResource(R.drawable.bar_chart_ico))
            Spacer(modifier = Modifier.height(20.dp))
            HotelProfileButton("Promotion Management", ImageVector.vectorResource(R.drawable.discount_ico))
            Spacer(modifier = Modifier.height(20.dp))
            HotelProfileButton("Change Password", ImageVector.vectorResource(R.drawable.key_ico))
            Spacer(modifier = Modifier.height(20.dp))
            HotelProfileButton("Logout", Icons.AutoMirrored.Filled.ExitToApp)

        }
    }
}

@Composable
fun HotelProfileButton(
    title: String,
    icon: ImageVector,
    onClick: () -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(12.dp)
            )
            .background(Color(0xFFE4E4E4).copy(0.3f))
            .padding(15.dp)
            .clickable {
                onClick()
            }
    ) {
        Icon(icon, contentDescription = null, tint = TrekkStayBlue)
        Text(
            title,
            fontFamily = PoppinsFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 13.sp,
            color = Color.Black.copy(0.6f),
            modifier = Modifier.padding(start = 10.dp)
        )
    }
}

//@Preview(showBackground = true, showSystemUi = true, device = "spec:width=411dp,height=891dp")
//@Composable
//fun HotelProfileScreenPreview() {
//    HotelProfileScreen()
//}