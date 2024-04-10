package com.trekkstay.hotel.feature.hotel.presentation.activities

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily
import com.trekkstay.hotel.ui.theme.TrekkStayBlue
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.hotel.R
import com.trekkstay.hotel.feature.hotel.domain.entities.Location
import com.trekkstay.hotel.feature.hotel.presentation.fragments.FacilityChip
import com.trekkstay.hotel.feature.hotel.presentation.fragments.HotelActionRow
import com.trekkstay.hotel.feature.hotel.presentation.fragments.InfoTextField
import com.trekkstay.hotel.feature.hotel.presentation.states.hotel.HotelState
import com.trekkstay.hotel.feature.hotel.presentation.states.hotel.HotelViewModel
import com.trekkstay.hotel.feature.hotel.presentation.states.location.LocationState
import com.trekkstay.hotel.feature.hotel.presentation.states.location.LocationViewModel
import com.trekkstay.hotel.feature.hotel.presentation.states.location.ViewProvinceAction
import com.trekkstay.hotel.feature.hotel.presentation.states.location.ViewDistrictAction
import com.trekkstay.hotel.feature.hotel.presentation.states.location.ViewWardAction

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CreateHotelScreen(hotelViewModel: HotelViewModel,locationViewModel: LocationViewModel, navController: NavHostController) {
    val hotelName by remember { mutableStateOf(TextFieldValue()) }
    val hotelEmail by remember { mutableStateOf(TextFieldValue()) }
    val hotelPhone by remember { mutableStateOf(TextFieldValue()) }
    val addressDetail by remember { mutableStateOf(TextFieldValue()) }
    val hotelDescription by remember { mutableStateOf(TextFieldValue()) }
    val timeList = arrayOf("12:00", "12:30", "13:00", "13:30", "14:00")

    var selectedProvince by remember { mutableStateOf<Location?>(null) }
    var selectedDistrict by remember { mutableStateOf<Location?>(null) }
    var selectedWard by remember { mutableStateOf<Location?>(null) }
    var provinceList : List<Location> = emptyList()
    var districtList : List<Location> = emptyList()
    var wardList : List<Location> = emptyList()
    var selectedImageUris by remember { mutableStateOf<List<Uri?>>(emptyList()) }
    val photosPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(),
        onResult = { selectedImageUris = it }
    )

    val hotelState by hotelViewModel.state.observeAsState()
    val locationState by locationViewModel.state.observeAsState()
    var showDialog by remember { mutableStateOf(true) }
    when (locationState) {
        is LocationState.SuccessViewProvince -> {
            provinceList = (locationState as LocationState.SuccessViewProvince).locationList.locationList
        }
        is LocationState.InvalidViewProvince -> {

        }
        is LocationState.ViewProvinceCalling -> {
        }
        is LocationState.SuccessViewDistrict -> {
            districtList = (locationState as LocationState.SuccessViewDistrict).locationList.locationList

        }
        is LocationState.InvalidViewDistrict -> {

        }
        is LocationState.ViewDistrictCalling -> {
        }
        is LocationState.SuccessViewWard -> {
            wardList = (locationState as LocationState.SuccessViewWard).locationList.locationList
        }
        is LocationState.InvalidViewWard -> {

        }
        is LocationState.ViewWardCalling -> {
        }
        else -> {
            // Handle other states
        }
    }

    if (showDialog) {
        when (hotelState) {
            is HotelState.SuccessCreateHotel -> {
                showDialog = true

            }
            is HotelState.InvalidCreateHotel -> {
                showDialog = true
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = { Text("Create hotel fail") },
                    text = { Text((hotelState as HotelState.InvalidCreateHotel).message) },
                    confirmButton = {},
                    dismissButton = {
                        Button(onClick = { showDialog = false }) {
                            Text("OK")
                        }
                    }
                )
            }
            is HotelState.CreateHotelCalling -> {
                // You can show a progress dialog or a loading indicator here
            }
            else -> {
                // Handle other states
            }
        }
    }

    LaunchedEffect(Unit) {
        val action = ViewProvinceAction
        locationViewModel.processAction(action)
    }

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxHeight()
            .padding(bottom = 75.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(25.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    navController.navigate("hotel_room_manage")
                }) {
                    Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = null)
                }
                Text(
                    text = "Create Your Hotel",
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
                    label = "Hotel Name",
                    text = hotelName,
                    icon = ImageVector.vectorResource(R.drawable.add_home_ico),
                )
                InfoTextField(
                    label = "Hotel Email",
                    text = hotelEmail,
                    icon = Icons.Default.Email,
                )
                InfoTextField(
                    label = "Hotel Phone",
                    text = hotelPhone,
                    icon = Icons.Default.Phone,
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TimeDropDownMenu(
                        widthSize = 170,
                        title = "Check In",
                        itemList = timeList,
                        leadingIcon = ImageVector.vectorResource(R.drawable.time_ico)
                    )
                    TimeDropDownMenu(
                        widthSize = 180,
                        title = "Check Out",
                        itemList = timeList,
                        leadingIcon = ImageVector.vectorResource(R.drawable.time_ico)
                    )
                }
                HotelActionRow(
                    label = "Hotel Location",
                    leadingIcon = Icons.Default.Place,
                    trailingIcon = ImageVector.vectorResource(R.drawable.map_ico),
                    clickHandler = {
                        //Open Google Maps
                    }
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    DropDownMenu(widthSize = 115, title = "Province", itemList = provinceList,{ selectedProvince = it
                        districtList = emptyList()
                        selectedDistrict = null
                        selectedWard = null
                        wardList = emptyList()
                        val action = ViewDistrictAction( it.code)
                        locationViewModel.processAction(action)
                    })
                    DropDownMenu(widthSize = 115, title = "District", itemList = districtList,{ selectedDistrict = it
                        selectedWard = null
                        wardList = emptyList()
                        val action = ViewWardAction( it.code)
                        locationViewModel.processAction(action)})
                    DropDownMenu(widthSize = 115, title = "Ward", itemList = wardList,{ selectedWard = it })
                }
                InfoTextField(
                    label = "Address Detail",
                    text = addressDetail,
                    icon = Icons.Default.Place,
                )
                HotelActionRow(
                    label = "Video",
                    leadingIcon = ImageVector.vectorResource(R.drawable.camera_ico),
                    clickHandler = {
                        //Upload Video
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
                InfoTextField(
                    label = "Description",
                    text = hotelDescription,
                    icon = Icons.Default.Info,
                    maxLine = 6
                )
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
                            text = "Facilities",
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
                        FacilityChip("Airport Transfer")
                        FacilityChip("Conference Room")
                        FacilityChip("Fitness Center")
                        FacilityChip("Food")
                        FacilityChip("Free Wifi")
                        FacilityChip("Laundry")
                        FacilityChip("Motorbike Rental")
                        FacilityChip("Parking Area")
                        FacilityChip("Spa")
                        FacilityChip("Pool")
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                }
            }
            Button(
                onClick = { /*TODO*/ },
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
                    text = "Save Hotel",
                    fontSize = 18.sp,
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeDropDownMenu(
    widthSize: Int,
    title: String,
    itemList: Array<String>,
    leadingIcon: ImageVector? = null
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(title) }

    ExposedDropdownMenuBox(
        modifier = Modifier.size(widthSize.dp, 50.dp),
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .size(widthSize.dp, 50.dp)
                .background(Color.Transparent, shape = RoundedCornerShape(12.dp))
                .border(1.dp, TrekkStayBlue, shape = RoundedCornerShape(12.dp))
                .padding(horizontal = 15.dp)
                .menuAnchor()
        ) {
            if (leadingIcon != null) {
                Icon(leadingIcon, contentDescription = null, tint = TrekkStayBlue)
            }
            Text(
                selectedText,
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                color = Color.Black.copy(0.6f)
            )
            Icon(
                Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                tint = TrekkStayBlue,
                modifier = Modifier.size(30.dp)
            )
        }
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            itemList.forEach { item ->
                DropdownMenuItem(
                    text = { Text(text = item, fontFamily = PoppinsFontFamily) },
                    onClick = {
                        selectedText = item
                        expanded = false
                    }
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownMenu(
    widthSize: Int,
    title: String,
    itemList: List<Location>,
    onItemSelected: (Location) -> Unit,
    leadingIcon: ImageVector? = null
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(title) }

    ExposedDropdownMenuBox(
        modifier = Modifier.size(widthSize.dp, 50.dp),
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .size(widthSize.dp, 50.dp)
                .background(Color.Transparent, shape = RoundedCornerShape(12.dp))
                .border(1.dp, TrekkStayBlue, shape = RoundedCornerShape(12.dp))
                .padding(horizontal = 15.dp)
                .menuAnchor()
        ) {
            if (leadingIcon != null) {
                Icon(leadingIcon, contentDescription = null, tint = TrekkStayBlue)
            }
            Text(
                selectedText,
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                color = Color.Black.copy(0.6f)
            )
            Icon(
                Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                tint = TrekkStayBlue,
                modifier = Modifier.size(30.dp)
            )
        }
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            itemList.forEach { item ->
                DropdownMenuItem(
                    text = { Text(text = item.nameVi, fontFamily = PoppinsFontFamily) },
                    onClick = {
                        selectedText = item.nameVi
                        expanded = false
                        onItemSelected(item)
                    }
                )
            }
        }
    }
}
