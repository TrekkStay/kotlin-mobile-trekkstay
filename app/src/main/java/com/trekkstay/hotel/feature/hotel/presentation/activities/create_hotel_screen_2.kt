package com.trekkstay.hotel.feature.hotel.presentation.activities

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily
import com.trekkstay.hotel.ui.theme.TrekkStayBlue
import com.trekkstay.hotel.ui.theme.TrekkStayCyan

@OptIn(ExperimentalMaterial3Api::class)
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
                Row {
                    OnOffButton1("Air Condition")
                    OnOffButton1("Bath Tub")
                    OnOffButton1("Shower")
                }
                Row {
                    OnOffButton1("Balcony")
                    OnOffButton1("Hair Drier")
                    OnOffButton1("Kitchen")
                }
                Row {
                    OnOffButton1("Televison")
                    OnOffButton1("Slippers")
                    OnOffButton1("Smoking")
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


@Composable
fun OnOffButton1(title: String) {
    var isFilled by remember { mutableStateOf(false) }

    if (isFilled) {
        Button(
            onClick = { isFilled = !isFilled },
            modifier = Modifier.padding(8.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = TrekkStayBlue,
                contentColor = Color.White),
        ) {
            Text(text = title)
        }
    } else {
        Button(
            onClick = { isFilled = !isFilled },
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color.White,
                contentColor = TrekkStayBlue
            ),
            modifier = Modifier
                .padding(8.dp)
                .border(1.dp, TrekkStayBlue, shape = RoundedCornerShape(36.dp))

        ) {
            Text(text = title)
        }
    }
}
@Preview(showBackground = true, showSystemUi = true, device = "spec:width=411dp,height=891dp")
@Composable
fun CreateHotelScreen2Preview() {
    CreateHotelScreen2()
}

