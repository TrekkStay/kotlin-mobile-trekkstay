package com.trekkstay.hotel.feature.hotel.presentation.activities

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.hotel.R
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily
import com.trekkstay.hotel.ui.theme.TrekkStayBlue
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.toSize

@Composable
fun CreateHotelScreen() {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    val dropdownItems = listOf("Item 1", "Item 2", "Item 3")


    Column {
        Spacer(modifier = Modifier.height(25.dp))
        Text(
            text = "Create Your Hotel" ,
            fontSize = 20.sp,
            fontFamily = PoppinsFontFamily,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 25.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(Color(0xFFE4E4E4))
        )
        Column(
            modifier = Modifier.padding(horizontal = 25.dp,vertical = 20.dp)
        ) {

            InputOutlineTextView(
                title = "Hotel Name",
                value = name,
                onValueChange = { newName ->
                    name = newName // Update the value of 'name'
                },
                icon = Icons.Filled.Create
            )
            Spacer(modifier = Modifier.height(20.dp))
            InputOutlineTextView(
                title = "Email Address",
                value = email,
                onValueChange = { newEmail ->
                    email = newEmail // Update the value of 'name'
                },
                icon = Icons.Filled.Email
            )
            Spacer(modifier = Modifier.height(20.dp))
            InputOutlineTextView(
                title = "Phone Number",
                value = phone,
                onValueChange = { newPhone ->
                    phone = newPhone // Update the value of 'name'
                },
                icon = Icons.Filled.Create
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                DropDownMenu(160, "Check-in")
                DropDownMenu(160, " Check-out")
            }
            Spacer(modifier = Modifier.height(20.dp))

            CreateHotelButton(
                title = "Hotel Location",
                icon = Icons.Filled.LocationOn,
                onClick = {

                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                DropDownMenu(125, "Province")
                DropDownMenu(120, "District")
                DropDownMenu(100, "Ward")
            }
            Spacer(modifier = Modifier.height(20.dp))
            InputOutlineTextView(
                title = "Address Detail",
                value = address,
                onValueChange = { newAddress ->
                    address = newAddress // Update the value of 'name'
                },
                icon = Icons.Filled.Place
            )
            Spacer(modifier = Modifier.height(20.dp))
            CreateHotelButton(
                title = "Image/Video",
                icon = Icons.Filled.PlayArrow,
                onClick = {

                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            InputOutlineTextView(
                title = "Description",
                value = description,
                onValueChange = { newDescription ->
                    description = newDescription // Update the value of 'name'
                },
                icon = Icons.Filled.Menu
            )
            Spacer(modifier = Modifier.height(20.dp))

        }
    }
}

@Composable
fun InputOutlineTextView(
    title: String,
    value: String,
    onValueChange: (String) -> Unit, // Function to update the value
    icon: ImageVector
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) }, // Call the onValueChange function to update the value
        label = { Text(title) },
        modifier = Modifier.fillMaxWidth(),
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = TrekkStayBlue
            )
        },
        shape = RoundedCornerShape(20.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = TrekkStayBlue,
            unfocusedBorderColor = TrekkStayBlue,
            cursorColor = TrekkStayBlue,
        )
    )
}

@Composable
fun SmallInputOutlineTextView(
    title: String,
    value: String,
    onValueChange: (String) -> Unit, // Function to update the value
    icon: ImageVector,

) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) }, // Call the onValueChange function to update the value
        label = { Text(title) },
        modifier = Modifier.fillMaxWidth(),
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = TrekkStayBlue
            )
        },
        shape = RoundedCornerShape(20.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = TrekkStayBlue,
            unfocusedBorderColor = TrekkStayBlue,
            cursorColor = TrekkStayBlue,
        )
    )
}

@Composable
fun CreateHotelButton(
    title: String,
    icon: ImageVector,
    onClick: () -> Unit // Function to handle click
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(40.dp),
        border = BorderStroke(1.dp, TrekkStayBlue),
        onClick = onClick // Pass the onClick function to handle the click event
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = TrekkStayBlue
            )
            Text(
                text = title,
                color = Color.Black,
                modifier = Modifier.padding(start = 12.dp)
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownMenu(widthSize: Int, title: String) {
    val context = LocalContext.current
    val timeList = arrayOf("12:00", "12:30", "13:00", "13:30", "14:00")
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(title) }

    Box(
        modifier = Modifier
            .width(widthSize.dp)
            .border(1.dp, TrekkStayBlue, shape = RoundedCornerShape(20.dp))
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            OutlinedTextField(
                value = selectedText,
                onValueChange = {  }, // Call the onValueChange function to update the value
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(),
//                leadingIcon = {
//                    Icon(
//                        imageVector = Icons.Filled.DateRange,
//                        contentDescription = null,
//                        tint = TrekkStayBlue
//                    )
//                },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                shape = RoundedCornerShape(20.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = TrekkStayBlue,
                    unfocusedBorderColor = TrekkStayBlue,
                    cursorColor = TrekkStayBlue,
                )
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                timeList.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            selectedText = item
                            expanded = false
                            Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true, device = "spec:width=411dp,height=891dp")
@Composable
fun CreateHotelScreenPreview() {
    CreateHotelScreen()

}