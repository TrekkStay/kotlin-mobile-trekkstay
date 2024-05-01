package com.trekkstay.hotel.feature.hotel.presentation.fragments

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.DateRangePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trekkstay.hotel.core.storage.LocalStore
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily
import com.trekkstay.hotel.ui.theme.TrekkStayCyan
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateRangeSelector(
    type: String,
    startDate: Long,
    endDate: Long,
    onDateRangeSelected: (Pair<Long, Long>) -> Unit
) {
    var isBotSheetVisible by remember { mutableStateOf(false) }
    val state = rememberDateRangePickerState(initialSelectedStartDateMillis = startDate, initialSelectedEndDateMillis = endDate)
    val botSheetState = rememberModalBottomSheetState()
    val context = LocalContext.current
    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        if (type == "search") {
            SearchDateInput("Check-in", state) {
                isBotSheetVisible = true
            }
            SearchDateInput("Check-out", state) {
                isBotSheetVisible = true
            }
        } else if (type == "book") {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
                    .clickable {
                        isBotSheetVisible = true
                    }
            ) {
                if (state.selectedEndDateMillis != null && state.selectedStartDateMillis != null) {
                    BookDateCol(
                        label = "Check In",
                        formatDateFromMillis(state.selectedStartDateMillis!!)
                    )
                    Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null)
                    BookDateCol(
                        label = "Check Out",
                        formatDateFromMillis(state.selectedEndDateMillis!!)
                    )
                } else {
                    Text(
                        "Select Check In & Check Out Date",
                        color = TrekkStayCyan,
                        fontFamily = PoppinsFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(vertical = 10.dp)
                    )
                }
            }
        }
        if (isBotSheetVisible) {
            ModalBottomSheet(
                onDismissRequest = {
                    isBotSheetVisible = false
                },
                sheetState = botSheetState
            ) {
                DateRangePicker(
                    state = state)
            }
        }
    }
    LaunchedEffect(state.selectedStartDateMillis, state.selectedEndDateMillis) {
        if (state.selectedStartDateMillis != null && state.selectedEndDateMillis != null) {
            onDateRangeSelected(Pair(state.selectedStartDateMillis!!, state.selectedEndDateMillis!!))
            if (type == "search") {
                LocalStore.saveKey(
                    context,
                    "search_start_date",
                    state.selectedStartDateMillis.toString()
                )
                LocalStore.saveKey(
                    context,
                    "search_end_date",
                    state.selectedEndDateMillis.toString()
                )
            }
            isBotSheetVisible = false
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun formatDateFromMillis(millis: Long): String {
    val instant = Instant.ofEpochMilli(millis)
    val localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
    val formatter = DateTimeFormatter.ofPattern("EEE, MMM dd, yyyy")
    return localDateTime.format(formatter)
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchDateInput(label: String, state: DateRangePickerState, onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFFC4C4C4).copy(0.65f))
            .size(315.dp, 55.dp)
            .clickable {
                onClick()
            },
    ) {
        Icon(
            Icons.Default.DateRange, contentDescription = null, modifier = Modifier
                .size(45.dp)
                .padding(8.dp)
        )
        Column {
            Text(
                label,
                color = Color(0xFF303030).copy(0.5f),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = PoppinsFontFamily
            )
            val selectedDateMillis =
                if (label == "Check-in") state.selectedStartDateMillis else state.selectedEndDateMillis
            if (selectedDateMillis != null) {
                Text(
                    formatDateFromMillis(selectedDateMillis),
                    color = Color(0xFF238C98).copy(0.9f),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = PoppinsFontFamily
                )
            } else {
                Text(
                    "Choose a date",
                    color = Color(0xFF238C98).copy(0.9f),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = PoppinsFontFamily
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun BookDateCol(
    label: String,
    date: String,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            label,
            fontFamily = PoppinsFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 13.sp,
            color = Color.Gray,
        )
        Text(
            date,
            fontFamily = PoppinsFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 13.sp,
            color = Color.White,
            modifier = Modifier
                .background(TrekkStayCyan, shape = RoundedCornerShape(5.dp))
                .padding(10.dp)
        )
    }
}