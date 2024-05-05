package com.trekkstay.hotel.feature.hotel.presentation.activities

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
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
import com.trekkstay.hotel.feature.hotel.presentation.fragments.RoomReservationCard
import com.trekkstay.hotel.feature.reservation.domain.entities.Reservation
import com.trekkstay.hotel.feature.reservation.presentation.states.ReservationState
import com.trekkstay.hotel.feature.reservation.presentation.states.ReservationViewModel
import com.trekkstay.hotel.feature.reservation.presentation.states.ViewDetailReservationAction
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily
import com.trekkstay.hotel.ui.theme.TrekkStayCyan

@Composable
fun BookingDetailScreen(
    idReservation: String,
    navController: NavController,
    reservationViewModel: ReservationViewModel
) {
    var hotelName by remember { mutableStateOf("") }
    var bookingID by remember { mutableStateOf("") }
    var customerName by remember { mutableStateOf("") }
    var customerContact by remember { mutableStateOf("") }
    var roomNum by remember { mutableIntStateOf(0) }
    var totalPrice by remember { mutableIntStateOf(1000) }
    var imgUrl by remember { mutableStateOf("") }
    var checkIn by remember { mutableStateOf("") }
    var checkOut by remember { mutableStateOf("") }
    var payMethod by remember { mutableStateOf("") }
    var payStatus by remember { mutableStateOf("") }

    var reservationDetail by remember {
        mutableStateOf<Reservation?>(null)
    }
    val reservationState by reservationViewModel.state.observeAsState()
    when (reservationState) {
        is ReservationState.SuccessViewDetailReservation -> {
            reservationDetail =
                (reservationState as ReservationState.SuccessViewDetailReservation).reservation
            hotelName = reservationDetail!!.room.hotelName
            bookingID = reservationDetail!!.id
            customerName = reservationDetail!!.guestInfo.name
            customerContact = reservationDetail!!.guestInfo.contact
            totalPrice = reservationDetail!!.room.bookingPrice
            imgUrl = reservationDetail!!.qrCodeUrl
            checkIn = reservationDetail!!.checkIn
            checkOut = reservationDetail!!.checkOut
            roomNum = reservationDetail!!.quantity.toInt()
            payMethod = when (reservationDetail!!.payment!!.method) {
                "PAY_AT_HOTEL" -> "Pay at Hotel"
                "MOMO" -> "Pay online with Momo"
                else -> ""
            }
            payStatus = when (reservationDetail!!.payment!!.status) {
                "PENDING" -> "Pending"
                "SUCCESS" -> "Successful"
                "FAILED" -> "Failed"
                else -> ""
            }
        }

        is ReservationState.InvalidViewDetailReservation -> {
            println((reservationState as ReservationState.InvalidListReservation).message)
        }

        is ReservationState.ViewDetailReservationCalling -> {}

        else -> {}
    }

    LaunchedEffect(Unit) {
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
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 30.dp)
        ) {
            Text(
                text = hotelName,
                fontFamily = PoppinsFontFamily,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
            Image(
                painter = rememberAsyncImagePainter(imgUrl),
                modifier = Modifier
                    .size(195.dp),
                contentDescription = "QR"
            )
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
            Column(
                verticalArrangement = Arrangement.spacedBy(15.dp),
                modifier = Modifier
                    .border(1.dp, Color(0xFFC4C4C4), shape = RoundedCornerShape(20.dp))
                    .padding(vertical = 20.dp, horizontal = 30.dp)
            ) {
                BookingInfoRow("Name", customerName)
                BookingInfoRow("Contact", customerContact)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    BookDateCol(label = "Check In", date = checkIn)
                    Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null)
                    BookDateCol(label = "Check Out", date = checkOut)
                }
                BookingInfoRow("Number of rooms", "$roomNum", 150)
                BookingInfoRow("Total Price", "$ $totalPrice", 150)
                BookingInfoRow("Payment Method", payMethod, 150)
                BookingInfoRow("Payment Status", payStatus, 150)
            }
            reservationDetail?.let { RoomReservationCard(it) }
        }
    }
}

@Composable
private fun BookingInfoRow(
    label: String,
    value: String,
    width: Int = 120
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
            modifier = Modifier.width(width.dp)
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
                .background(TrekkStayCyan, shape = RoundedCornerShape(5.dp))
                .padding(10.dp)
        )
    }
}

