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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hotel.R
import com.trekkstay.hotel.core.storage.LocalStore
import com.trekkstay.hotel.feature.hotel.presentation.fragments.DateRangeSelector
import com.trekkstay.hotel.feature.hotel.presentation.fragments.InfoTextField
import com.trekkstay.hotel.feature.reservation.domain.entities.GuestInfo
import com.trekkstay.hotel.feature.reservation.presentation.states.CreateReservationAction
import com.trekkstay.hotel.feature.reservation.presentation.states.ReservationState
import com.trekkstay.hotel.feature.reservation.presentation.states.ReservationViewModel
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily
import com.trekkstay.hotel.ui.theme.TrekkStayCyan
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BookingFormScreen(roomId:String,
                      reservationViewModel: ReservationViewModel,
                      navController: NavController
) {
    var expandedDesc by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val userName = LocalStore.getKey(context,"name","")
    val userEmail = LocalStore.getKey(context,"email","")
    val searchStartDate = LocalStore.getKey(context,"search_start_date",System.currentTimeMillis().toString()).toLong()
    val searchEndDate = LocalStore.getKey(context,"search_end_date",(System.currentTimeMillis() + 86400000).toString()).toLong()
    val searchRoomNum = LocalStore.getKey(context,"search_room_num","1").toInt()
    val searchAdultNum = LocalStore.getKey(context,"search_adult_num","2").toInt()
    val searchChildNum = LocalStore.getKey(context,"search_child_num","1").toInt()

    var name by remember { mutableStateOf(TextFieldValue(userName)) }
    var email by remember { mutableStateOf(TextFieldValue(userEmail)) }
    var phone by remember { mutableStateOf(TextFieldValue()) }
    var roomNum by remember { mutableIntStateOf(searchRoomNum) }
    var adultNum by remember { mutableIntStateOf(searchAdultNum) }
    var childNum by remember { mutableIntStateOf(searchChildNum) }


    var selectedDateRange by remember { mutableStateOf<Pair<Long, Long>?>(Pair(searchStartDate, searchEndDate)) }
    fun formatDateRange(startDateMillis: Long, endDateMillis: Long): Pair<String, String> {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val startDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(startDateMillis), ZoneId.systemDefault())
        val endDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(endDateMillis), ZoneId.systemDefault())
        val formattedStartDate = startDate.format(formatter)
        val formattedEndDate = endDate.format(formatter)
        return Pair(formattedStartDate, formattedEndDate)
    }

    val reservationState by reservationViewModel.state.observeAsState()

    when (reservationState){
        is ReservationState.SuccessCreateReservation -> {
            println((reservationState as ReservationState.SuccessCreateReservation).reservation)

        }
        is ReservationState.InvalidCreateReservation -> {
            println((reservationState as ReservationState.InvalidCreateReservation).message)
        }
        is ReservationState.CreateReservationCalling -> {
        }

        else ->{

        }
    }

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
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = null)
                }
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
                selectedDateRange?.first?.let {
                    DateRangeSelector(type = "book", startDate = it, endDate = selectedDateRange!!.second)
                    { dateRange ->
                        selectedDateRange = dateRange
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 20.dp, horizontal = 50.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {val (formattedCheckInDate, formattedCheckOutDate) = formatDateRange(selectedDateRange!!.first,
                        selectedDateRange!!.second
                    )

                        val reservationAction = CreateReservationAction(
                            roomId= roomId,
                            checkIn = formattedCheckInDate,
                            checkOut = formattedCheckOutDate,
                            quantity = roomNum,
                            guestInfo = GuestInfo(name= name.text, contact = email.text,adult = adultNum,children=childNum)
                        )
                        reservationViewModel.processAction(reservationAction)
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