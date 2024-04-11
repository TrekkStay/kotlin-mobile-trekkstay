package com.trekkstay.hotel.feature.hotel.presentation.fragments

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily
import com.trekkstay.hotel.ui.theme.TrekkStayBlue

@Composable
fun FacilityChip(label: String, selected: Boolean, onSelectedChange: (Boolean) -> Unit) {
    FilterChip(
        onClick = { onSelectedChange(!selected) },
        label = {
            Text(
                label,
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp
            )
        },
        selected = selected,
        colors = FilterChipDefaults.filterChipColors(
            containerColor = Color.White,
            selectedContainerColor = TrekkStayBlue,
            labelColor = TrekkStayBlue,
            selectedLabelColor = Color.White
        ),
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(2.dp, TrekkStayBlue),
        modifier = Modifier.height(30.dp)
    )
}