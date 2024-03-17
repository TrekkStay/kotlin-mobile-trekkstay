package com.trekkstay.hotel.feature.hotel.presentation.activities

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.example.hotel.R
import com.trekkstay.hotel.feature.hotel.presentation.fragments.FacilityChip
import com.trekkstay.hotel.feature.hotel.presentation.fragments.HotelRoomOptSelector
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily
import com.trekkstay.hotel.ui.theme.TrekkStayBlue

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CreateRoomScreen() {
    var room_type by remember { mutableStateOf(TextFieldValue()) }
    var description by remember { mutableStateOf(TextFieldValue()) }
    var quantity by remember { mutableStateOf(TextFieldValue()) }
    var discount_rate by remember { mutableStateOf(TextFieldValue()) }
    var price by remember { mutableStateOf(TextFieldValue()) }
    var view by remember { mutableStateOf(TextFieldValue()) }
    var roomSize by remember { mutableStateOf(TextFieldValue()) }
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxHeight()
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(25.dp))
            Text(
                text = "Create hotel room",
                fontSize = 20.sp,
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 25.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            HorizontalDivider(color = Color(0xFFE4E4E4), thickness = 3.dp)
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.padding(horizontal = 25.dp, vertical = 20.dp)
            ) {
                RoomInfoTextField(
                    label = "Room type",
                    text = room_type,
                    icon = ImageVector.vectorResource(R.drawable.bed_ico)
                )
                RoomInfoTextField(
                    label = "Description",
                    text = description,
                    icon = Icons.Default.Info
                )
                RoomInfoTextField(
                    label = "Quantity",
                    text = quantity,
                    icon = ImageVector.vectorResource(R.drawable.box_ico)
                )
                RoomInfoTextField(
                    label = "Discount Rate",
                    text = discount_rate,
                    icon = ImageVector.vectorResource(R.drawable.discount_ico)
                )
                RoomInfoTextField(
                    label = "Original Price",
                    text = price,
                    icon = ImageVector.vectorResource(R.drawable.money_circle_ico)
                )
                MediaSelectorRow(
                    label = "Video",
                    icon = ImageVector.vectorResource(R.drawable.camera_ico),
                    clickHandler = {
                        //TODO
                    }
                )
                MediaSelectorRow(
                    label = "Image",
                    icon = ImageVector.vectorResource(R.drawable.photo_ico),
                    clickHandler = {
                        //TODO
                    }
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(5.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFE4E4E4).copy(0.3f))
                        .border(1.dp, TrekkStayBlue, shape = RoundedCornerShape(16.dp))
                        .padding(20.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = null,
                            tint = TrekkStayBlue,
                            modifier = Modifier.padding(horizontal = 10.dp)
                        )
                        Text(
                            text = "Facility",
                            fontSize = 13.sp,
                            fontFamily = PoppinsFontFamily,
                            fontWeight = FontWeight.Medium,
                            color = Color.Black.copy(0.6f)
                        )
                    }
                    FlowRow(
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        horizontalArrangement = Arrangement.spacedBy(18.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp)
                    ) {
                        FacilityChip("Air Condition")
                        FacilityChip("Bath Tub")
                        FacilityChip("Shower")
                        FacilityChip("Balcony")
                        FacilityChip("Hair Dryer")
                        FacilityChip("Kitchen")
                        FacilityChip("Television")
                        FacilityChip("Slippers")
                        FacilityChip("Smoking")
                    }
                    RoomInfoTextField(label = "View", text = view, icon = ImageVector.vectorResource(R.drawable.eye_ico))
                    RoomInfoTextField(label = "Room Size", text = roomSize, icon = ImageVector.vectorResource(R.drawable.size_ico))
                    Spacer(modifier = Modifier.height(5.dp))
                    HotelRoomOptSelector()
                }
            }
        }
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                containerColor = TrekkStayBlue,
                contentColor = Color.White
            ),
            contentPadding = PaddingValues(horizontal = 100.dp, vertical = 15.dp),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(25.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = "Save Hotel Room",
                fontSize = 18.sp,
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
fun RoomInfoTextField(
    label: String,
    text: TextFieldValue,
    icon: ImageVector
) {
    var text by remember { mutableStateOf(text) }
    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = {
            Text(
                label,
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.Medium,
                color = Color.Black.copy(0.6f)
            )
        },
        modifier = Modifier.fillMaxWidth(),
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = TrekkStayBlue
            )
        },
        textStyle = TextStyle(
            fontFamily = PoppinsFontFamily,
            fontSize = 14.sp
        ),
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color("#007EF2".toColorInt()),
            unfocusedBorderColor = Color("#007EF2".toColorInt()),
            cursorColor = Color("#007EF2".toColorInt()),
        ),
//        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
    )
}

@Composable
fun MediaSelectorRow(
    label: String,
    icon: ImageVector,
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
                icon,
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
                Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                tint = TrekkStayBlue,
                modifier = Modifier.size(30.dp)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, device = "spec:width=411dp,height=891dp")
@Composable
fun CreateRoomPreview() {
    CreateRoomScreen()
}