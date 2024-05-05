package com.trekkstay.hotel.feature.customer.presentation.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.trekkstay.hotel.feature.hotel.domain.entities.Hotel
import com.trekkstay.hotel.feature.hotel.presentation.states.hotel.HotelState
import com.trekkstay.hotel.feature.hotel.presentation.states.hotel.HotelViewModel
import com.trekkstay.hotel.feature.hotel.presentation.states.hotel.ViewHotelNearAction
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily
import com.trekkstay.hotel.ui.theme.TrekkStayCyan
import kotlinx.coroutines.launch

@Composable
fun HotelTabsRow(hotelViewModel: HotelViewModel, navController: NavHostController) {
    val context = LocalContext.current
    val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)
    var userLocation by remember { mutableStateOf(LatLng(0.0, 0.0)) }
    var viewLocationCheck by remember { mutableStateOf(true) }
    val locationPermissionLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                getLocation(fusedLocationClient, context) { location ->
                    location?.let { LatLng(it.latitude, it.longitude) }
                        ?.let {
                            userLocation = it
                            val action =
                                ViewHotelNearAction(coordinate = userLocation, maxRange = 200.0)
                            hotelViewModel.processAction(action)
                        }
                }
            }
        }
    var nearByHotels by remember { mutableStateOf<List<Hotel?>>(emptyList()) }
    var nearByMsg by remember { mutableStateOf("") }
    var mostRatedHotels by remember { mutableStateOf<List<Hotel?>>(emptyList()) }
    var mostSearchedHotels by remember { mutableStateOf<List<Hotel?>>(emptyList()) }
    LaunchedEffect(Unit) {
        if (viewLocationCheck) {
            if (checkLocationPermission(context)) {
                getLocation(fusedLocationClient, context) { location ->
                    location?.let { LatLng(it.latitude, it.longitude) }
                        ?.let {
                            userLocation = it
                            val action =
                                ViewHotelNearAction(coordinate = userLocation, maxRange = 200.0)
                            hotelViewModel.processAction(action)
                        }
                }
            } else {
                locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
            viewLocationCheck = false
        }
    }
    val hotelTabs = arrayOf("Nearby", "Most Rated", "Most Searched")
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { hotelTabs.size })
    val selectedTabIndex = remember {
        derivedStateOf { pagerState.currentPage }
    }
    val hotelState by hotelViewModel.state.observeAsState()
    when (hotelState) {
        is HotelState.SuccessViewHotelNear -> {
            nearByHotels = (hotelState as HotelState.SuccessViewHotelNear).list
            nearByMsg = "No nearby hotels found"
            println(nearByHotels)
        }
        is HotelState.InvalidViewHotelNear -> {
            nearByHotels = emptyList()
            nearByMsg = "Something went wrong"
        }
        is HotelState.ViewHotelNearCalling -> {
            nearByHotels = emptyList()
            nearByMsg = "Loading..."
        }
        else -> { nearByHotels = emptyList() }
    }
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
            when (page) {
                0 -> {
                    if (nearByHotels.isNotEmpty()) {
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            contentPadding = PaddingValues(5.dp)
                        ) {
                            items(minOf(2, nearByHotels.size)) { index ->
                                HotelCard(
                                    hotel = nearByHotels[index],
                                    navController = navController
                                )
                            }
                        }
                    } else {
                        HotelTabsAnnouncement(nearByMsg)
                    }
                }
                1 -> {
                    if (mostRatedHotels.isNotEmpty()) {
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            contentPadding = PaddingValues(5.dp)
                        ) {
                            items(minOf(2, mostRatedHotels.size)) { index ->
                                HotelCard(
                                    hotel = mostRatedHotels[index],
                                    navController = navController
                                )
                            }
                        }
                    } else {
                        HotelTabsAnnouncement(msg = "No most rated hotels found")
                    }
                }
                2 -> {
                    if (mostSearchedHotels.isNotEmpty()) {
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            contentPadding = PaddingValues(5.dp)
                        ) {
                            items(minOf(2, mostSearchedHotels.size)) { index ->
                                HotelCard(
                                    hotel = mostSearchedHotels[index],
                                    navController = navController
                                )
                            }
                        }
                    } else {
                        HotelTabsAnnouncement(msg = "No most searched hotels found")
                    }
                }
            }
        }
    }
}

@Composable
private fun HotelTabsAnnouncement (
    msg: String
) {
    val configuration = LocalConfiguration.current
    val screenWidth = remember(configuration) { configuration.screenWidthDp.dp }
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.size(screenWidth,170.dp)
    ) {
        Text(
            msg,
            fontFamily = PoppinsFontFamily,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            color = Color(0xFF303030).copy(alpha = 0.24f),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

fun checkLocationPermission(context: Context): Boolean {
    return ContextCompat.checkSelfPermission(
        context,
        Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED
}

@SuppressLint("MissingPermission")
fun getLocation(
    fusedLocationClient: FusedLocationProviderClient,
    context: Context,
    callback: (Location?) -> Unit
) {

    if (ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    ) {
        // Permissions granted, proceed to get location
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            callback(location)
        }.addOnFailureListener { _ ->
            callback(null)
        }
    } else {
        callback(null)
    }
}
