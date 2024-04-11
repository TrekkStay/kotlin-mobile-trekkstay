package com.trekkstay.hotel.feature.customer.presentation.fragments

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trekkstay.hotel.feature.hotel.presentation.states.hotel.HotelState
import com.trekkstay.hotel.feature.hotel.presentation.states.hotel.HotelViewModel
import com.trekkstay.hotel.feature.hotel.presentation.states.hotel.ViewHotelAction
import com.trekkstay.hotel.feature.hotel.presentation.states.room.GetHotelRoomAction
import com.trekkstay.hotel.feature.hotel.presentation.states.room.RoomState
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily
import com.trekkstay.hotel.ui.theme.TrekkStayCyan
import kotlinx.coroutines.launch

@Composable
fun HotelTabsRow(hotelViewModel: HotelViewModel) {
    val hotelTabs = arrayOf("Nearby", "Recommended", "Popular", "Most Searched")
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { hotelTabs.size })
    val selectedTabIndex = remember {
        derivedStateOf { pagerState.currentPage }
    }
    val hotelState by hotelViewModel.state.observeAsState()
    when (hotelState) {
        is HotelState.SuccessViewHotel -> {
            val hotels = (hotelState as HotelState.SuccessViewHotel).list.hotelList

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp)
            ) {
                ScrollableTabRow(
                    divider = {},
                    selectedTabIndex = selectedTabIndex.value,
                    edgePadding = 0.dp,
                    modifier = Modifier.fillMaxWidth(),
                    indicator = { tabPositions ->
                        TabRowDefaults.SecondaryIndicator(
                            height = 5.dp,
                            modifier = Modifier
                                .tabIndicatorOffset(tabPositions[selectedTabIndex.value])
                                .padding(start = 25.dp, end = 25.dp),
                            color = TrekkStayCyan
                        )
                    }
                ) {
                    hotelTabs.forEachIndexed { index, tab ->
                        Tab(
                            selectedContentColor = Color.Black,
                            unselectedContentColor = Color.Black.copy(alpha = 0.35f),
                            modifier = Modifier.padding(0.dp),
                            selected = selectedTabIndex.value == index,
                            onClick = {
                                scope.launch {
                                    pagerState.animateScrollToPage(index)
                                }
                            },
                            text = {
                                val textStyle = if (selectedTabIndex.value == index) {
                                    TextStyle(
                                        fontFamily = PoppinsFontFamily,
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 16.sp
                                    )
                                } else {
                                    TextStyle(
                                        fontFamily = PoppinsFontFamily,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 16.sp
                                    )
                                }
                                Text(text = tab, style = textStyle)
                            }
                        )

                    }
                }
                HorizontalPager(
                    modifier = Modifier.fillMaxSize(),
                    state = pagerState,
                ) { page ->
                    val startIndex = page * 2
                    val endIndex = minOf(startIndex + 2, hotels.size)

                    if (startIndex < endIndex) {
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            contentPadding = PaddingValues(5.dp)
                        ) {
                            items(endIndex - startIndex) { index ->
                                val hotel = hotels[startIndex + index]
                                val roomPrice = if (hotel.room.isNotEmpty()) {
                                    hotel.room.first().originalPrice
                                } else {

                                    1
                                }
                                HotelCard(
                                    name = hotel.name,
                                    destination = hotel.addressDetail,
                                    rating = 5.0,
                                    price = roomPrice,
                                    imgUrl = "https://www.usatoday.com/gcdn/-mm-/05b227ad5b8ad4e9dcb53af4f31d7fbdb7fa901b/c=0-64-2119-1259/local/-/media/USATODAY/USATODAY/2014/08/13/1407953244000-177513283.jpg?width=1320&height=746&fit=crop&format=pjpg&auto=webp"
                                )
                            }
                        }
                    }
                }


            }
        }
        is HotelState.InvalidViewHotel -> {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp)
            ) {
                ScrollableTabRow(
                    divider = {},
                    selectedTabIndex = selectedTabIndex.value,
                    edgePadding = 0.dp,
                    modifier = Modifier.fillMaxWidth(),
                    indicator = { tabPositions ->
                        TabRowDefaults.SecondaryIndicator(
                            height = 5.dp,
                            modifier = Modifier
                                .tabIndicatorOffset(tabPositions[selectedTabIndex.value])
                                .padding(start = 25.dp, end = 25.dp),
                            color = TrekkStayCyan
                        )
                    }
                ) {
                    hotelTabs.forEachIndexed { index, tab ->
                        Tab(
                            selectedContentColor = Color.Black,
                            unselectedContentColor = Color.Black.copy(alpha = 0.35f),
                            modifier = Modifier.padding(0.dp),
                            selected = selectedTabIndex.value == index,
                            onClick = {
                                scope.launch {
                                    pagerState.animateScrollToPage(index)
                                }
                            },
                            text = {
                                val textStyle = if (selectedTabIndex.value == index) {
                                    TextStyle(
                                        fontFamily = PoppinsFontFamily,
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 16.sp
                                    )
                                } else {
                                    TextStyle(
                                        fontFamily = PoppinsFontFamily,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 16.sp
                                    )
                                }
                                Text(text = tab, style = textStyle)
                            }
                        )

                    }
                }
                HorizontalPager(
                    modifier = Modifier.fillMaxSize(),
                    state = pagerState,
                ) { page ->

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        contentPadding = PaddingValues(5.dp)
                    ) {
                        items(5) {

                            HotelCard(
                                name = "...",
                                destination = "...",
                                rating = 0.0,
                                price = 0,
                                imgUrl = "https://www.usatoday.com/gcdn/-mm-/05b227ad5b8ad4e9dcb53af4f31d7fbdb7fa901b/c=0-64-2119-1259/local/-/media/USATODAY/USATODAY/2014/08/13/1407953244000-177513283.jpg?width=1320&height=746&fit=crop&format=pjpg&auto=webp"
                            )
                        }
                    }
                }
            }
        }
        is HotelState.ViewHotelCalling -> {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp)
            ) {
                ScrollableTabRow(
                    divider = {},
                    selectedTabIndex = selectedTabIndex.value,
                    edgePadding = 0.dp,
                    modifier = Modifier.fillMaxWidth(),
                    indicator = { tabPositions ->
                        TabRowDefaults.SecondaryIndicator(
                            height = 5.dp,
                            modifier = Modifier
                                .tabIndicatorOffset(tabPositions[selectedTabIndex.value])
                                .padding(start = 25.dp, end = 25.dp),
                            color = TrekkStayCyan
                        )
                    }
                ) {
                    hotelTabs.forEachIndexed { index, tab ->
                        Tab(
                            selectedContentColor = Color.Black,
                            unselectedContentColor = Color.Black.copy(alpha = 0.35f),
                            modifier = Modifier.padding(0.dp),
                            selected = selectedTabIndex.value == index,
                            onClick = {
                                scope.launch {
                                    pagerState.animateScrollToPage(index)
                                }
                            },
                            text = {
                                val textStyle = if (selectedTabIndex.value == index) {
                                    TextStyle(
                                        fontFamily = PoppinsFontFamily,
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 16.sp
                                    )
                                } else {
                                    TextStyle(
                                        fontFamily = PoppinsFontFamily,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 16.sp
                                    )
                                }
                                Text(text = tab, style = textStyle)
                            }
                        )

                    }
                }
                HorizontalPager(
                    modifier = Modifier.fillMaxSize(),
                    state = pagerState,
                ) { page ->

                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            contentPadding = PaddingValues(5.dp)
                        ) {
                            items(5) {

                                HotelCard(
                                    name = "...",
                                    destination = "...",
                                    rating = 0.0,
                                    price = 0,
                                    imgUrl = "https://www.usatoday.com/gcdn/-mm-/05b227ad5b8ad4e9dcb53af4f31d7fbdb7fa901b/c=0-64-2119-1259/local/-/media/USATODAY/USATODAY/2014/08/13/1407953244000-177513283.jpg?width=1320&height=746&fit=crop&format=pjpg&auto=webp"
                                )
                            }
                        }
                    }
                }
        }

        else -> {
        }
    }

    LaunchedEffect(Unit) {
        if (hotelState !is HotelState.SuccessViewHotel) {
            val action = ViewHotelAction
            hotelViewModel.processAction(action)
        }
    }

}