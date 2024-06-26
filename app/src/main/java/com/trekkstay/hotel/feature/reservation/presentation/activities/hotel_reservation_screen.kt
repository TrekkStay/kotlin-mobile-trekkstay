package com.trekkstay.hotel.feature.reservation.presentation.activities

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
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.trekkstay.hotel.core.storage.LocalStore
import com.trekkstay.hotel.feature.reservation.domain.entities.Reservation
import com.trekkstay.hotel.feature.reservation.presentation.fragments.HotelReservationCard
import com.trekkstay.hotel.feature.reservation.presentation.fragments.ReservationTabIndicator
import com.trekkstay.hotel.feature.reservation.presentation.states.ListReservationAction
import com.trekkstay.hotel.feature.reservation.presentation.states.ReservationState
import com.trekkstay.hotel.feature.reservation.presentation.states.ReservationViewModel
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily
import com.trekkstay.hotel.ui.theme.TrekkStayBlue
import kotlinx.coroutines.launch

@Composable
fun HotelReservationScreen(
    reservationViewModel: ReservationViewModel,
    navController: NavController
) {
    val context = LocalContext.current
    val hotelId = LocalStore.getKey(context, "hotelId", "")
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

    println(reservationHotel1)
    println(reservationHotel2)
    println(reservationHotel3)


    val reservationState by reservationViewModel.state.observeAsState()
    when (reservationState) {
        is ReservationState.SuccessListReservation -> {
            val reservationHotel =
                (reservationState as ReservationState.SuccessListReservation).reservation.reservationList

            if ((reservationState as ReservationState.SuccessListReservation).sendState == "UPCOMING") {
                reservationHotel1 = reservationHotel
            } else if ((reservationState as ReservationState.SuccessListReservation).sendState == "COMPLETED") {
                reservationHotel2 = reservationHotel
            } else if ((reservationState as ReservationState.SuccessListReservation).sendState == "CANCELLED") {
                reservationHotel3 = reservationHotel
            }
        }
        is ReservationState.InvalidListReservation -> { }
        is ReservationState.ListReservationCalling -> { }
        else -> { }
    }

    LaunchedEffect(Unit) {
        val action1 = ListReservationAction(hotelId, "UPCOMING", "")
        reservationViewModel.processAction(action1)

        val action2 = ListReservationAction(hotelId, "COMPLETED", "")
        reservationViewModel.processAction(action2)

        val action3 = ListReservationAction(hotelId, "CANCELLED", "")
        reservationViewModel.processAction(action3)
    }

    Column(
        modifier = Modifier.padding(top = 15.dp, bottom = 70.dp)
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
                color = TrekkStayBlue,
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
        ) {
            TabRow(
                divider = {},
                selectedTabIndex = selectedTabIndex.value,
                modifier = Modifier.fillMaxWidth().padding(top = 15.dp, bottom = 5.dp,start = 10.dp , end = 10.dp),
                indicator = @Composable { tabPositions: List<TabPosition> ->
                    ReservationTabIndicator(tabPositions, pagerState, "hotel")
                }
            ) {
                hotelTabs.forEachIndexed { index, tab ->
                    Tab(
                        selectedContentColor = Color.White,
                        unselectedContentColor = TrekkStayBlue,
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                            .border(BorderStroke(2.dp, TrekkStayBlue), RoundedCornerShape(50)),
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
                Box(Modifier.fillMaxSize()) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        contentPadding = PaddingValues(15.dp)
                    ) {
                        itemsIndexed(
                            when (page) {
                                0 -> reservationHotel1
                                1 -> reservationHotel2
                                2 -> reservationHotel3
                                else -> emptyList()
                            }
                        ) {_, item ->
                            when (page) {
                                0 -> {
                                    HotelReservationCard(
                                        reservationId = item.id,
                                        roomImg = item.room.images.media[0],
                                        customerId = "#${item.userId}",
                                        customerName = item.guestInfo.name,
                                        roomType = item.room.type,
                                        checkIn = item.checkIn,
                                        checkOut = item.checkOut,
                                        price = item.totalPrice,
                                        type = "Upcoming",
                                        navController
                                    )
                                }
                                1 -> {
                                    HotelReservationCard(
                                        reservationId = item.id,
                                        roomImg = item.room.images.media[0],
                                        customerId = "#${item.userId}",
                                        customerName = item.guestInfo.name,
                                        roomType = item.room.type,
                                        checkIn = item.checkIn,
                                        checkOut = item.checkOut,
                                        price = item.totalPrice,
                                        type = "Completed",
                                        navController
                                    )
                                }
                                2 -> {
                                    HotelReservationCard(
                                        reservationId = item.id,
                                        roomImg = item.room.images.media[0],
                                        customerId = "#${item.userId}",
                                        customerName = item.guestInfo.name,
                                        roomType = item.room.type,
                                        checkIn = item.checkIn,
                                        checkOut = item.checkOut,
                                        price = item.totalPrice,
                                        type = "Cancelled",
                                        navController
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

