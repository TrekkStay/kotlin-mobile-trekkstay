package com.trekkstay.hotel.feature.hotel.presentation.fragments

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hotel.R
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily
import com.trekkstay.hotel.ui.theme.TrekkStayCyan

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomerSortHotel(
    sortCriteria: String,
    onSort: (String) -> Unit
) {
    var isBotSheetVisible by remember { mutableStateOf(false) }
    val botSheetState = rememberModalBottomSheetState()
    val radioOptions = listOf("Price Increase","Price Decrease")
    OutlinedButton(
        contentPadding = PaddingValues(10.dp),
        border = BorderStroke(2.dp, TrekkStayCyan),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = if (sortCriteria == "") Color.Transparent else TrekkStayCyan,
            contentColor = if (sortCriteria == "") TrekkStayCyan else Color.White
        ),
        modifier = Modifier.width(125.dp),
        onClick = {
            isBotSheetVisible = true
        }
    ) {
        Text(
            text = "Sort",
            fontFamily = PoppinsFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp
        )
        Spacer(modifier = Modifier.width(10.dp))
        Icon(ImageVector.vectorResource(R.drawable.sort_ico), contentDescription = null)
        if (isBotSheetVisible) {
            ModalBottomSheet(
                onDismissRequest = {
                    isBotSheetVisible = false
                },
                sheetState = botSheetState,
            ) {
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "Sort",
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))
                HorizontalDivider(color = Color(0xFFE4E4E4), thickness = 2.dp, modifier = Modifier.padding(horizontal = 25.dp))
                Column (
                    modifier = Modifier.padding(horizontal = 40.dp,vertical = 20.dp)
                ) {
                    radioOptions.forEach { sorter ->
                        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                if (sortCriteria == sorter) {
                                    onSort("")
                                } else {
                                    onSort(sorter)
                                }
                            }) {
                            RadioButton(
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = TrekkStayCyan,
                                    unselectedColor = TrekkStayCyan
                                ),
                                selected = (sorter == sortCriteria),
                                onClick = {
                                    if (sortCriteria == sorter) {
                                        onSort("")
                                    } else {
                                        onSort(sorter)
                                    }
                                }
                            )
                            Text(
                                text = sorter,
                                fontFamily = PoppinsFontFamily,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                    }
                }
                HorizontalDivider(color = Color(0xFFE4E4E4), thickness = 2.dp, modifier = Modifier.padding(horizontal = 25.dp))
                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }
}