package com.trekkstay.hotel.feature.authenticate.presentation.activities

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.hotel.R
import com.trekkstay.hotel.feature.authenticate.presentation.fragments.ProfileNavButton
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily

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
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.padding(horizontal = 25.dp, vertical = 20.dp)
        ) {
            ProfileNavButton(
                "Edit My Hotel Information",
                Icons.Default.Info,
                type = "hotel",
                onClick = {
                    navController.navigate("hotel_create") {
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true
                    }
                })
            ProfileNavButton(
                "Room Management",
                ImageVector.vectorResource(R.drawable.bed_ico),
                type = "hotel",
                onClick = {
                    navController.navigate("hotel_room_manage") {
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true
                    }
                })
            ProfileNavButton("Staff Management", Icons.Default.AccountBox, type = "hotel")
            ProfileNavButton(
                "Statistic Report",
                ImageVector.vectorResource(R.drawable.bar_chart_ico), type = "hotel"
            )
            ProfileNavButton(
                "Promotion Management",
                ImageVector.vectorResource(R.drawable.discount_ico), type = "hotel"
            )
            ProfileNavButton("Change Password", ImageVector.vectorResource(R.drawable.key_ico), type = "hotel")
            ProfileNavButton("Sign out", Icons.AutoMirrored.Filled.ExitToApp, type = "hotel")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, device = "spec:width=411dp,height=891dp")
@Composable
fun ProfileScreenPreview() {
    val navController = rememberNavController()
    HotelProfileScreen(navController = navController)
}