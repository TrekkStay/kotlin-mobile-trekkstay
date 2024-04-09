package com.trekkstay.hotel.feature.authenticate.presentation.fragments

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily
import com.trekkstay.hotel.ui.theme.TrekkStayBlue
import com.trekkstay.hotel.ui.theme.TrekkStayCyan

@Composable
fun ProfileNavButton(
    title: String,
    icon: ImageVector,
    type: String,
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
        Icon(icon, contentDescription = null,tint = if (type == "hotel") TrekkStayBlue else if (type == "customer") TrekkStayCyan else Color.Transparent)
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