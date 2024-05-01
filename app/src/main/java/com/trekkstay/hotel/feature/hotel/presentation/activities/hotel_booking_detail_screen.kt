package com.trekkstay.hotel.feature.hotel.presentation.activities

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.trekkstay.hotel.feature.reservation.domain.entities.Reservation
import com.trekkstay.hotel.feature.reservation.presentation.states.ReservationState
import com.trekkstay.hotel.feature.reservation.presentation.states.ReservationViewModel
import com.trekkstay.hotel.feature.reservation.presentation.states.ViewDetailReservationAction
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily
import com.trekkstay.hotel.ui.theme.TrekkStayBlue

@Composable
fun HotelBookingDetailScreen(
    idReservation: String,
    reservationViewModel: ReservationViewModel,
    navController: NavController
) {
    val statusList = arrayOf("Failed", "Pending", "Success")
    var selectedStatus by remember { mutableStateOf("Pending") }


    var hotelName by remember {
        mutableStateOf("")
    }
    var bookingID by remember {
        mutableStateOf("")
    }
    var customerName by remember {
        mutableStateOf("")
    }
    var customerEmail by remember {
        mutableStateOf("")
    }
    var customerPhone by remember {
        mutableStateOf("")
    }
    var roomNum by remember {
        mutableStateOf(0)
    }
    var totalPrice by remember {
        mutableStateOf(1000)
    }
    var imgUrl by remember {
        mutableStateOf("")
    }

    var checkIn by remember {
        mutableStateOf("")
    }

    var checkOut by remember {
        mutableStateOf("")
    }


    var reservationDetail by remember {
        mutableStateOf<Reservation?>(null)
    }
    val reservationState by reservationViewModel.state.observeAsState()
    when (reservationState) {
        is ReservationState.SuccessViewDetailReservation -> {
            println("okkkkkkkkkkkkk")
            reservationDetail =
                (reservationState as ReservationState.SuccessViewDetailReservation).reservation
            println(">>>>>>>>>>>>>>>>> detail")
            println(reservationDetail)
            hotelName = reservationDetail!!.room.hotelName
            bookingID = reservationDetail!!.id
            customerName = reservationDetail!!.guestInfo.name
            customerEmail = reservationDetail!!.guestInfo.contact
            customerPhone = reservationDetail!!.guestInfo.contact
            totalPrice = reservationDetail!!.room.bookingPrice
            imgUrl = reservationDetail!!.qrCodeUrl
            checkIn = reservationDetail!!.checkIn
            checkOut = reservationDetail!!.checkOut
            roomNum = reservationDetail!!.quantity.toInt()
        }

        is ReservationState.InvalidViewDetailReservation -> {
            println((reservationState as ReservationState.InvalidListReservation).message)
        }

        is ReservationState.ViewDetailReservationCalling -> {
            println("checking")
        }

        else -> {}
    }

    LaunchedEffect(Unit) {
        println(">>>>>>>>>>>> detail boooking")
        val action = ViewDetailReservationAction(idReservation)
        reservationViewModel.processAction(action)

    }

    Column(
        modifier = Modifier
            .padding(top = 25.dp, bottom = 80.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Icon(
                Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = null,
                Modifier.clickable {
                    navController.popBackStack()
                })
            Text(
                text = "Booking Detail",
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(20.dp)
                .border(1.dp, Color(0xFFC4C4C4), shape = RoundedCornerShape(20.dp))
                .padding(vertical = 20.dp, horizontal = 30.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(vertical = 10.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(imgUrl),
                    modifier = Modifier
                        .size(200.dp),
                    contentDescription = "QR"
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color.Gray)) {
                            append("Booking ID: ")
                        }
                        append(bookingID)
                    },
                    fontFamily = PoppinsFontFamily,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            BookingInfoRow("Name", customerName)
            BookingInfoRow("Email", customerEmail)
            BookingInfoRow("Phone", customerPhone)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                BookDateCol(label = "Check In", date = checkIn)
                Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null)
                BookDateCol(label = "Check Out", date = checkOut)
            }
            BookingInfoRow("Number of rooms", "$roomNum")
            BookingInfoRow("Total", "$ $totalPrice")
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Payment Status",
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                    color = Color.Gray,
                    modifier = Modifier.width(140.dp)
                )
                DropDownMenu(
                    title = selectedStatus,
                    itemList = statusList,
                    onItemSelected = { selectedStatus = it })
            }
            //Thêm hotel room card rồi navigate
            //HotelRoomCard(room = , navController = )
        }
    }
}

@Composable
private fun BookingInfoRow(
    label: String,
    value: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = label,
            fontFamily = PoppinsFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier.width(140.dp)
        )
        Text(
            text = value,
            fontFamily = PoppinsFontFamily,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .weight(1f)
        )
    }
}

@Composable
private fun BookDateCol(
    label: String,
    date: String,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            label,
            fontFamily = PoppinsFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 13.sp,
            color = Color.Gray,
        )
        Text(
            date,
            fontFamily = PoppinsFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 13.sp,
            color = Color.White,
            modifier = Modifier
                .background(TrekkStayBlue, shape = RoundedCornerShape(5.dp))
                .padding(10.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DropDownMenu(
    title: String,
    itemList: Array<String>,
    onItemSelected: (String) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(title) }

    ExposedDropdownMenuBox(
        modifier = Modifier.size(170.dp, 35.dp),
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .size(170.dp, 35.dp)
                .background(Color.Transparent, shape = RoundedCornerShape(8.dp))
                .border(1.dp, TrekkStayBlue, shape = RoundedCornerShape(8.dp))
                .padding(horizontal = 15.dp)
                .menuAnchor()
        ) {
            Text(
                selectedText,
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                color = Color.Black.copy(0.6f)
            )
            Icon(
                Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                tint = TrekkStayBlue,
                modifier = Modifier.size(20.dp)
            )
        }
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(170.dp)
                .background(Color.White)
        ) {
            itemList.forEach { item ->
                DropdownMenuItem(
                    text = { Text(text = item, fontFamily = PoppinsFontFamily) },
                    colors = MenuItemColors(
                        textColor = TrekkStayBlue,
                        leadingIconColor = TrekkStayBlue,
                        trailingIconColor = TrekkStayBlue,
                        disabledTextColor = TrekkStayBlue,
                        disabledLeadingIconColor = TrekkStayBlue,
                        disabledTrailingIconColor = TrekkStayBlue
                    ),
                    onClick = {
                        selectedText = item
                        expanded = false
                        onItemSelected(item)
                    }
                )
            }
        }
    }
}

