package com.trekkstay.hotel.feature.notification.presentation.activities

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trekkstay.hotel.feature.notification.presentation.fragments.NotificationCard
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily
import com.trekkstay.hotel.ui.theme.TrekkStayCyan

@Composable
fun CustomerNotificationScreen() {
    Column(
        modifier = Modifier.padding(top = 25.dp, bottom = 90.dp)
    ) {
        Text(
            text = "Notification",
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
            verticalArrangement = Arrangement.spacedBy(15.dp),
            modifier = Modifier
                .padding(horizontal = 25.dp)
                .verticalScroll(rememberScrollState()),
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                "Today",
                fontSize = 16.sp,
                color = Color.Black.copy(0.8f),
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.SemiBold
            )
            NotificationCard("book_done", "Estabeez Hotel booking was successful")
            NotificationCard("book_done", "Estabeez Hotel booking was successful")
            NotificationCard("cancel_done", "You have canceled Ehiz hotel booking")
            NotificationCard("discount", "Ehiz hotel offers 30% discount")
            NotificationCard("book_done", "Estabeez Hotel booking was successful")
            Text(
                "Yesterday",
                fontSize = 16.sp,
                color = Color.Black.copy(0.8f),
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.SemiBold
            )
            NotificationCard("cancel_done", "You have canceled Ehiz hotel booking")
            NotificationCard("discount", "Ehiz hotel offers 30% discount")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, device = "spec:width=411dp,height=891dp")
@Composable
fun CustomerNotiPreview() {
    CustomerNotificationScreen()
}