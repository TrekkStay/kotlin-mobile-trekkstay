package com.trekkstay.hotel.feature.hotel.presentation.fragments

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomerRoomOptSelector() {
    var isBotSheetVisible by remember { mutableStateOf(false) }
    var roomNumber by remember { mutableStateOf(1) }
    var adultNumber by remember { mutableStateOf(1) }
    var childNumber by remember { mutableStateOf(0) }
    val botSheetState = rememberModalBottomSheetState()

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .size(315.dp, 55.dp)
            .background(Color(0xFFC4C4C4).copy(0.65f))
            .clickable {
                isBotSheetVisible = true
            }
    ) {
        Icon(
            Icons.Sharp.List,
            contentDescription = null,
            modifier = Modifier
                .size(45.dp)
                .padding(8.dp)
        )
        Text(
            text = "${roomNumber} Room, ${adultNumber} Adults, ${childNumber} Children",
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp
        )
        if (isBotSheetVisible) {
            ModalBottomSheet(
                onDismissRequest = {
                    isBotSheetVisible = false
                },
                sheetState = botSheetState
            ) {
                OptionCounterRow(
                    label = "Room",
                    count = roomNumber,
                    onIncrease = { roomNumber += 1 },
                    onDecrease = { if (roomNumber > 1) roomNumber -= 1 })
                OptionCounterRow(
                    label = "Adults",
                    count = adultNumber,
                    onIncrease = { adultNumber += 1 },
                    onDecrease = { if (adultNumber > 1) adultNumber -= 1 })
                OptionCounterRow(
                    label = "Children",
                    count = childNumber,
                    onIncrease = { childNumber += 1 },
                    onDecrease = { if (childNumber > 0) childNumber -= 1 })
                Spacer(modifier = Modifier.size(30.dp))
            }
        }
    }
}

