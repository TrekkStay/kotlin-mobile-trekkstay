package com.trekkstay.hotel.feature.hotel.presentation.activities

import android.content.Context
import android.net.Uri
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.hotel.R
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.trekkstay.hotel.feature.hotel.presentation.fragments.FacilityBullet
import com.trekkstay.hotel.feature.hotel.presentation.states.hotel.HotelDetailAction
import com.trekkstay.hotel.feature.hotel.presentation.states.hotel.HotelState
import com.trekkstay.hotel.feature.hotel.presentation.states.hotel.HotelViewModel
import com.trekkstay.hotel.feature.shared.Utils.labelizeRating
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily
import com.trekkstay.hotel.ui.theme.TrekkStayCyan

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HotelDetailScreen(
    navController: NavController,
    hotelViewModel: HotelViewModel,
    id: String
) {
    val context = LocalContext.current
    var star by remember { mutableDoubleStateOf(0.0) }
    var reviewNum by remember { mutableIntStateOf(0) }
    var liked by remember { mutableStateOf(false) }
    val likedTint = (if (liked) TrekkStayCyan else Color(0xFFB8B8B9))
    var expandedDesc by remember { mutableStateOf(false) }


    LaunchedEffect(Unit) {
        val action = HotelDetailAction(id)
        hotelViewModel.processAction(action)
    }

    val hotelState by hotelViewModel.state.observeAsState()
    when (hotelState) {
        is HotelState.SuccessHotelDetail -> {
            val hotel = (hotelState as HotelState.SuccessHotelDetail).hotel
            star = hotel.rating.averagePoint
            reviewNum = hotel.rating.totalReview

            val cheapRoomPrice = if (hotel.room.isNotEmpty()) {
                hotel.room.first().originalPrice
            } else {
                1
            }.toString()
            val hotelImgList = if (hotel.images.media.isEmpty()) {
                arrayOf(
                    "https://www.usatoday.com/gcdn/-mm-/05b227ad5b8ad4e9dcb53af4f31d7fbdb7fa901b/c=0-64-2119-1259/local/-/media/USATODAY/USATODAY/2014/08/13/1407953244000-177513283.jpg?width=1320&height=746&fit=crop&format=pjpg&auto=webp",
                )

            } else {
                hotel.images.media.toTypedArray()
            }
            val hotelVideoList = hotel.videos.media.toTypedArray()

            Box(
                modifier = Modifier.padding(bottom = 70.dp)
            ) {
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(bottom = 80.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(200.dp)
                    ) {
                        HorizontalPager(state = rememberPagerState(pageCount = { hotelVideoList.size + hotelImgList.size })) {
                            Box() {
                                if (hotel.videos.media.isNotEmpty() && it < hotelVideoList.size) {
                                    AndroidView(
                                        factory = { context ->
                                            PlayerView(context).apply {
                                                player = ExoPlayer.Builder(context).build().apply {
                                                    setMediaItem(MediaItem.fromUri(Uri.parse(hotel.videos.media[it])))
                                                    repeatMode = ExoPlayer.REPEAT_MODE_ALL
                                                    playWhenReady = playWhenReady
                                                    prepare()
                                                    play()
                                                }
                                                useController = true
                                                layoutParams = ViewGroup.LayoutParams(
                                                    ViewGroup.LayoutParams.MATCH_PARENT,
                                                    ViewGroup.LayoutParams.MATCH_PARENT
                                                )
                                            }
                                        },
                                        modifier = Modifier.fillMaxSize()
                                    )
                                } else {
                                    AsyncImage(
                                        model = hotelImgList[it - hotelVideoList.size],
                                        contentDescription = null,
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .size(200.dp)
                                    )
                                }
                                Text(
                                    "${it + 1}/${hotelImgList.size + hotelVideoList.size}",
                                    fontSize = 14.sp,
                                    fontFamily = PoppinsFontFamily,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color.White,
                                    modifier = Modifier
                                        .offset(x = -5.dp, y = -5.dp)
                                        .background(
                                            Color.Black.copy(alpha = 0.4f),
                                            RoundedCornerShape(15.dp)
                                        )
                                        .padding(vertical = 5.dp, horizontal = 10.dp)
                                        .align(Alignment.BottomEnd)
                                )
                            }
                        }
                        IconButton(
                            modifier = Modifier.background(
                                Color.Black.copy(alpha = 0.4f),
                                shape = CircleShape
                            ),
                            onClick = { navController.popBackStack() }
                        ) {
                            Icon(
                                Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 15.dp, vertical = 10.dp)
                    ) {
                        Text(
                            text = hotel.name,
                            fontFamily = PoppinsFontFamily,
                            fontWeight = FontWeight.Medium,
                            fontSize = 20.sp,
                            modifier = Modifier.weight(1f)
                        )
                        IconButton(onClick = { liked = !liked }) {
                            Icon(
                                Icons.Default.Favorite,
                                contentDescription = null,
                                tint = likedTint,
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    }
                    HorizontalDivider(color = Color(0xFFB8B8B9), thickness = 2.dp)
                    Column(
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 25.dp, vertical = 20.dp)
                    ) {
                        Text(
                            "Location",
                            fontFamily = PoppinsFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp
                        )
                        Box(
                            modifier = Modifier
                                .aspectRatio(3.5f / 1f)
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(16.dp))
                                .background(Color.White)
                        ) {
                            MapViewContainer(location = hotel.coordinates, context = context)
                        }
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(5.dp)
                        ) {
                            Icon(
                                Icons.Default.Place,
                                contentDescription = null,
                                tint = TrekkStayCyan,
                                modifier = Modifier.size(35.dp)
                            )
                            Text(
                                hotel.addressDetail,
                                textAlign = TextAlign.Justify,
                                fontFamily = PoppinsFontFamily,
                                fontSize = 12.sp,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                    HorizontalDivider(color = Color(0xFFB8B8B9), thickness = 2.dp)
                    Column(
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 25.dp, vertical = 20.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                "What they offer",
                                fontFamily = PoppinsFontFamily,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp
                            )
                        }
                        FlowRow(
                            verticalArrangement = Arrangement.spacedBy(15.dp),
                            horizontalArrangement = Arrangement.Absolute.SpaceAround,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 10.dp)
                        ) {
                            FacilityBullet(label = "Free Wifi")
                            FacilityBullet(label = "Food Service")
                            FacilityBullet(label = "Spa Service")
                            FacilityBullet(label = "Motorbike Rental")
                            FacilityBullet(label = "Fitness Center")
                            FacilityBullet(label = "Parking Area")
                        }
                    }
                    HorizontalDivider(color = Color(0xFFB8B8B9), thickness = 2.dp)
                    Column(
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 25.dp, vertical = 20.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                "Hotel description",
                                fontFamily = PoppinsFontFamily,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp
                            )
                            ClickableText(
                                text = AnnotatedString("Show more"),
                                style = TextStyle(
                                    color = TrekkStayCyan,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    fontFamily = PoppinsFontFamily
                                ),
                                onClick = {
                                    expandedDesc = !expandedDesc
                                }
                            )
                        }
                        Text(
                            hotel.description,
                            textAlign = TextAlign.Justify,
                            fontFamily = PoppinsFontFamily,
                            fontSize = 12.sp,
                            maxLines = if (expandedDesc) Int.MAX_VALUE else 3,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    HorizontalDivider(color = Color(0xFFB8B8B9), thickness = 2.dp)
                    Column(
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 25.dp, vertical = 20.dp)
                    ) {
                        Text(
                            "Some helpful facts",
                            fontFamily = PoppinsFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp
                        )
                        Column(
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            FactRow(
                                icon = ImageVector.vectorResource(R.drawable.time_ico),
                                label = "Check-in from",
                                value = hotel.checkInTime
                            )
                            FactRow(
                                icon = ImageVector.vectorResource(R.drawable.time_ico),
                                label = "Check-out until",
                                value = hotel.checkOutTime
                            )
                        }
                    }
                    HorizontalDivider(color = Color(0xFFB8B8B9), thickness = 2.dp)
                    Column(
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 20.dp)
                            .clickable {
                                navController.navigate("review_list/${hotel.id}/${hotel.name}/${reviewNum}/${star}") {
                                    launchSingleTop = true
                                }
                            }
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 25.dp)
                        ) {
                            Text(
                                text = "$star",
                                fontSize = 20.sp,
                                fontFamily = PoppinsFontFamily,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                modifier = Modifier
                                    .background(
                                        TrekkStayCyan,
                                        shape = CircleShape
                                    )
                                    .padding(15.dp)
                            )
                            Column(
                                modifier = Modifier.fillMaxHeight()
                            ) {
                                Text(
                                    "${labelizeRating(star)}",
                                    fontFamily = PoppinsFontFamily,
                                    fontWeight = FontWeight.Bold,
                                    color = TrekkStayCyan,
                                    fontSize = 16.sp
                                )
                                Text(
                                    buildAnnotatedString {
                                        withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold)) {
                                            append("$reviewNum")
                                        }
                                        append(" reviews")
                                    },
                                    fontFamily = PoppinsFontFamily,
                                )
                            }
                        }
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(25.dp, shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                        .background(
                            Color.White,
                            shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp)
                        )
                        .padding(20.dp)
                        .align(Alignment.BottomCenter)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            "Start At",
                            fontSize = 14.sp,
                            fontFamily = PoppinsFontFamily
                        )
                        Text(
                            "$$cheapRoomPrice",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            fontFamily = PoppinsFontFamily,
                            color = TrekkStayCyan
                        )
                    }
                    Button(
                        onClick = {
                            navController.navigate("hotel_room_detail/${hotel.id}/${hotel.name}") {
                                launchSingleTop = true
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF238C98)),
                        modifier = Modifier
                            .size(180.dp, 40.dp),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Icon(
                            ImageVector.vectorResource(R.drawable.bed_ico),
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "See All Rooms",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        is HotelState.InvalidHotelDetail -> {

        }

        is HotelState.HotelDetailCalling -> {
        }

        else -> {
            // Handle other states
        }
    }
}

@Composable
fun FactRow(
    icon: ImageVector,
    label: String,
    value: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = TrekkStayCyan
        )
        Text(
            buildAnnotatedString {
                append("$label: ")
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(value)
                }
            },
            fontFamily = PoppinsFontFamily,
            fontSize = 16.sp,
        )
    }
}

@Composable
private fun MapViewContainer(
    location: LatLng,
    context: Context
) {
    AndroidView(
        factory = { _ ->
            MapView(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                getMapAsync { googleMap ->
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15f))

                    // Disable user interaction with the map
                    googleMap.uiSettings.setAllGesturesEnabled(false)

                    // Add marker for the given location
                    googleMap.addMarker(MarkerOptions().position(location))
                }
                onCreate(null)
                onResume()
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}


