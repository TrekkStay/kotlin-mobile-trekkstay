package com.trekkstay.hotel.feature.hotel.presentation.fragments

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hotel.R
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily
import com.trekkstay.hotel.ui.theme.TrekkStayBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HotelRoomOptSelector(
    onBedNumChange: (Int) -> Unit,
    onAdultNumberChange: (Int) -> Unit,
    onChildNumberChange: (Int) -> Unit
) {
    var isBotSheetVisible by remember { mutableStateOf(false) }
    var bedNum by remember { mutableIntStateOf(1) }
    var adultNumber by remember { mutableIntStateOf(1) }
    var childNumber by remember { mutableIntStateOf(0) }
    val botSheetState = rememberModalBottomSheetState()

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .background(Color.Transparent)
            .border(1.dp, TrekkStayBlue, shape = RoundedCornerShape(12.dp))
            .padding(horizontal = 10.dp)
            .clickable {
                isBotSheetVisible = true
            }
    ) {
        Icon(
            ImageVector.vectorResource(R.drawable.facility_ico),
            contentDescription = null,
            tint = TrekkStayBlue,
            modifier = Modifier
                .size(30.dp)
        )
        Text(
            text = "$bedNum Beds, $adultNumber Adults, $childNumber Children",
            fontFamily = PoppinsFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 13.sp,
            color = Color.Black.copy(0.6f)
        )
        if (isBotSheetVisible) {
            ModalBottomSheet(
                onDismissRequest = {
                    isBotSheetVisible = false
                },
                sheetState = botSheetState
            ) {
                OptionCounterRow(
                    label = "Bed",
                    count = bedNum,
                    onIncrease = { bedNum += 1; onBedNumChange(bedNum) },
                    onDecrease = { if (bedNum > 1) {bedNum -= 1 ; onBedNumChange(bedNum) }})
                OptionCounterRow(
                    label = "Adults",
                    count = adultNumber,
                    onIncrease = { adultNumber += 1 ; onAdultNumberChange(adultNumber)},
                    onDecrease = { if (adultNumber > 1) { adultNumber -= 1; onAdultNumberChange(adultNumber) } })
                OptionCounterRow(
                    label = "Children",
                    count = childNumber,
                    onIncrease = { childNumber += 1;onChildNumberChange(childNumber) },
                    onDecrease = { if (childNumber > 0) {childNumber -= 1 ; onChildNumberChange(childNumber)} })
                Spacer(modifier = Modifier.size(30.dp))
            }
        }
    }
}