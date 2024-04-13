package com.trekkstay.hotel.feature.authenticate.presentation.activities

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.hotel.R
import com.trekkstay.hotel.config.router.AppRouter
import com.trekkstay.hotel.feature.authenticate.presentation.fragments.ProfileNavButton
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily
import com.trekkstay.hotel.ui.theme.TrekkStayCyan

@Composable
fun CustomerProfileScreen(navController: NavHostController) {
    Column (
        modifier = Modifier.padding(top = 25.dp)
    ) {
        Text(
            text = "My account",
            fontSize = 20.sp,
            color = TrekkStayCyan,
            fontFamily = PoppinsFontFamily,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 25.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        HorizontalDivider(color = Color(0xFFE4E4E4), thickness = 2.dp)
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
            ProfileNavButton("Sign out", Icons.AutoMirrored.Filled.ExitToApp, type = "customer",
                onClick = {
                    AppRouter.navigateTo("start-up")
                })
        }
    }
}