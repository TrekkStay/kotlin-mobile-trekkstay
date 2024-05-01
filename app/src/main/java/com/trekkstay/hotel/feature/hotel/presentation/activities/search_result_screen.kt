package com.trekkstay.hotel.feature.hotel.presentation.activities

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hotel.R
import com.trekkstay.hotel.feature.hotel.domain.entities.Attraction
import com.trekkstay.hotel.feature.hotel.domain.entities.Hotel
import com.trekkstay.hotel.feature.hotel.domain.entities.MarkerInfo
import com.trekkstay.hotel.feature.hotel.presentation.fragments.CustomerFilterHotel
import com.trekkstay.hotel.feature.hotel.presentation.fragments.CustomerSortHotel
import com.trekkstay.hotel.feature.hotel.presentation.fragments.HotelSearchResultCard
import com.trekkstay.hotel.feature.hotel.presentation.states.attraction.AttractionState
import com.trekkstay.hotel.feature.hotel.presentation.states.attraction.AttractionViewModel
import com.trekkstay.hotel.feature.hotel.presentation.states.attraction.ViewAttractionAction
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily
import com.trekkstay.hotel.ui.theme.TrekkStayCyan
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SearchResultScreen(
    hotels: List<Hotel>,
    location: String,
    locationCode: String,
    checkIn: String,
    checkOut: String,
    numGuess: Int,
    attractionViewModel: AttractionViewModel,
    onBackPress: () -> Unit,
    navController: NavController
) {
    var sortCriteria by remember { mutableStateOf("") }
    var attractionList : List<Attraction> = emptyList()

    val neighborList = mutableListOf(
        "Testing",
    )

    val attractionState by attractionViewModel.state.observeAsState()
    var myAttraction = ""

    when (attractionState) {
        is AttractionState.SuccessAttractionList -> {
            attractionList = (attractionState as AttractionState.SuccessAttractionList).attractionList.attractionList
            myAttraction = attractionList[0].name
            for (item in attractionList){
                neighborList.add(item.name)
            }
        }

        is AttractionState.InvalidAttractionList -> {

        }

        is AttractionState.Idle -> {

        }

        else -> {

        }
    }

    LaunchedEffect(Unit) {
        val action = ViewAttractionAction(locationCode)
        attractionViewModel.processAction(action)
    }

    var filteredNeighborhood by remember { mutableStateOf("") }
    var filteredRatings by remember { mutableStateOf(listOf<Int>()) }
    var showResult by remember { mutableStateOf(false) }

    fun formatDateString(inputDate: String): String {
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val outputFormatter = DateTimeFormatter.ofPattern("MM/dd")
        val date = LocalDate.parse(inputDate, inputFormatter)
        return date.format(outputFormatter)
    }
    if (showResult) {
        val markerList = hotels.map { hotel ->
            val latLng = hotel.coordinates
            val price = hotel.room.firstOrNull()?.originalPrice ?: 0.0
            val name = hotel.name
            MarkerInfo(
                latLng, price.toDouble(),
                name
            )
        }
        MapWithMarkers(markerList = markerList) {
            showResult = false
        }

    } else {
        Column(
            modifier = Modifier
                .padding(top = 15.dp, bottom = 70.dp)
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
                    .background(
                        color = Color(0xFFC4C4C4).copy(alpha = 0.65f),
                        shape = RoundedCornerShape(15.dp)
                    )
                    .clickable {
                        onBackPress()
                    }
                    .padding(10.dp),

                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Default.Search,
                    contentDescription = null,
                    tint = TrekkStayCyan
                )

                Column(
                    verticalArrangement = Arrangement.SpaceAround,
                ) {
                    Text(
                        location,
                        textAlign = TextAlign.Left,
                        fontSize = 16.sp,
                        fontFamily = PoppinsFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        color = TrekkStayCyan,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Text(
                        text = "${formatDateString(checkIn)} - ${formatDateString(checkOut)} * $numGuess Guests",
                        fontSize = 16.sp,
                        fontFamily = PoppinsFontFamily,
                        fontWeight = FontWeight.Medium,
                        color = TrekkStayCyan,
                    )
                }
            }
            Spacer(modifier = Modifier.height(0.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
            ) {
                CustomerSortHotel(sortCriteria, onSort = { sortCriteria = it })
                CustomerFilterHotel(
                    neighborList,
                    filteredNeighborhood,
                    filteredRatings,
                    filterNeighborhood = { filteredNeighborhood = it },
                    filterRatings = { filteredRatings = it }
                )
                OutlinedButton(
                    contentPadding = PaddingValues(10.dp),
                    border = BorderStroke(2.dp, TrekkStayCyan),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color.Transparent,
                        contentColor = TrekkStayCyan
                    ),
                    modifier = Modifier.width(90.dp),
                    onClick = { showResult = true }
                )
                {
                    Icon(
                        ImageVector.vectorResource(R.drawable.map_ico),
                        contentDescription = null,
                        tint = TrekkStayCyan
                    )
                }
            }
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(15.dp),
            ) {
                items(hotels.size) {
                    HotelSearchResultCard(hotels[it],navController)
                }
            }
        }
    }
}
