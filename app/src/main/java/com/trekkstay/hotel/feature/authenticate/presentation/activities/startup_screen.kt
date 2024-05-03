package com.trekkstay.hotel.feature.authenticate.presentation.activities

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.hotel.R
import com.trekkstay.hotel.ui.theme.NunitoFontFamily
import com.trekkstay.hotel.ui.theme.TrekkStayBlue
import com.trekkstay.hotel.ui.theme.TrekkStayCyan

@Composable
fun StartupScreen(navController: NavHostController) {
    Column (
        modifier = Modifier.fillMaxSize().background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .size(420.dp)
        ) {
            Icon(
                ImageVector.vectorResource(R.drawable.filled_circle_ico),
                null,
                tint = TrekkStayBlue,
                modifier = Modifier.size(330.dp).align(Alignment.Center).offset(x = 10.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.startup_2),
                contentDescription = "Image Description",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(260.dp, 270.dp)
                    .clip(CircleShape)
                    .align(Alignment.Center)
            )
            Icon(
                ImageVector.vectorResource(R.drawable.filled_circle_ico),
                null,
                tint = Color(0xFFFFD700),
                modifier = Modifier.size(140.dp).align(Alignment.BottomStart).offset(x = -20.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.startup_1),
                contentDescription = "Image Description",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(120.dp, 120.dp)
                    .offset(x = -16.dp)
                    .clip(CircleShape)
                    .align(Alignment.BottomStart)
            )
            Icon(
                ImageVector.vectorResource(R.drawable.filled_circle_ico),
                null,
                tint = TrekkStayCyan,
                modifier = Modifier.size(140.dp).align(Alignment.TopEnd).offset(x = 20.dp, y = -20.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.startup_3),
                contentDescription = "Image Description",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(120.dp, 120.dp)
                    .offset(x = 25.dp, y = -5.dp)
                    .clip(CircleShape)
                    .align(Alignment.TopEnd)
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp, vertical = 20.dp)
        ) {
            Text(
                "Your Perfect Escape Awaits!",
                fontSize = 24.sp,
                fontFamily = NunitoFontFamily,
                fontWeight = FontWeight.Bold,
                color = TrekkStayCyan,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                "Your passport to a world of extraordinary hotel experiences. Join us today and unlock a realm of comfort, luxury, and adventure.",
                fontSize = 15.sp,
                color = Color.Black.copy(0.8f),
                fontFamily = NunitoFontFamily,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 10.dp)
        ) {
            Button(
                onClick = {
                    navController.navigate("register") {
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = TrekkStayCyan),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(15.dp)
            ) {
                Text(
                    text = "Start Exploring",
                    color = Color.White,
                    fontFamily = NunitoFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.width(10.dp))
                Icon(
                    ImageVector.vectorResource(R.drawable.top_right_arrow_ico),
                    contentDescription = null,
                    modifier = Modifier.size(25.dp)
                )
            }
            Text("Or")
            Button(
                onClick = {
                    navController.navigate("emp_register") {
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = TrekkStayBlue),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(15.dp)
            ) {
                Text(
                    text = "BECOME A PARTNER WITH US!",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                )
            }
        }

    }
}

@Preview(showBackground = true, showSystemUi = true, device = "spec:width=411dp,height=891dp")
@Composable
fun MyAppPreview() {
    StartupScreen(rememberNavController())
}
