package com.trekkstay.hotel

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hotel.R
import com.trekkstay.hotel.ui.theme.NunitoFontFamily
import com.trekkstay.hotel.ui.theme.TrekkStayCyan
import kotlinx.coroutines.delay

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SplashScreen()
        }
    }

    @Composable
    private fun SplashScreen() {
        LaunchedEffect(key1 = true) {
            delay(500)
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(TrekkStayCyan)
        ) {
            Image(
                painter = painterResource(id = R.drawable.hotel_location_splash),
                contentDescription = null,
                modifier = Modifier.size(130.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                "TrekkStay",
                fontFamily = NunitoFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 45.sp,
                color = Color.White,
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                "Stay your way!",
                fontFamily = NunitoFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                color = Color(0xFFFFD700),
            )
        }
    }
}