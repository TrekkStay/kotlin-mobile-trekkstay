package com.trekkstay.hotel.feature.hotel.presentation.activities

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hotel.R
import com.trekkstay.hotel.feature.hotel.presentation.fragments.DateRangeSelector
import com.trekkstay.hotel.feature.hotel.presentation.fragments.InfoTextField
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily
import com.trekkstay.hotel.ui.theme.TrekkStayCyan

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BookingFormScreen() {
    var expandedDesc by remember { mutableStateOf(false) }

    var name by remember { mutableStateOf(TextFieldValue()) }
    var email by remember { mutableStateOf(TextFieldValue()) }
    var phone by remember { mutableStateOf(TextFieldValue()) }
    var roomNum by remember { mutableStateOf(1) }
    var adultNum by remember { mutableStateOf(1) }
    var childNum by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier.padding(bottom = 70.dp)
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(bottom = 80.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp, vertical = 20.dp)
            ) {
                Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = null)
                Text(
                    text = "Booking Form",
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp, vertical = 5.dp)
            ) {
                Text(
                    text = "Fill in the form with your information to process payment.",
                    textAlign = TextAlign.Center,
                    fontFamily = PoppinsFontFamily,
                    color = Color.Gray,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    maxLines = if (expandedDesc) Int.MAX_VALUE else 3,
                    overflow = TextOverflow.Ellipsis
                )
                InfoTextField(
                    label = "Full Name",
                    text = name,
                    onValueChange = { name = it },
                    icon = Icons.Default.AccountCircle,
                    view = "customer"
                )
                InfoTextField(
                    label = "Email Address",
                    text = email,
                    onValueChange = { email = it },
                    icon = Icons.Default.Email,
                    view = "customer"
                )
                InfoTextField(
                    label = "Phone Number",
                    text = phone,
                    onValueChange = { phone = it },
                    icon = Icons.Default.Phone,
                    view = "customer",
                    type = "number"
                )
                GuestInfoRow(title = "Room Number", count = roomNum, icon = ImageVector.vectorResource(R.drawable.bed_ico),onIncrement = {
                    roomNum++
                }, onDecrement = {
                    if (roomNum > 1) roomNum--
                })
                GuestInfoRow(title = "Adults", count = adultNum, icon = ImageVector.vectorResource(R.drawable.people_ico), onIncrement = {
                    adultNum++
                }, onDecrement = {
                    if (adultNum > 1) adultNum--
                })
                GuestInfoRow(title = "Children", count = childNum, icon = ImageVector.vectorResource(R.drawable.child_ico), onIncrement = {
                    childNum++
                }, onDecrement = {
                    if (childNum > 0) childNum--
                })
                DateRangeSelector(type = "book",
                    ) {
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 40.dp, horizontal = 50.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        // Handle button click
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = TrekkStayCyan),
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(50.dp),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Continue",
                        color = Color.White,
                        fontWeight = FontWeight.Medium,
                        fontSize = 15.sp,
                        fontFamily = PoppinsFontFamily
                    )
                }
            }
        }
    }
}

@Composable
private fun GuestInfoRow(
    title: String,
    count: Int,
    icon: ImageVector,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(13.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .border(1.dp, TrekkStayCyan, RoundedCornerShape(12.dp))
            .padding(horizontal = 12.dp)
    )
    {
        Icon(
            icon,
            contentDescription = null,
            tint = TrekkStayCyan
        )
        Row(modifier = Modifier.weight(1f)) {
            Text(
                title,
                fontFamily = PoppinsFontFamily,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black.copy(0.6f)
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = {
                onDecrement()
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_remove_24),
                    contentDescription = null
                )
            }
            Text(
                "$count",
                fontFamily = PoppinsFontFamily,
                color = TrekkStayCyan,
                modifier = Modifier.padding(8.dp)
            )
            IconButton(onClick = {
                onIncrement()
            }) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, showSystemUi = true, device = "spec:width=411dp,height=891dp")
@Composable
fun BookingFormPreview() {
    BookingFormScreen()
}