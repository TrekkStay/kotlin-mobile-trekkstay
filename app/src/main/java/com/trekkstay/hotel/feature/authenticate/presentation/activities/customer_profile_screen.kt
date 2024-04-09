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
import androidx.compose.material.icons.filled.Favorite
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.hotel.R
import com.trekkstay.hotel.feature.authenticate.presentation.fragments.ProfileNavButton
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily

@Composable
fun CustomerProfileScreen(navController: NavHostController) {
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
            ProfileNavButton("My Favourite", Icons.Default.Favorite, type = "customer")
            ProfileNavButton(
                "Change Password",
                ImageVector.vectorResource(R.drawable.key_ico),
                type = "customer"
            )
            ProfileNavButton("Edit Information", Icons.Default.AccountBox, type = "customer")
            ProfileNavButton("Sign out", Icons.AutoMirrored.Filled.ExitToApp, type = "customer")
        }
    }
}