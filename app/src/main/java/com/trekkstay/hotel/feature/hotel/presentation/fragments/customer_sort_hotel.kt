package com.trekkstay.hotel.feature.hotel.presentation.fragments

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hotel.R
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily
import com.trekkstay.hotel.ui.theme.TrekkStayCyan

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomerSortHotel() {
    var isBotSheetVisible by remember { mutableStateOf(false) }
    val botSheetState = rememberModalBottomSheetState()
    val radioOptions = listOf("Price Increase","Price Decrease")
    var selectedOption by remember { mutableStateOf("") }
    OutlinedButton(
        contentPadding = PaddingValues(10.dp),
        border = BorderStroke(2.dp, TrekkStayCyan),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = if (selectedOption == "") Color.Transparent else TrekkStayCyan,
            contentColor = if (selectedOption == "") TrekkStayCyan else Color.White
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
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            RadioButton(
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = TrekkStayCyan,
                                    unselectedColor = TrekkStayCyan
                                ),
                                selected = (sorter == selectedOption),
                                onClick = { selectedOption = sorter }
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
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth().padding(vertical = 20.dp),
                ) {
                    Button(
                        onClick = { selectedOption = "" },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = TrekkStayCyan.copy(0.25f),
                            contentColor = TrekkStayCyan
                        ),
                        contentPadding = PaddingValues(horizontal = 30.dp, vertical = 15.dp),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.width(160.dp)
                    ) {
                        Text(
                            text = "Reset",
                            fontSize = 17.sp,
                            fontFamily = PoppinsFontFamily,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Button(
                        onClick = {
                            //apply sort
                            isBotSheetVisible = false
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = TrekkStayCyan,
                            contentColor = Color.White
                        ),
                        contentPadding = PaddingValues(horizontal = 30.dp, vertical = 15.dp),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.width(160.dp)
                    ) {
                        Text(
                            text = "Apply Sort",
                            fontSize = 17.sp,
                            fontFamily = PoppinsFontFamily,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
                Spacer(modifier = Modifier.height(50.dp))
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, device = "spec:width=411dp,height=891dp")
@Composable
fun SortHotelPreview() {
    CustomerSortHotel()
}