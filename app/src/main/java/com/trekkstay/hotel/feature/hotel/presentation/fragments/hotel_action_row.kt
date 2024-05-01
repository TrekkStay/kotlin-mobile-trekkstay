package com.trekkstay.hotel.feature.hotel.presentation.fragments

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily
import com.trekkstay.hotel.ui.theme.TrekkStayBlue

@Composable
fun HotelActionRow(
    label: String,
    leadingIcon: ImageVector,
    trailingIcon: ImageVector = Icons.Default.KeyboardArrowDown,
    clickHandler: () -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End,
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .border(1.dp, TrekkStayBlue, RoundedCornerShape(12.dp))
            .clickable {
                clickHandler()
            }
    )
    {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .padding(15.dp)
                .weight(1f)
        ) {
            Icon(
                leadingIcon,
                contentDescription = null,
                tint = TrekkStayBlue
            )
            Text(
                label,
                fontFamily = PoppinsFontFamily,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black.copy(0.6f)
            )
        }

        VerticalDivider(
            color = TrekkStayBlue,
            thickness = 2.dp,
            modifier = Modifier
                .fillMaxHeight()
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(55.dp)

        ) {
            Icon(
                trailingIcon,
                contentDescription = null,
                tint = TrekkStayBlue,
                modifier = Modifier.size(30.dp)
            )
        }
    }
}