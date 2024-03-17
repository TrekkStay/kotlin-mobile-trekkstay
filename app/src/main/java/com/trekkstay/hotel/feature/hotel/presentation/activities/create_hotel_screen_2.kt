package com.trekkstay.hotel.feature.hotel.presentation.activities

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trekkstay.hotel.feature.hotel.presentation.fragments.FacilityChip
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily
import com.trekkstay.hotel.ui.theme.TrekkStayBlue

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CreateHotelScreen2() {

    var view by remember { mutableStateOf("") }

    Column {
        Spacer(modifier = Modifier.height(25.dp))
        Text(
            text = "Create Your Hotel",
            fontSize = 20.sp,
            fontFamily = PoppinsFontFamily,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 25.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Box (
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                
        ){
            Column(
                modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, TrekkStayBlue, shape = RoundedCornerShape(16.dp))
            ) {
                Row(
                    modifier = Modifier.padding(10.dp)
                ) {
                    androidx.compose.material3.Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = null,
                        tint = TrekkStayBlue,
                        modifier = Modifier.padding(horizontal = 10.dp)
                    )
                    Text(
                        text = "Facility",
                        fontSize = 18.sp,
                        fontFamily = PoppinsFontFamily,
                        fontWeight = FontWeight.SemiBold,
                    )
                }
                FlowRow(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(18.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 10.dp)
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
                Row (Modifier.padding(horizontal = 10.dp, vertical = 5.dp))
                {
                    InputOutlineTextView(
                        title = "View",
                        value = view,
                        onValueChange = { newDescription ->
                            view = newDescription // Update the value of 'name'
                        },
                        icon = Icons.Filled.Home
                    )
                }
                Row (Modifier.padding(horizontal = 10.dp, vertical = 5.dp)){
                    InputOutlineTextView(
                        title = "Number of Bedrooms",
                        value = view,
                        onValueChange = { newDescription ->
                            view = newDescription // Update the value of 'name'
                        },
                        icon = Icons.Filled.Home
                    )
                }

                Row (Modifier.padding(horizontal = 10.dp, vertical = 5.dp)){
                    InputOutlineTextView(
                        title = "Room Size",
                        value = view,
                        onValueChange = { newDescription ->
                            view = newDescription // Update the value of 'name'
                        },
                        icon = Icons.Filled.Home
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    DropDownMenu(160, "Check-in")
                    DropDownMenu(160, " Check-out")
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, device = "spec:width=411dp,height=891dp")
@Composable
fun CreateHotelScreen2Preview() {
    CreateHotelScreen2()
}