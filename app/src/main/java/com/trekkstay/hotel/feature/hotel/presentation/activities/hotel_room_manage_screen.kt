package com.trekkstay.hotel.feature.hotel.presentation.activities

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.trekkstay.hotel.core.storage.LocalStore
import com.trekkstay.hotel.feature.hotel.domain.entities.Room
import com.trekkstay.hotel.feature.hotel.presentation.fragments.HotelRoomCard
import com.trekkstay.hotel.feature.hotel.presentation.states.room.GetHotelRoomAction
import com.trekkstay.hotel.feature.hotel.presentation.states.room.RoomState
import com.trekkstay.hotel.feature.hotel.presentation.states.room.RoomViewModel
import com.trekkstay.hotel.feature.hotel.presentation.states.room.ViewRoomAction
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily
import com.trekkstay.hotel.ui.theme.TrekkStayBlue

@Composable
fun HotelRoomManageScreen(roomViewModel: RoomViewModel, navController: NavHostController) {

    println("in here")
    var roomList: List<Room> = emptyList()
    val roomState by roomViewModel.state.observeAsState()
    val hotelId = LocalStore.getKey(LocalContext.current, "hotelId", "not created")
    LaunchedEffect(Unit) {
        val action = GetHotelRoomAction
        roomViewModel.processAction(action)

    }
    when (roomState) {
        is RoomState.SuccessGetHotelRoom -> {
            val action = ViewRoomAction(hotelId)
            roomViewModel.processAction(action)
        }

        is RoomState.SuccessViewRoom -> {
            roomList = (roomState as RoomState.SuccessViewRoom).roomList.roomList
        }

        is RoomState.InvalidGetHotelRoom -> {}
        is RoomState.GetHotelRoomCalling -> {}
        is RoomState.InvalidViewRoom -> {}
        is RoomState.ViewRoomCalling -> {}
        else -> {}
    }

    Scaffold(
        modifier = Modifier.padding(bottom = 70.dp),
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(10.dp),
                shape = RoundedCornerShape(30.dp),
                contentColor = TrekkStayBlue,
                containerColor = Color(0xFF89CFF3),
                onClick = {
                    navController.navigate("hotel_room_create") {
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true
                    }
                },
            ) {
                Icon(Icons.Filled.Add, "Floating action button.")
            }
        }
    ) { _ ->
        Column {
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    navController.navigate("hotel_profile")
                }) {
                    Icon(
                        Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = "backFromRoomManage"
                    )
                }
                Text(
                    text = "Your hotel rooms",
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp
                )
            }
            if (roomList.isEmpty()) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = "There is no room in your hotel yet.",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = PoppinsFontFamily,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Tap the + button to add a new room.",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = PoppinsFontFamily,
                        color = Color.Gray
                    )
                }
            } else {
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp, vertical = 10.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    roomList.forEach { room ->
                        HotelRoomCard(
                            room,
                            navController
                        )
                    }
                }
            }
        }
    }
}



