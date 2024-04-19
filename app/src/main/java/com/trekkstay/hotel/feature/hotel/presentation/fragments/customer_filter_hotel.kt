package com.trekkstay.hotel.feature.hotel.presentation.fragments

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hotel.R
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily
import com.trekkstay.hotel.ui.theme.TrekkStayCyan

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun CustomerFilterHotel() {
    var isBotSheetVisible by remember { mutableStateOf(false) }
    val botSheetState = rememberModalBottomSheetState()
    var selectedNeighborhood by remember { mutableStateOf("") }
    val neighborList = listOf(
        "Ben Thanh Market",
        "Nguyen Hue Street",
        "Xuan Huong Lake",
        "Love Valley",
        "Langbiang Mountain"
    )
    var selectedRatings by remember { mutableStateOf(listOf<Int>()) }
    val ratingList = listOf(3, 4, 5)
    var selectedOption by remember { mutableIntStateOf(0) }
    OutlinedButton(
        contentPadding = PaddingValues(10.dp),
        border = BorderStroke(2.dp, TrekkStayCyan),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = if (selectedOption == 0) Color.Transparent else TrekkStayCyan,
            contentColor = if (selectedOption == 0) TrekkStayCyan else Color.White
        ),
        modifier = Modifier.width(125.dp),
        onClick = {
            isBotSheetVisible = true
        }
    ) {
        Text(
            text = "Filter",
            fontFamily = PoppinsFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp
        )
        Spacer(modifier = Modifier.width(10.dp))
        if (selectedNeighborhood == "" && selectedRatings.isEmpty()) {
            Icon(ImageVector.vectorResource(R.drawable.filter_list_ico), contentDescription = null)
        } else {
            var neighborNum = (if (selectedNeighborhood != "") 1 else 0)
            selectedOption = neighborNum + selectedRatings.size
            Text(
                "$selectedOption",
                color = TrekkStayCyan,
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .background(Color.White,shape = CircleShape)
                    .padding(horizontal = 5.dp)
            )
        }
        if (isBotSheetVisible) {
            ModalBottomSheet(
                onDismissRequest = {
                    isBotSheetVisible = false
                },
                sheetState = botSheetState,
            ) {
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "Filter",
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))
                HorizontalDivider(
                    color = Color(0xFFE4E4E4),
                    thickness = 2.dp,
                    modifier = Modifier.padding(horizontal = 25.dp)
                )
                Column(
                    modifier = Modifier.padding(15.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                    ) {
                        Text(
                            text = "Neighborhood",
                            fontFamily = PoppinsFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 20.sp
                        )
                        IconButton(onClick = {
                            selectedNeighborhood = ""
                        }) {
                            Icon(
                                ImageVector.vectorResource(R.drawable.ico_filter_off),
                                contentDescription = null
                            )
                        }
                    }
                    FlowRow(
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                    ) {
                        neighborList.forEach { neighbor ->
                            val isSelected = neighbor == selectedNeighborhood
                            CustomerNeighborFilterChip(
                                label = neighbor,
                                selected = isSelected,
                                onSelectedChange = { selected ->
                                    selectedNeighborhood = selected
                                })
                        }
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                    ) {
                        Text(
                            text = "Review Rating",
                            fontFamily = PoppinsFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 20.sp
                        )
                        IconButton(onClick = {
                            selectedRatings = emptyList()
                        }) {
                            Icon(
                                ImageVector.vectorResource(R.drawable.ico_filter_off),
                                contentDescription = null
                            )
                        }
                    }
                    FlowRow(
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                    ) {
                        ratingList.forEach { rating ->
                            CustomerRatingFilterChip(
                                star = rating,
                                selected = rating in selectedRatings,
                                onSelectedChange = { isSelected ->
                                    selectedRatings = if (isSelected) {
                                        selectedRatings + rating
                                    } else {
                                        selectedRatings - rating
                                    }
                                })
                        }
                    }
                }
                HorizontalDivider(
                    color = Color(0xFFE4E4E4),
                    thickness = 2.dp,
                    modifier = Modifier.padding(horizontal = 25.dp)
                )
                Spacer(modifier = Modifier.height(80.dp))
            }
        }
    }
}

@Composable
private fun CustomerNeighborFilterChip(
    label: String, selected: Boolean, onSelectedChange: (String) -> Unit
) {
    FilterChip(
        onClick = {
            if (selected) {
                onSelectedChange("")
            } else {
                onSelectedChange(label)
            }
        },
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
            selectedContainerColor = TrekkStayCyan,
            labelColor = TrekkStayCyan,
            selectedLabelColor = Color.White
        ),
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(2.dp, TrekkStayCyan),
        modifier = Modifier.height(30.dp)
    )
}

@Composable
private fun CustomerRatingFilterChip(
    star: Int, selected: Boolean, onSelectedChange: (Boolean) -> Unit
) {
    FilterChip(
        onClick = {
            onSelectedChange(!selected)
        },
        label = {
            Text(
                "$star",
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp
            )
        },
        leadingIcon = {
            if (selected) {
                Icon(
                    Icons.Filled.Star,
                    null,
                    tint = Color(0xFFFFC107)
                )
            } else {
                Icon(
                    Icons.Outlined.Star,
                    null,
                    tint = Color.Gray
                )
            }
        },
        selected = selected,
        colors = FilterChipDefaults.filterChipColors(
            containerColor = Color.White,
            selectedContainerColor = TrekkStayCyan,
            labelColor = TrekkStayCyan,
            selectedLabelColor = Color.White
        ),
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(2.dp, TrekkStayCyan),
        modifier = Modifier.height(30.dp)
    )
}

@Preview(showBackground = true, showSystemUi = true, device = "spec:width=411dp,height=891dp")
@Composable
fun FilterHotelPreview() {
    CustomerFilterHotel()
}