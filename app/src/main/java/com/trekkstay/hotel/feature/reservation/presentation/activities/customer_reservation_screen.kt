package com.trekkstay.hotel.feature.reservation.presentation.activities

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.trekkstay.hotel.core.storage.LocalStore
import com.trekkstay.hotel.feature.hotel.domain.entities.Hotel
import com.trekkstay.hotel.feature.hotel.presentation.states.hotel.HotelDetailAction
import com.trekkstay.hotel.feature.hotel.presentation.states.location.ViewProvinceAction
import com.trekkstay.hotel.feature.hotel.presentation.states.search.SearchState
import com.trekkstay.hotel.feature.reservation.domain.entities.Reservation
import com.trekkstay.hotel.feature.reservation.presentation.fragments.CustomerReservationCard
import com.trekkstay.hotel.feature.reservation.presentation.fragments.ReservationTabIndicator
import com.trekkstay.hotel.feature.reservation.presentation.states.ListReservationAction
import com.trekkstay.hotel.feature.reservation.presentation.states.ReservationState
import com.trekkstay.hotel.feature.reservation.presentation.states.ReservationViewModel
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily
import com.trekkstay.hotel.ui.theme.TrekkStayCyan
import kotlinx.coroutines.launch

@Composable
fun CustomerReservationScreen(
    reservationViewModel: ReservationViewModel,
    navController: NavController
) {
    var countCallingAPI by remember {
        mutableStateOf(0)
    }
    val hotelTabs = arrayOf("Upcoming", "Completed", "Cancelled")
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { hotelTabs.size })
    val selectedTabIndex = remember {
        derivedStateOf { pagerState.currentPage }
    }

    var reservationHotel1 by remember {
        mutableStateOf(listOf<Reservation>())
    }
    var reservationHotel2 by remember {
        mutableStateOf(listOf<Reservation>())
    }
    var reservationHotel3 by remember {
        mutableStateOf(listOf<Reservation>())
    }
    val reservationState by reservationViewModel.state.observeAsState()
    when (reservationState) {
        is ReservationState.SuccessListReservation -> {
            println("okkkkkkkkkkkkk")
            val reservationHotel =
                (reservationState as ReservationState.SuccessListReservation).reservation.reservationList

            if (countCallingAPI == 0) {
                reservationHotel1 = reservationHotel
                countCallingAPI++;
                val action2 = ListReservationAction("", "COMPLETED", "")
                reservationViewModel.processAction(action2)
            } else if (countCallingAPI == 1) {
                reservationHotel2 = reservationHotel
                countCallingAPI++;
                val action3 = ListReservationAction("", "CANCELLED", "")
                reservationViewModel.processAction(action3)
            } else if (countCallingAPI == 2) {
                countCallingAPI++;
                reservationHotel3 = reservationHotel
            }

        }

        is ReservationState.InvalidListReservation -> {
            println((reservationState as ReservationState.InvalidListReservation).message)
        }

        is ReservationState.ListReservationCalling -> {
            println("checking")
        }

        else -> {}
    }

    println(">>>>>>>>>>>>>>>>>>>>>>Q")
    println(reservationHotel1)
    println(reservationHotel2)
    println(reservationHotel3)


    LaunchedEffect(selectedTabIndex.value) {
//        reservationHotel = emptyList()

//        val action = when (selectedTabIndex.value) {
//            0 -> ListReservationAction("", "UPCOMING", "")
//            1 -> ListReservationAction("", "COMPLETED", "")
//            2 -> ListReservationAction("", "CANCELLED", "")
//            else -> ListReservationAction("", "", "") // Default action
//        }
        val action1 = ListReservationAction("", "UPCOMING", "")
        reservationViewModel.processAction(action1)
    }


    Column(
        modifier = Modifier.padding(top = 15.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Reservation",
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.SemiBold,
                color = TrekkStayCyan,
                fontSize = 20.sp
            )
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    Icons.Default.Search,
                    contentDescription = null,
                    tint = Color.Black.copy(0.5f)
                )
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
        HorizontalDivider(color = Color(0xFFE4E4E4), thickness = 2.dp)

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, start = 10.dp, end = 10.dp)
        ) {
            TabRow(
                divider = {},
                selectedTabIndex = selectedTabIndex.value,
                modifier = Modifier.fillMaxWidth(),
                indicator = @Composable { tabPositions: List<TabPosition> ->
                    ReservationTabIndicator(tabPositions, pagerState, "customer")
//                    val action = ListReservationAction("", hotelTabs[selectedTabIndex.value], "")
//                    reservationViewModel.processAction(action)

                }

            ) {
                hotelTabs.forEachIndexed { index, tab ->
                    Tab(
                        selectedContentColor = Color.White,
                        unselectedContentColor = TrekkStayCyan,
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                            .border(BorderStroke(2.dp, TrekkStayCyan), RoundedCornerShape(50)),
                        selected = selectedTabIndex.value == index,
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                        text = {
                            Text(
                                text = tab,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = PoppinsFontFamily
                            )
                        }
                    )

                }
            }

            HorizontalPager(
                modifier = Modifier.fillMaxSize(),
                state = pagerState,
            ) { page ->
                val reservationType = when (page) {
                    0 -> "Upcoming"
                    1 -> "Completed"
                    2 -> "Cancelled"
                    else -> "Unknown"
                }
                Box(Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(20.dp),
                    ) {
                        if (page == 0) {
                            reservationHotel1.forEach { item ->
                                CustomerReservationCard(
                                    hotelImg = item.room.images.media[0] ?: "",
                                    hotelName = item.room.hotelName ?: "",
                                    destination = item.room.location ?: "",
                                    type = reservationType,
                                    price = item.room.bookingPrice.toDouble() ?: 0.0
                                )
                            }
                        }
                        else if (page == 1) {
                            reservationHotel2.forEach { item ->
                                CustomerReservationCard(
                                    hotelImg = item.room.images.media[0] ?: "",
                                    hotelName = item.room.hotelName ?: "",
                                    destination = item.room.location ?: "",
                                    type = reservationType,
                                    price = item.room.bookingPrice.toDouble() ?: 0.0
                                )
                            }
                        }
                        else if (page == 2) {
                            reservationHotel3.forEach { item ->
                                CustomerReservationCard(
                                    hotelImg = item.room.images.media[0] ?: "",
                                    hotelName = item.room.hotelName ?: "",
                                    destination = item.room.location ?: "",
                                    type = reservationType,
                                    price = item.room.bookingPrice.toDouble() ?: 0.0
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
