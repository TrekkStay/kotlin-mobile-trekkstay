package com.trekkstay.hotel.feature.hotel.presentation.activities

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.view.ViewGroup
import android.webkit.MimeTypeMap
import android.widget.FrameLayout
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
import androidx.compose.material3.MenuItemColors
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.hotel.R
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.PlayerView
import java.io.File
import com.google.android.gms.maps.model.LatLng
import com.trekkstay.hotel.feature.gg_map.GGMap
import com.trekkstay.hotel.feature.hotel.domain.entities.Location
import com.trekkstay.hotel.feature.hotel.presentation.fragments.FacilityChip
import com.trekkstay.hotel.feature.hotel.presentation.fragments.HotelActionRow
import com.trekkstay.hotel.feature.hotel.presentation.fragments.InfoTextField
import com.trekkstay.hotel.feature.hotel.presentation.states.hotel.CreateHotelAction
import com.trekkstay.hotel.feature.hotel.presentation.states.hotel.HotelState
import com.trekkstay.hotel.feature.hotel.presentation.states.hotel.HotelViewModel
import com.trekkstay.hotel.feature.hotel.presentation.states.location.LocationState
import com.trekkstay.hotel.feature.hotel.presentation.states.location.LocationViewModel
import com.trekkstay.hotel.feature.hotel.presentation.states.location.ViewProvinceAction
import com.trekkstay.hotel.feature.hotel.presentation.states.location.ViewDistrictAction
import com.trekkstay.hotel.feature.hotel.presentation.states.location.ViewWardAction
import com.trekkstay.hotel.feature.hotel.presentation.states.media.MediaState
import com.trekkstay.hotel.feature.hotel.presentation.states.media.MediaViewModel
import com.trekkstay.hotel.feature.hotel.presentation.states.media.UploadMediaAction
import com.trekkstay.hotel.feature.hotel.presentation.states.media.UploadVideoAction
import java.io.FileOutputStream

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CreateHotelScreen(hotelViewModel: HotelViewModel,locationViewModel: LocationViewModel, mediaViewModel: MediaViewModel,navController: NavHostController) {
    val context = LocalContext.current
    val contentResolver = context.contentResolver
    var hotelName by remember { mutableStateOf(TextFieldValue()) }
    var hotelEmail by remember { mutableStateOf(TextFieldValue()) }
    var hotelPhone by remember { mutableStateOf(TextFieldValue()) }
    var addressDetail by remember { mutableStateOf(TextFieldValue()) }
    var hotelDescription by remember { mutableStateOf(TextFieldValue()) }
    var checkInTime by remember { mutableStateOf("") }
    var checkOutTime by remember { mutableStateOf("") }
    var selectedLatLng by remember { mutableStateOf(LatLng(0.0,0.0)) }
    val timeList = arrayOf("12:00", "12:30", "13:00", "13:30", "14:00")
    var selectedFacilities by remember { mutableStateOf(listOf<String>()) }
    val facilities = listOf("Airport Transfer",
    "Conference Room",
    "Fitness Center",
    "Food",
    "Free Wifi",
    "Laundry",
    "Motorbike Rental",
    "Parking Area",
    "Spa",
    "Pool")

     fun getFileName(context: Context, uri: Uri): String? {
        var fileName: String? = null
        val projection = arrayOf(MediaStore.MediaColumns.DISPLAY_NAME)
        context.contentResolver.query(uri, projection, null, null, null)?.use { cursor ->
            if (cursor.moveToFirst()) {
                fileName = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DISPLAY_NAME))
            }
        }
        return fileName
    }
    fun queryFile(context: Context, uri: Uri): File {
        val fileName = getFileName(context, uri) ?: "image_file"
        val inputStream = context.contentResolver.openInputStream(uri)
        val outputFile = File(context.cacheDir, fileName)
        inputStream?.use { input ->
            FileOutputStream(outputFile).use { output ->
                input.copyTo(output)
            }
        }
        return outputFile
    }
    fun queryVideo(context: Context, uri: Uri): File {
        val fileName = getFileName(context, uri) ?: "video_file"
        val inputStream = context.contentResolver.openInputStream(uri)
        val outputFile = File(context.cacheDir, fileName)
        inputStream?.use { input ->
            FileOutputStream(outputFile).use { output ->
                input.copyTo(output)
            }
        }
        return outputFile
    }
    fun getFileExtension(uri: Uri, contentResolver: ContentResolver): String {
        val mimeTypeMap = MimeTypeMap.getSingleton()
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ?: ""
    }



    var selectedProvince by remember { mutableStateOf<Location?>(null) }
    var selectedDistrict by remember { mutableStateOf<Location?>(null) }
    var selectedWard by remember { mutableStateOf<Location?>(null) }
    var provinceList : List<Location> = emptyList()
    var districtList : List<Location> = emptyList()
    var wardList : List<Location> = emptyList()
    var selectedImageUris by remember { mutableStateOf<List<Uri?>>(emptyList()) }
    var selectedVideoUris by remember { mutableStateOf<List<Uri?>>(emptyList()) }
    var selectedFile by remember{ mutableStateOf<List<File>>(emptyList())}
    var selectedExtension by remember{ mutableStateOf<List<String>>(emptyList())}
    var selectedVideoFile by remember{ mutableStateOf<List<File>>(emptyList())}
    var selectedVideoExtension by remember{ mutableStateOf<List<String>>(emptyList())}
    var imageUrls by remember{ mutableStateOf<List<String>>(emptyList())}
    val photosPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia()
    ) { uris ->
        selectedImageUris = uris

         selectedFile = uris.map { uri ->
            queryFile(context,uri)
        }
         selectedExtension = uris.map{
                uri ->
            getFileExtension(uri, contentResolver)
        }

    }
    val videoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents()
    ) { uris ->
        selectedVideoUris = uris

        selectedVideoFile = uris.map { uri ->
            queryVideo(context,uri)
        }
        selectedVideoExtension = uris.map{
                uri ->
            getFileExtension(uri, contentResolver)
        }

    }


    val hotelState by hotelViewModel.state.observeAsState()
    val locationState by locationViewModel.state.observeAsState()
    val mediaState by mediaViewModel.state.observeAsState()
    var showDialog by remember { mutableStateOf(true) }
    var showMap by remember {mutableStateOf(false)}
    var hasUploadedVideo by remember { mutableStateOf(false) }

    when (mediaState){
        is MediaState.SuccessUploadVideo -> {
            if(!hasUploadedVideo) {
                println("ok")
                val action = CreateHotelAction(
                    name = hotelName.text,
                    description = hotelDescription.text,
                    airportTransfer = "Airport Transfer" in selectedFacilities,
                    conferenceRoom = "Conference Room" in selectedFacilities,
                    fitnessCenter = "Fitness Center" in selectedFacilities,
                    foodService = "Food" in selectedFacilities,
                    freeWifi = "Free Wifi" in selectedFacilities,
                    laundryService = "Laundry" in selectedFacilities,
                    motorBikeRental = "Motorbike Rental" in selectedFacilities,
                    parkingArea = "Parking Area" in selectedFacilities,
                    spaService = "Spa" in selectedFacilities,
                    swimmingPool = " Pool" in selectedFacilities,
                    addressDetail = addressDetail.text,
                    checkInTime = checkInTime,
                    checkOutTime = checkOutTime,
                    provinceCode = selectedProvince?.code ?: "",
                    districtCode = selectedDistrict?.code ?: "",
                    wardCode = selectedWard?.code ?: "",
                    email = hotelEmail.text,
                    phone = hotelPhone.text,
                    videos = (mediaState as MediaState.SuccessUploadVideo).media.media,
                    images = imageUrls,
                    coordinates = selectedLatLng,
                )

                hotelViewModel.processAction(action)
                hasUploadedVideo=true
            }
        }
        is MediaState.InvalidUploadVideo -> {

        }
        is MediaState.UploadVideoCalling -> {
        }
        is MediaState.SuccessUploadMedia -> {
            imageUrls =  (mediaState as MediaState.SuccessUploadMedia).media.media
            val mediaAction = UploadVideoAction(
                selectedVideoFile,
                selectedVideoExtension,
            )
            mediaViewModel.processAction(mediaAction)

        }
        is MediaState.InvalidUploadMedia -> {

        }
        is MediaState.UploadMediaCalling -> {
        }
        else ->{

        }
    }
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
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = { Text("Create hotel success") },
                    text = { Text("Create hotel $hotelName successful") },
                    confirmButton = {},
                    dismissButton = {
                        Button(onClick = { showDialog = false }) {
                            Text("OK")
                        }
                    }
                )
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
        if(showMap) {
            GGMap(onMapClicked = {latLng ->
                selectedLatLng= latLng
                showMap =false
            },)
        }else{
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
                        navController.navigate("hotel_profile")
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
                        onValueChange = { hotelName = it },
                        icon = ImageVector.vectorResource(R.drawable.add_home_ico),
                    )
                    InfoTextField(
                        label = "Hotel Email",
                        text = hotelEmail,
                        onValueChange = { hotelEmail = it },
                        icon = Icons.Default.Email,
                    )
                    InfoTextField(
                        label = "Hotel Phone",
                        text = hotelPhone,
                        onValueChange = { hotelPhone = it },
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
                            leadingIcon = ImageVector.vectorResource(R.drawable.time_ico),
                            onItemSelected = { checkInTime = it }
                        )
                        TimeDropDownMenu(
                            widthSize = 180,
                            title = "Check Out",
                            itemList = timeList,
                            leadingIcon = ImageVector.vectorResource(R.drawable.time_ico),
                            onItemSelected = { checkOutTime = it }
                        )
                    }
                    HotelActionRow(
                        label = if (selectedLatLng == LatLng(0.0, 0.0)) "Hotel Location" else "${selectedLatLng.latitude}:${selectedLatLng.longitude}",
                        leadingIcon = Icons.Default.Place,
                        trailingIcon = ImageVector.vectorResource(R.drawable.map_ico),
                        clickHandler = {
                            showMap = true
                        }
                    )
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        DropDownMenu(widthSize = 115, title = "Province", itemList = provinceList, {
                            selectedProvince = it
                            districtList = emptyList()
                            selectedDistrict = null
                            selectedWard = null
                            wardList = emptyList()
                            val action = ViewDistrictAction(it.code)
                            locationViewModel.processAction(action)
                        })
                        DropDownMenu(widthSize = 115, title = "District", itemList = districtList, {
                            selectedDistrict = it
                            selectedWard = null
                            wardList = emptyList()
                            val action = ViewWardAction( it.code)
                            locationViewModel.processAction(action)
                        })
                        DropDownMenu(
                            widthSize = 115,
                            title = "Ward",
                            itemList = wardList,
                            { selectedWard = it })
                    }
                    InfoTextField(
                        label = "Address Detail",
                        text = addressDetail,
                        onValueChange = { addressDetail = it },
                        icon = Icons.Default.Place,
                    )
                    HotelActionRow(
                        label = "Video",
                        leadingIcon = ImageVector.vectorResource(R.drawable.camera_ico),
                        clickHandler = {
                            videoPickerLauncher.launch("video/*")
                        }
                    )
                    if (selectedVideoUris.isNotEmpty()) {
                        FlowRow(
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                            horizontalArrangement = Arrangement.SpaceAround,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(5.dp))
                                .background(Color(0xFFD9D9D9))
                                .padding(vertical = 5.dp)
                        ) {
                            for (uri in selectedVideoUris) {
                                AndroidView(
                                    factory = {
                                        PlayerView(context).apply {
                                            player = ExoPlayer.Builder(context).build().apply {
                                                uri?.let { it1 -> MediaItem.fromUri(it1) }
                                                    ?.let { it2 -> setMediaItem(it2) }
                                                repeatMode = ExoPlayer.REPEAT_MODE_ALL
                                                playWhenReady = playWhenReady
                                                prepare()
                                                play()
                                            }
                                            useController = true
                                            FrameLayout.LayoutParams(
                                                ViewGroup.LayoutParams.MATCH_PARENT,
                                                ViewGroup.LayoutParams.MATCH_PARENT
                                            )
                                        }


                                    },
                                    modifier = Modifier
                                        .size(320.dp, 200.dp)
                                        .clip(RoundedCornerShape(10.dp))
                                )
                            }
                        }
                    }
                    HotelActionRow(
                        label = "Image",
                        leadingIcon = ImageVector.vectorResource(R.drawable.photo_ico),
                        clickHandler = {
                            photosPickerLauncher.launch(
                                PickVisualMediaRequest(
                                    ActivityResultContracts.PickVisualMedia.ImageOnly
                                )
                            )
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
                        onValueChange = { hotelDescription = it },
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
                        Spacer(modifier = Modifier.height(5.dp))
                    }
                }
                Button(
                    onClick = {
                        showDialog = true
                        hasUploadedVideo=false
                        val mediaAction = UploadMediaAction(
                            selectedFile,
                            selectedExtension,
                        )
                        mediaViewModel.processAction(mediaAction)
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
                        text = "Save Hotel",
                        fontSize = 18.sp,
                        fontFamily = PoppinsFontFamily,
                        fontWeight = FontWeight.SemiBold
                    )
                }
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
    onItemSelected: (String) -> Unit,
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
                        onItemSelected(item)
                    }
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DropDownMenu(
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
            onDismissRequest = { expanded = false },
            modifier = Modifier.width(180.dp).background(Color.White)
        ) {
            itemList.forEach { item ->
                DropdownMenuItem(
                    text = { Text(text = item.nameVi, fontFamily = PoppinsFontFamily) },
                    colors = MenuItemColors(
                        textColor = TrekkStayBlue,
                        leadingIconColor = TrekkStayBlue,
                        trailingIconColor = TrekkStayBlue,
                        disabledTextColor = TrekkStayBlue,
                        disabledLeadingIconColor = TrekkStayBlue,
                        disabledTrailingIconColor = TrekkStayBlue
                    ),
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
