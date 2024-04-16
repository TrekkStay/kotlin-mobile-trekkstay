package com.trekkstay.hotel.feature.hotel.presentation.activities

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.trekkstay.hotel.feature.hotel.presentation.fragments.HotelEmpCard
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily
import com.trekkstay.hotel.ui.theme.TrekkStayBlue

@Composable
fun HotelEmpListScreen(navController: NavHostController) {
    Scaffold(
        modifier = Modifier.padding(bottom = 70.dp),
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(10.dp),
                shape = RoundedCornerShape(30.dp),
                contentColor = TrekkStayBlue,
                containerColor = Color(0xFF89CFF3),
                onClick = {
                    navController.navigate("hotel_emp_create")
                },
            ) {
                Icon(Icons.Filled.Add, "Floating action button.")
            }
        }
    ) { _ ->
        Column {
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    navController.navigate("hotel_profile")
                }) {
                    Icon(
                        Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = "backFromRoomManage"
                    )
                }
                Text(
                    text = "Your employees",
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp, vertical = 10.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                HotelEmpCard()
                HotelEmpCard()
                HotelEmpCard()
                HotelEmpCard()
            }
        }
    }
}