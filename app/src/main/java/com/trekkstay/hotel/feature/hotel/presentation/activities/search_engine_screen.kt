package com.trekkstay.hotel.feature.hotel.presentation.activities

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.trekkstay.hotel.core.storage.LocalStore
import com.trekkstay.hotel.feature.hotel.domain.entities.Destination
import com.trekkstay.hotel.feature.hotel.domain.entities.Hotel
import com.trekkstay.hotel.feature.hotel.presentation.fragments.CustomerRoomOptSelector
import com.trekkstay.hotel.feature.hotel.presentation.fragments.DateRangeSelector
import com.trekkstay.hotel.feature.hotel.presentation.fragments.DestinationSearchBar
import com.trekkstay.hotel.feature.hotel.presentation.states.attraction.AttractionViewModel
import com.trekkstay.hotel.feature.hotel.presentation.states.search.SearchHotelAction
import com.trekkstay.hotel.feature.hotel.presentation.states.search.SearchState
import com.trekkstay.hotel.feature.hotel.presentation.states.search.SearchViewModel
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SearchEngineScreen(
    searchViewModel: SearchViewModel,
    navController: NavHostController,
    attractionViewModel: AttractionViewModel
) {
    val context = LocalContext.current
    var selectedDestination by remember { mutableStateOf<Destination?>(null) }
    var selectedDateRange by remember { mutableStateOf<Pair<Long, Long>?>(Pair(System.currentTimeMillis(), System.currentTimeMillis() + 86400000)) }
    var roomNumber by remember { mutableIntStateOf(1) }
    var adultNumber by remember { mutableIntStateOf(2) }
    var childNumber by remember { mutableIntStateOf(1) }
    var showResult by remember { mutableStateOf(false) }

    var searchedHotel by remember {
        mutableStateOf(listOf<Hotel>())
    }

    LaunchedEffect(Unit) {
        showResult = false
    }

    fun formatDateRange(startDateMillis: Long, endDateMillis: Long): Pair<String, String> {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val startDate =
            LocalDateTime.ofInstant(Instant.ofEpochMilli(startDateMillis), ZoneId.systemDefault())
        val endDate =
            LocalDateTime.ofInstant(Instant.ofEpochMilli(endDateMillis), ZoneId.systemDefault())
        val formattedStartDate = startDate.format(formatter)
        val formattedEndDate = endDate.format(formatter)
        return Pair(formattedStartDate, formattedEndDate)
    }

    val searchState by searchViewModel.state.observeAsState()
    when (searchState) {
        is SearchState.SuccessSearchHotel -> {
            showResult = true
            searchedHotel = (searchState as SearchState.SuccessSearchHotel).list.hotelList
        }

        is SearchState.InvalidSearchHotel -> {
            println((searchState as SearchState.InvalidSearchHotel).message)
        }
        is SearchState.SearchHotelCalling -> { }
        else -> { }
    }


    Scaffold(
    ) { innerPadding ->
        if (showResult && searchedHotel.isNotEmpty()) {
            val (formattedCheckInDate, formattedCheckOutDate) = formatDateRange(
                selectedDateRange?.first ?: 1716422400000,
                selectedDateRange?.second ?: 176422400000
            )
            SearchResultScreen(
                hotels = searchedHotel,
                location = selectedDestination?.name ?: "Ho Chi Minh",
                locationCode = selectedDestination?.code ?: "79",
                numGuess = adultNumber + childNumber,
                checkIn = formattedCheckInDate,
                checkOut = formattedCheckOutDate,
                attractionViewModel = attractionViewModel,
                navController = navController,
                onBackPress = {
                    showResult = false
                }
            )
        } else {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                            contentDescription = "backFromSearch"
                        )
                    }
                    Text(
                        text = "Search Hotel",
                        fontFamily = PoppinsFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    modifier = Modifier
                        .padding(20.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color(0xFFE4E4E4).copy(0.5f))
                        .padding(20.dp)
                ) {
                    DestinationSearchBar(searchViewModel,
                        onDestinationSelected = {
                            selectedDestination = it
                        }
                    )
                    selectedDateRange?.first?.let {
                        DateRangeSelector(type = "search", startDate = it, endDate = selectedDateRange!!.second)
                        { dateRange ->
                            selectedDateRange = dateRange
                        }
                    }
                    CustomerRoomOptSelector(
                        onRoomNumberSelected = {
                            roomNumber = it
                            LocalStore.saveKey(
                                context,
                                "search_room_num",
                                it.toString()
                            )
                        },
                        onAdultNumberSelected = {
                            adultNumber = it
                            LocalStore.saveKey(
                                context,
                                "search_adult_num",
                                it.toString()
                            )
                        },
                        onChildNumberSelected = {
                            childNumber = it
                            LocalStore.saveKey(
                                context,
                                "search_child_num",
                                it.toString()
                            )
                        })
                    Row {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .background(Color(0xFF238C98).copy(0.24f))
                                .size(240.dp, 30.dp)
                                .clickable {
                                    if (selectedDestination != null) {
                                        val (formattedCheckInDate, formattedCheckOutDate) = formatDateRange(
                                            selectedDateRange!!.first,
                                            selectedDateRange!!.second
                                        )
                                        val action =
                                            SearchHotelAction(
                                                locationCode = selectedDestination!!.code,
                                                limit = 40,
                                                page = 1,
                                                numOfRoom = roomNumber,
                                                adults = adultNumber,
                                                children = childNumber,
                                                checkInDate = formattedCheckInDate,
                                                checkOutDate = formattedCheckOutDate,
                                            )
                                        searchViewModel.processAction(action)

                                    }
                                }
                        ) {
                            Text(
                                text = "Search",
                                fontWeight = FontWeight.Bold,
                                fontFamily = PoppinsFontFamily,
                                color = Color(0xFF238C98).copy(0.9f),
                                fontSize = 16.sp,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                }
            }
        }
    }


}