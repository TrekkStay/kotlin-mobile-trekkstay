package com.trekkstay.hotel.feature.hotel.presentation.activities

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.AlertDialog
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.hotel.R
import com.trekkstay.hotel.feature.hotel.domain.entities.Location
import com.trekkstay.hotel.feature.hotel.presentation.fragments.FacilityChip
import com.trekkstay.hotel.feature.hotel.presentation.fragments.HotelActionRow
import com.trekkstay.hotel.feature.hotel.presentation.fragments.HotelRoomOptSelector
import com.trekkstay.hotel.feature.hotel.presentation.fragments.InfoTextField
import com.trekkstay.hotel.feature.hotel.presentation.states.hotel.GetHotelIdAction
import com.trekkstay.hotel.feature.hotel.presentation.states.hotel.HotelState
import com.trekkstay.hotel.feature.hotel.presentation.states.hotel.HotelViewModel
import com.trekkstay.hotel.feature.hotel.presentation.states.location.ViewProvinceAction
import com.trekkstay.hotel.feature.hotel.presentation.states.room.CreateRoomAction
import com.trekkstay.hotel.feature.hotel.presentation.states.room.RoomState
import com.trekkstay.hotel.feature.hotel.presentation.states.room.RoomViewModel
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily
import com.trekkstay.hotel.ui.theme.TrekkStayBlue

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CreateRoomScreen(hotelViewModel: HotelViewModel,roomViewModel: RoomViewModel,navController: NavHostController) {
    var hotelId by remember { mutableStateOf("") }
    var roomType by remember { mutableStateOf(TextFieldValue()) }
    var description by remember { mutableStateOf(TextFieldValue()) }
    var quantity by remember { mutableStateOf(TextFieldValue()) }
    var discountRate by remember { mutableStateOf(TextFieldValue()) }
    var price by remember { mutableStateOf(TextFieldValue()) }
    var view by remember { mutableStateOf(TextFieldValue()) }
    var roomSize by remember { mutableStateOf(TextFieldValue()) }
    var selectedImageUris by remember { mutableStateOf<List<Uri?>>(emptyList()) }
    var selectedBedNum by remember { mutableIntStateOf(0) }
    var selectedAdultNumber by remember { mutableIntStateOf(0) }
    var selectedChildNumber by remember { mutableIntStateOf(0) }
    val photosPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(), onResult = { selectedImageUris = it}
    )
    var selectedFacilities by remember { mutableStateOf(listOf<String>()) }
    val facilities = listOf(
    "Air Condition",
    "Bath Tub",
    "Shower",
    "Balcony",
    "Hair Dryer",
    "Kitchen",
    "Television",
    "Slippers",
    "Smoking")

    val roomState by roomViewModel.state.observeAsState()
    var showDialog by remember { mutableStateOf(true) }
    if (showDialog) {
        when (roomState) {
            is RoomState.SuccessCreateRoom -> {
                showDialog = true
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = { Text("Create room success") },
                    text = { Text("Create room successful") },
                    confirmButton = {},
                    dismissButton = {
                        Button(onClick = { showDialog = false }) {
                            Text("OK")
                        }
                    }
                )
            }
            is RoomState.InvalidCreateRoom -> {
                showDialog = true
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = { Text("Create room fail") },
                    text = { Text((roomState as RoomState.InvalidCreateRoom).message) },
                    confirmButton = {},
                    dismissButton = {
                        Button(onClick = { showDialog = false }) {
                            Text("OK")
                        }
                    }
                )
            }
            is RoomState.CreateRoomCalling -> {
                // You can show a progress dialog or a loading indicator here
            }
            else -> {
                // Handle other states
            }
        }
    }

    val hotelState by hotelViewModel.state.observeAsState()
    when (hotelState) {
        is HotelState.SuccessGetHotelId -> {
             hotelId =
                (hotelState as HotelState.SuccessGetHotelId).id
        }

        is HotelState.InvalidGetHotelId -> {
        }
        is HotelState.GetHotelIdCalling -> {
        }

        else -> {}
    }
    LaunchedEffect(Unit) {
        val action = GetHotelIdAction
        hotelViewModel.processAction(action)
    }
    

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxHeight().padding(bottom = 75.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(25.dp))
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    navController.navigate("hotel_room_manage")
                }) {
                    Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = null)
                }
                Text(
                    text = "Create hotel room",
                    fontSize = 20.sp,
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.SemiBold,
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            HorizontalDivider(color = Color(0xFFE4E4E4), thickness = 3.dp)
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.padding(horizontal = 25.dp, vertical = 20.dp)
            ) {
                InfoTextField(
                    label = "Room type",
                    text = roomType,
                    onValueChange = { roomType = it },
                    icon = ImageVector.vectorResource(R.drawable.bed_ico),
                )
                InfoTextField(
                    label = "Description",
                    text = description,
                    onValueChange = { description = it },
                    icon = Icons.Default.Info,
                )
                InfoTextField(
                    label = "Quantity",
                    text = quantity,
                    onValueChange = { quantity = it },
                    icon = ImageVector.vectorResource(R.drawable.box_ico),
                    type = "number"
                )
                InfoTextField(
                    label = "Discount Rate",
                    text = discountRate,
                    onValueChange = { discountRate = it },
                    icon = ImageVector.vectorResource(R.drawable.discount_ico),
                    type = "number"
                )
                InfoTextField(
                    label = "Original Price",
                    text = price,
                    onValueChange = { price = it },
                    icon = ImageVector.vectorResource(R.drawable.money_circle_ico),
                    type = "number"
                )
                HotelActionRow(
                    label = "Video",
                    leadingIcon = ImageVector.vectorResource(R.drawable.camera_ico),
                    clickHandler = {
                        //TODO
                    }
                )
                HotelActionRow(
                    label = "Image",
                    leadingIcon = ImageVector.vectorResource(R.drawable.photo_ico),
                    clickHandler = {
                        photosPickerLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                    }
                )
                if (selectedImageUris.isNotEmpty()) {
                    FlowRow(
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        horizontalArrangement = Arrangement.SpaceAround,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(5.dp))
                            .background(Color(0xFFD9D9D9))
                            .padding(vertical = 5.dp)

                    ) {
                        for (uri in selectedImageUris) {
                            AsyncImage(
                                model = uri,
                                contentDescription = null,
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier
                                    .size(160.dp, 100.dp)
                                    .clip(RoundedCornerShape(10.dp))
                            )
                        }
                    }
                }
                Column(
                    verticalArrangement = Arrangement.spacedBy(5.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFE4E4E4).copy(0.3f))
                        .border(1.dp, TrekkStayBlue, shape = RoundedCornerShape(16.dp))
                        .padding(20.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = null,
                            tint = TrekkStayBlue,
                            modifier = Modifier.padding(horizontal = 10.dp)
                        )
                        Text(
                            text = "Facility",
                            fontSize = 13.sp,
                            fontFamily = PoppinsFontFamily,
                            fontWeight = FontWeight.Medium,
                            color = Color.Black.copy(0.6f)
                        )
                    }
                    FlowRow(
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        horizontalArrangement = Arrangement.spacedBy(18.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp)
                    ) {
                        facilities.forEach { facility ->
                            FacilityChip(
                                label = facility,
                                selected = facility in selectedFacilities,
                                onSelectedChange = { isSelected ->
                                    selectedFacilities = if (isSelected) {
                                        selectedFacilities + facility
                                    } else {
                                        selectedFacilities - facility
                                    }
                                }
                            )
                        }
                    }
                    InfoTextField(
                        label = "View",
                        text = view,
                        onValueChange = { view = it },
                        icon = ImageVector.vectorResource(R.drawable.eye_ico),
                    )
                    InfoTextField(
                        label = "Room Size",
                        text = roomSize,
                        onValueChange = { roomSize = it },
                        icon = ImageVector.vectorResource(R.drawable.size_ico),
                        type = "number"
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    HotelRoomOptSelector(
                        onBedNumChange = { bedNum ->
                            selectedBedNum = bedNum
                        },
                        onAdultNumberChange = { adultNumber ->
                            selectedAdultNumber = adultNumber
                        },
                        onChildNumberChange = { childNumber ->
                            selectedChildNumber = childNumber
                        }
                    )
                }
            }
            Button(
                onClick = {
                    val action = CreateRoomAction(
                        hotelId = hotelId,
                        type = roomType.text,
                        description = description.text,
                        quantities = quantity.text.toIntOrNull() ?: 0,
                        discountRate = discountRate.text.toIntOrNull() ?: 0,
                        originalPrice =price.text.toIntOrNull() ?: 0,
                        images = emptyList(),
                        videos = emptyList(),
                        airConditioner = "Air Condition" in facilities,
                        bathTub =  "Bath Tub" in facilities,
                        shower =  "Shower" in facilities,
                        balcony = "Balcony" in facilities,
                        hairDryer = "Hair Dryer" in facilities,
                        kitchen = "Kitchen" in facilities,
                        television = "Television" in facilities,
                        slippers = "Slippers" in facilities,
                        nonSmoking = "Smoking" in facilities,
                        view = view.text,
                        roomSize = roomSize.text.toIntOrNull() ?: 0,
                        numberOfBed = selectedBedNum,
                        adults = selectedAdultNumber,
                        children = selectedChildNumber
                    )
                    roomViewModel.processAction(action)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = TrekkStayBlue,
                    contentColor = Color.White
                ),
                contentPadding = PaddingValues(horizontal = 100.dp, vertical = 15.dp),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(5.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = "Save Hotel Room",
                    fontSize = 18.sp,
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}