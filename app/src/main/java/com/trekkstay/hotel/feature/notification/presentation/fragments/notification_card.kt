package com.trekkstay.hotel.feature.notification.presentation.fragments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hotel.R
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily
import com.trekkstay.hotel.ui.theme.TrekkStayBlue
import com.trekkstay.hotel.ui.theme.TrekkStayCyan

@Composable
fun NotificationCard(
    type: String,
    content: String,
) {
    var icon: ImageVector = when (type) {
        "book_done" -> ImageVector.vectorResource(R.drawable.book_done_ico)
        "cancel_done" -> ImageVector.vectorResource(R.drawable.book_clear_ico)
        "discount" -> ImageVector.vectorResource(R.drawable.discount_ico)
        else -> Icons.Default.Notifications
    }
    var icoColor: Color = when (type) {
        "book_done" -> Color(0xFF0FA958)
        "cancel_done" -> Color(0xFFEA523D)
        "discount" -> TrekkStayBlue
        else -> TrekkStayCyan
    }
    var title: String = when (type) {
        "book_done" -> "Reservation Successful"
        "cancel_done" -> "Reservation Canceled"
        "discount" -> "Discount"
        else -> "Notification"
    }
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(12.dp)
            )
            .background(Color(0xFFE4E4E4).copy(0.4f))
            .padding(15.dp)
    ) {
        if (icon != null) {
            Icon(
                icon,
                contentDescription = null,
                tint = icoColor,
                modifier = Modifier.size(50.dp)
            )
        }
        Column(
        ) {
            Text(
                title,
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp,
                color = Color.Black.copy(0.75f)
            )
            Text(
                content,
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 13.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = Color.Black.copy(0.6f)
            )
        }
    }
}