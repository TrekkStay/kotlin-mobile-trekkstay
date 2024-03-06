package com.trekkstay.hotel.feature.search.presentation.fragments

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.sharp.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hotel.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoomOptionSelectorModal() {
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
                CounterRow(
                    label = "Room",
                    count = roomNumber,
                    onIncrease = { roomNumber += 1 },
                    onDecrease = { if (roomNumber > 1) roomNumber -= 1 })
                CounterRow(
                    label = "Adults",
                    count = adultNumber,
                    onIncrease = { adultNumber += 1 },
                    onDecrease = { if (adultNumber > 1) adultNumber -= 1 })
                CounterRow(
                    label = "Children",
                    count = childNumber,
                    onIncrease = { childNumber += 1 },
                    onDecrease = { if (childNumber > 0) childNumber -= 1 })
                Spacer(modifier = Modifier.size(30.dp))
            }
        }
    }
}

@Composable
fun CounterRow(
    label: String,
    count: Int,
    onDecrease: () -> Unit,
    onIncrease: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = label, modifier = Modifier.padding(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onDecrease) {
                Icon(painter = painterResource(id = R.drawable.baseline_remove_24), contentDescription = null)
            }
            Text("$count", modifier = Modifier.padding(8.dp))
            IconButton(onClick = onIncrease) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
        }
    }
}