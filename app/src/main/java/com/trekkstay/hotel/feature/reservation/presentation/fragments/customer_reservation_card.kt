package com.trekkstay.hotel.feature.reservation.presentation.fragments

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.trekkstay.hotel.feature.reservation.presentation.states.CancelReservationAction
import com.trekkstay.hotel.feature.reservation.presentation.states.ReservationState
import com.trekkstay.hotel.feature.reservation.presentation.states.ReservationViewModel
import com.trekkstay.hotel.feature.shared.TextDialog
import com.trekkstay.hotel.feature.shared.Utils.formatPrice
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily
import com.trekkstay.hotel.ui.theme.TrekkStayCyan
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomerReservationCard(
    reservationId: String,
    hotelImg: String,
    hotelId: String,
    hotelName: String,
    destination: String,
    type: String,
    price: Double = 0.0,
    checkIn: String = "",
    checkOut: String = "",
    isRated: Boolean,
    navController: NavController,
    reservationViewModel: ReservationViewModel
) {
    val formattedPrice = formatPrice(price)
    var isBotSheetVisible by remember { mutableStateOf(false) }
    val botSheetState = rememberModalBottomSheetState()
    val currentDate = LocalDate.ofEpochDay(System.currentTimeMillis() / (1000 * 60 * 60 * 24))
    val checkInDate = LocalDate.parse(checkIn)
    val remainDays = ChronoUnit.DAYS.between(currentDate, checkInDate)
    var showDialog by remember { mutableStateOf(false) }
    var dialogTitle by remember { mutableStateOf("") }
    var dialogMessage by remember { mutableStateOf("") }
    if (showDialog) {
        TextDialog(
            title = dialogTitle,
            msg = dialogMessage,
            onDismiss = { showDialog = false },
        )
    }

    val reservationState by reservationViewModel.state.observeAsState()
    when (reservationState) {
        is ReservationState.SuccessCancelReservation -> {
            println("success cancel reservation")
            TextDialog(
                title = "Cancel Booking",
                msg = "You cancelled booking successfully!",
                type = "success",
                onDismiss = {
                    showDialog = false
                    navController.navigate("customer_reservations") {
                        launchSingleTop = true
                    }
                }
            )
        }

        is ReservationState.InvalidCancelReservation -> {
            println("invalid cancel reservation")
            val msg = (reservationState as ReservationState.InvalidCancelReservation).message
            println(msg)
            TextDialog(
                title = "Cancel Failed!",
                msg = (reservationState as ReservationState.InvalidCancelReservation).message,
                onDismiss = {
                    showDialog = false
                    navController.navigate("customer_reservations") {
                        launchSingleTop = true
                    }
                }
            )
        }

        is ReservationState.CancelReservationCalling -> {
            println("cancel calling")
        }

        else -> {}
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .size(150.dp)
            .shadow(5.dp, shape = RoundedCornerShape(10.dp))
            .background(Color.White, shape = RoundedCornerShape(10.dp))
            .padding(10.dp)

    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .size(80.dp)
        ) {
            AsyncImage(
                model = hotelImg,
                contentDescription = "Reservation's hotel image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(150.dp, 90.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .border(1.dp, Color.LightGray, shape = RoundedCornerShape(12.dp))
            )
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(vertical = 5.dp)
            ) {
                Text(
                    hotelName,
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 15.sp
                )
                Text(
                    destination,
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 11.sp,
                    color = Color(0xFF303030).copy(0.65f)
                )
                when (type) {
                    "Upcoming" -> {
                        Text(
                            "${formatDate(checkIn)} - ${formatDate(checkOut)}",
                            fontFamily = PoppinsFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            color = TrekkStayCyan
                        )
                    }

                    "Completed" -> {
                        Text(
                            text = "$$formattedPrice",
                            fontSize = 15.sp,
                            fontFamily = PoppinsFontFamily,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    "Cancelled" -> {
                        Text(
                            "Cancelled & refunded",
                            fontFamily = PoppinsFontFamily,
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                            color = Color(0xFFC82222).copy(0.6f)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            when (type) {
                "Upcoming" -> {}
                "Completed" -> {
                    Icon(
                        Icons.Default.CheckCircle,
                        contentDescription = "type icon",
                        tint = Color(0xFF0FA958)
                    )
                }

                "Cancelled" -> {
                    Icon(
                        Icons.Default.Warning,
                        contentDescription = "type icon",
                        tint = Color(0xFFE83F28).copy(0.9f)
                    )
                }
            }
            Spacer(modifier = Modifier.width(5.dp))
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .size(120.dp)
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            when (type) {
                "Upcoming" -> {
                    val btnColor = if (remainDays < 3) Color(0xFFCDE8E5) else TrekkStayCyan
                    OutlinedButton(
                        border = BorderStroke(2.dp, btnColor),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = btnColor
                        ),
                        onClick = {
                            if (remainDays < 3) {
                                dialogTitle = "Booking Cancellation Denied"
                                dialogMessage =
                                    "Sorry, you cannot cancel your reservation now. It's less than 2 days before your check-in date."
                                showDialog = true
                            } else {
                                isBotSheetVisible = true
                            }
                        }
                    ) {
                        Text(
                            "Cancel",
                            fontFamily = PoppinsFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 13.sp
                        )
                    }
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = TrekkStayCyan,
                            contentColor = Color.White
                        ),
                        onClick = {
                            navController.navigate("reservation_detail/${reservationId}")
                        }
                    ) {
                        Text(
                            "View Booking",
                            fontFamily = PoppinsFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 13.sp
                        )
                    }
                    if (isBotSheetVisible) {
                        ModalBottomSheet(
                            onDismissRequest = {
                                isBotSheetVisible = false
                            },
                            sheetState = botSheetState
                        ) {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(20.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 5.dp, bottom = 70.dp)
                            ) {
                                Text(
                                    "Cancel Booking",
                                    fontFamily = PoppinsFontFamily,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color(0xFFDA3D3D).copy((0.7f)),
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillMaxWidth()
                                )
                                HorizontalDivider(
                                    thickness = 2.dp,
                                    color = Color(0xFFC4C4C4).copy(0.2f),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 20.dp)
                                )
                                Text(
                                    "Are you sure you want to cancel this hotel booking?",
                                    fontFamily = PoppinsFontFamily,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillMaxWidth()
                                )
                                Text(
                                    "Only 80% would be refunded according to our cancellation policy.",
                                    fontFamily = PoppinsFontFamily,
                                    fontSize = 16.sp,
                                    color = Color.Black.copy(0.7f),
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillMaxWidth()
                                )
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    modifier = Modifier.fillMaxWidth(),
                                ) {
                                    Button(
                                        onClick = {
                                            val action = CancelReservationAction(reservationId)
                                            reservationViewModel.processAction(action)
                                        },
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = TrekkStayCyan,
                                            contentColor = Color.White
                                        ),
                                        contentPadding = PaddingValues(
                                            horizontal = 30.dp,
                                            vertical = 15.dp
                                        ),
                                        shape = RoundedCornerShape(10.dp)
                                    ) {
                                        Text(
                                            text = "Yes, Continue",
                                            fontSize = 15.sp,
                                            fontFamily = PoppinsFontFamily,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                "Completed" -> {
                    Spacer(modifier = Modifier.weight(1f))
                    if (!isRated) {
                        Button(
                            colors = ButtonDefaults.buttonColors(
                                containerColor = TrekkStayCyan.copy(0.4f),
                                contentColor = Color(0xFF068E9D)
                            ),
                            onClick = {
                                navController.navigate("customer_review/$hotelId/$hotelName")
                            }
                        ) {
                            Text(
                                "Review & Rating",
                                fontFamily = PoppinsFontFamily,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 13.sp
                            )
                        }
                    } else {
                        Text(
                            "Reviewed and Rated!",
                            fontFamily = PoppinsFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 13.sp
                        )
                    }
                }

                "Cancelled" -> {
                    Text(
                        "You've cancelled this hotel booking",
                        textAlign = TextAlign.Center,
                        fontFamily = PoppinsFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp,
                        color = Color(0xFFC82222).copy(0.8f),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

fun formatDate(inputDate: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd/MM/yy", Locale.getDefault())
    val date = inputFormat.parse(inputDate)
    return outputFormat.format(date)
}