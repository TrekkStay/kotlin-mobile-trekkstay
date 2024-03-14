package com.trekkstay.hotel.feature.hotel.presentation.activities

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.TextFieldValue

@Composable
fun CreateHotelScreen() {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }


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
            CreateHotelButton(
                title = "Hotel Location",
                icon = Icons.Filled.LocationOn,
                onClick = {

                }
            )
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




@Preview(showBackground = true, showSystemUi = true, device = "spec:width=411dp,height=891dp")
@Composable
fun CreateHotelScreenPreview() {
    CreateHotelScreen()
}