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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trekkstay.hotel.feature.reservation.presentation.fragments.CustomerReservationCard
import com.trekkstay.hotel.feature.reservation.presentation.fragments.ReservationTabIndicator
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily
import com.trekkstay.hotel.ui.theme.TrekkStayCyan
import kotlinx.coroutines.launch

@Composable
fun CustomerReservationScreen() {
    Column (
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
        CustomerReservationTabsRow()
    }
}

@Composable
fun CustomerReservationTabsRow() {
    val hotelTabs = arrayOf("Upcoming", "Completed", "Cancelled")
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { hotelTabs.size })
    val selectedTabIndex = remember {
        derivedStateOf { pagerState.currentPage }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp,start = 10.dp, end = 10.dp)
    ) {
        TabRow(
            divider = {},
            selectedTabIndex = selectedTabIndex.value,
            modifier = Modifier.fillMaxWidth(),
            indicator = @Composable { tabPositions: List<TabPosition> ->
                ReservationTabIndicator(tabPositions, pagerState, "customer")
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
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    contentPadding = PaddingValues(top = 25.dp, bottom = 100.dp,start = 5.dp, end = 5.dp)
                ) {
                    items(5) {
                        CustomerReservationCard(
                            hotelImg = "https://www.usatoday.com/gcdn/-mm-/05b227ad5b8ad4e9dcb53af4f31d7fbdb7fa901b/c=0-64-2119-1259/local/-/media/USATODAY/USATODAY/2014/08/13/1407953244000-177513283.jpg?width=1320&height=746&fit=crop&format=pjpg&auto=webp",
                            hotelName = "Brent Hotel",
                            destination = "Hoi An",
                            type = reservationType,
                            price = 100.0
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, device = "spec:width=411dp,height=891dp")
@Composable
fun MyAppPreview() {
    CustomerReservationScreen()
}
