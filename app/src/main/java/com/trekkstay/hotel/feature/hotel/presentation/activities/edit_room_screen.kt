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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.hotel.R
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.PlayerView
import com.trekkstay.hotel.feature.hotel.presentation.fragments.FacilityChip
import com.trekkstay.hotel.feature.hotel.presentation.fragments.HotelActionRow
import com.trekkstay.hotel.feature.hotel.presentation.fragments.InfoTextField
import com.trekkstay.hotel.feature.hotel.presentation.fragments.OptionCounterRow
import com.trekkstay.hotel.feature.hotel.presentation.states.hotel.GetHotelIdAction
import com.trekkstay.hotel.feature.hotel.presentation.states.hotel.HotelState
import com.trekkstay.hotel.feature.hotel.presentation.states.hotel.HotelViewModel
import com.trekkstay.hotel.feature.hotel.presentation.states.media.MediaState
import com.trekkstay.hotel.feature.hotel.presentation.states.media.MediaViewModel
import com.trekkstay.hotel.feature.hotel.presentation.states.media.UploadMediaAction
import com.trekkstay.hotel.feature.hotel.presentation.states.media.UploadVideoAction
import com.trekkstay.hotel.feature.hotel.presentation.states.room.RoomDetailAction
import com.trekkstay.hotel.feature.hotel.presentation.states.room.RoomState
import com.trekkstay.hotel.feature.hotel.presentation.states.room.RoomViewModel
import com.trekkstay.hotel.feature.hotel.presentation.states.room.UpdateRoomAction
import com.trekkstay.hotel.feature.shared.AnimLoader
import com.trekkstay.hotel.feature.shared.TextDialog
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily
import com.trekkstay.hotel.ui.theme.TrekkStayBlue
import java.io.File
import java.io.FileOutputStream

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun EditRoomScreen(
    roomId: String,
    hotelViewModel: HotelViewModel,
    roomViewModel: RoomViewModel,
    mediaViewModel: MediaViewModel,
    navController: NavHostController
) {

    var loadingUpdate by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val contentResolver = context.contentResolver
    var hotelId by remember { mutableStateOf("") }
    var roomType by remember { mutableStateOf(TextFieldValue()) }
    var description by remember { mutableStateOf(TextFieldValue()) }
    var quantity by remember { mutableStateOf(TextFieldValue()) }
    var discountRate by remember { mutableStateOf(TextFieldValue()) }
    var price by remember { mutableStateOf(TextFieldValue()) }
    var view by remember { mutableStateOf(TextFieldValue()) }
    var roomSize by remember { mutableStateOf(TextFieldValue()) }
    var selectedBedNum by remember { mutableIntStateOf(1) }
    var selectedAdultNumber by remember { mutableIntStateOf(1) }
    var selectedChildNumber by remember { mutableIntStateOf(0) }
    var roomTypeString by remember { mutableStateOf("") }
    var descriptionString by remember { mutableStateOf("") }
    var quantityString by remember { mutableStateOf("") }
    var discountRateString by remember { mutableStateOf("") }
    var priceString by remember { mutableStateOf("") }
    var viewString by remember { mutableStateOf("") }
    var roomSizeString by remember { mutableStateOf("") }
    var selectedBedNumString by remember { mutableIntStateOf(-1) }
    var selectedAdultNumberString by remember { mutableIntStateOf(-1) }
    var selectedChildNumberString by remember { mutableIntStateOf(-1) }
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
        "Smoking"
    )
    var loadingCreate by remember { mutableStateOf(false) }
    fun getFileName(context: Context, uri: Uri): String? {
        var fileName: String? = null
        val projection = arrayOf(MediaStore.MediaColumns.DISPLAY_NAME)
        context.contentResolver.query(uri, projection, null, null, null)?.use { cursor ->
            if (cursor.moveToFirst()) {
                fileName =
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DISPLAY_NAME))
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

    var selectedImageUris by remember { mutableStateOf<List<Uri?>>(emptyList()) }
    var selectedVideoUris by remember { mutableStateOf<List<Uri?>>(emptyList()) }
    var selectedFile by remember { mutableStateOf<List<File>>(emptyList()) }
    var selectedExtension by remember { mutableStateOf<List<String>>(emptyList()) }
    var selectedVideoFile by remember { mutableStateOf<List<File>>(emptyList()) }
    var selectedVideoExtension by remember { mutableStateOf<List<String>>(emptyList()) }
    var selectedImageLinks by remember { mutableStateOf<List<String>>(emptyList()) }
    var selectedVideoLinks by remember { mutableStateOf<List<String>>(emptyList()) }
    var imageUrls by remember { mutableStateOf<List<String>>(emptyList()) }
    val photosPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia()
    ) { uris ->
        selectedImageUris = uris

        selectedFile = uris.map { uri ->
            queryFile(context, uri)
        }
        selectedExtension = uris.map { uri ->
            getFileExtension(uri, contentResolver)
        }

    }
    val videoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents()
    ) { uris ->
        selectedVideoUris = uris

        selectedVideoFile = uris.map { uri ->
            queryVideo(context, uri)
        }
        selectedVideoExtension = uris.map { uri ->
            getFileExtension(uri, contentResolver)
        }

    }

    fun parseUrlsToUris(urlList: List<String>): List<Uri> {
        val uriList = mutableListOf<Uri>()
        for (url in urlList) {
            val uri = Uri.parse(url)
            uriList.add(uri)
        }
        return uriList
    }

    fun checkForHttps(uris: List<Uri?>): Boolean {
        for (uri in uris) {
            if (uri.toString().startsWith("https://")) {
                return true
            }
        }
        return false
    }

    val roomState by roomViewModel.state.observeAsState()
    val mediaState by mediaViewModel.state.observeAsState()
    var showDialog by remember { mutableStateOf(true) }
    var hasUploadedVideo by remember { mutableStateOf(false) }


    when (mediaState) {
        is MediaState.SuccessUploadVideo -> {
            if (!hasUploadedVideo) {
                val action = UpdateRoomAction(
                    id = roomId,
                    hotelId = hotelId,
                    type = roomTypeString.ifEmpty { roomType.text },
                    description = descriptionString.ifEmpty { description.text },
                    quantities = quantityString.ifEmpty { quantity.text }.toIntOrNull() ?: 0,
                    discountRate = discountRateString.ifEmpty { discountRate.text }.toIntOrNull()
                        ?: 0,
                    originalPrice = priceString.ifEmpty { price.text }.toIntOrNull() ?: 0,
                    videos = (mediaState as MediaState.SuccessUploadVideo).media.media,
                    images = imageUrls,
                    airConditioner = "Air Condition" in selectedFacilities,
                    bathTub = "Bath Tub" in selectedFacilities,
                    shower = "Shower" in selectedFacilities,
                    balcony = "Balcony" in selectedFacilities,
                    hairDryer = "Hair Dryer" in selectedFacilities,
                    kitchen = "Kitchen" in selectedFacilities,
                    television = "Television" in selectedFacilities,
                    slippers = "Slippers" in selectedFacilities,
                    nonSmoking = "Smoking" in selectedFacilities,
                    view = viewString.ifEmpty { view.text },
                    roomSize = roomSizeString.ifEmpty { roomSize.text }.toIntOrNull() ?: 0,
                    numberOfBed = if (selectedBedNumString == -1) selectedBedNum else selectedBedNumString,
                    adults = if (selectedAdultNumberString == -1) selectedAdultNumber else selectedAdultNumberString,
                    children = if (selectedChildNumberString == -1) selectedChildNumber else selectedChildNumberString,
                )
                roomViewModel.processAction(action)
                hasUploadedVideo = true
            }
        }

        is MediaState.InvalidUploadVideo -> {

        }

        is MediaState.UploadVideoCalling -> {
        }

        is MediaState.SuccessUploadMedia -> {
            imageUrls = (mediaState as MediaState.SuccessUploadMedia).media.media
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

        else -> {

        }
    }
    if (showDialog) {
        when (roomState) {
            is RoomState.SuccessCreateRoom -> {
                loadingCreate = false
                showDialog = true
                TextDialog(
                    title = "Successfully Created",
                    msg = "A room has been added to your hotel successfully.",
                    type = "success"
                ) {
                    showDialog = false
                }
                navController.navigate("hotel_room_manage")
            }

            is RoomState.InvalidCreateRoom -> {
                loadingCreate = false
                showDialog = true
                TextDialog(
                    title = "Fail Creating Room",
                    msg = "Something went wrong!!! Please try again later",
                ) {
                    showDialog = false
                }
            }

            is RoomState.CreateRoomCalling -> {
                loadingCreate = true
            }

            else -> {
                // Handle other states
            }
        }
    }
    var showValidateDialog by remember { mutableStateOf(false) }
    var dialogTitle by remember { mutableStateOf("") }
    var dialogMessage by remember { mutableStateOf("") }

    if (showValidateDialog) {
        TextDialog(
            title = dialogTitle,
            msg = dialogMessage,
            onDismiss = { showValidateDialog = false },
        )
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
        val action1 = RoomDetailAction(roomId)
        roomViewModel.processAction(action1)
    }

    when (roomState) {
        is RoomState.SuccessRoomDetail -> {
            val room = (roomState as RoomState.SuccessRoomDetail).room
            roomType = TextFieldValue(room.type)
            description = TextFieldValue(room.description)
            quantity = TextFieldValue(room.quantity.toString())
            discountRate = TextFieldValue(room.discountRate.toString())
            price = TextFieldValue(room.originalPrice.toString())
            selectedImageUris = parseUrlsToUris(room.image.media)
            selectedVideoUris = parseUrlsToUris(room.video.media)
            selectedImageLinks = room.image.media
            selectedVideoLinks = room.video.media
            selectedFacilities = mutableListOf<String>().apply {
                facilities.forEachIndexed { index, facility ->
                    if (when (index) {
                            0 -> room.facilities.airConditioner
                            1 -> room.facilities.bathTub
                            2 -> room.facilities.shower
                            3 -> room.facilities.balcony
                            4 -> room.facilities.hairDryer
                            5 -> room.facilities.kitchen
                            6 -> room.facilities.television
                            7 -> room.facilities.slippers
                            8 -> room.facilities.nonSmoking
                            else -> false
                        }
                    ) {
                        add(facility)
                    }
                }
            }
            view = TextFieldValue(room.facilities.view)
            roomSize = TextFieldValue(room.facilities.roomSize.toString())
            selectedBedNum = room.facilities.numBed
            selectedAdultNumber = room.facilities.sleepRoom.adults
            selectedChildNumber = room.facilities.sleepRoom.children

            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(bottom = 75.dp)
            ) {
                Column(
                    modifier = Modifier.verticalScroll(rememberScrollState())
                ) {
                    Spacer(modifier = Modifier.height(25.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = {
                            navController.navigate("hotel_room_manage")
                        }) {
                            Icon(
                                Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                                contentDescription = null
                            )
                        }
                        Text(
                            text = "Edit hotel room",
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
                            onValueChange = {
                                roomType = it
                                roomTypeString = it.text
                            },
                            icon = ImageVector.vectorResource(R.drawable.bed_ico),
                        )
                        InfoTextField(
                            label = "Description",
                            text = description,
                            onValueChange = {
                                description = it
                                descriptionString = it.text
                            },
                            icon = Icons.Default.Info,
                        )
                        InfoTextField(
                            label = "Quantity",
                            text = quantity,
                            onValueChange = {
                                quantity = it
                                quantityString = it.text
                            },
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
                            onValueChange = {
                                price = it
                                priceString = it.text
                            },
                            icon = ImageVector.vectorResource(R.drawable.money_circle_ico),
                            type = "number"
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
                                onValueChange = {
                                    view = it
                                    viewString = it.text
                                },
                                icon = ImageVector.vectorResource(R.drawable.eye_ico),
                            )
                            InfoTextField(
                                label = "Room Size",
                                text = roomSize,
                                onValueChange = {
                                    roomSize = it
                                    roomSizeString = it.text
                                },
                                icon = ImageVector.vectorResource(R.drawable.size_ico),
                                type = "number"
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            UpdateHotelRoomOptSelector(
                                initialBedNum = selectedBedNum,
                                initialAdultNumber = selectedAdultNumber,
                                initialChildNumber = selectedChildNumber,
                                onBedNumChange = { bedNum ->
                                    selectedBedNum = bedNum
                                    selectedBedNumString = bedNum
                                },
                                onAdultNumberChange = { adultNumber ->
                                    selectedAdultNumber = adultNumber
                                    selectedAdultNumberString = adultNumber
                                },
                                onChildNumberChange = { childNumber ->
                                    selectedChildNumber = childNumber
                                    selectedChildNumberString = childNumber
                                }
                            )
                        }
                    }
                    Button(
                        onClick = {
                            if (roomType.text.isEmpty() ||
                                description.text.isEmpty() ||
                                quantity.text.isEmpty() ||
                                discountRate.text.isEmpty() ||
                                price.text.isEmpty() ||
                                view.text.isEmpty() ||
                                roomSize.text.isEmpty() ||
                                selectedImageUris.isEmpty() ||
                                selectedVideoUris.isEmpty() ||
                                selectedFacilities.isEmpty()
                            ) {
                                dialogTitle = "Empty Fields"
                                dialogMessage =
                                    "Please input all the required information before creating your hotel"
                                showValidateDialog = true
                            } else {
                                if (checkForHttps(selectedImageUris) && checkForHttps(
                                        selectedVideoUris
                                    )
                                ) {
                                    val action = UpdateRoomAction(
                                        id = roomId,
                                        hotelId = hotelId,
                                        type = roomTypeString.ifEmpty { roomType.text },
                                        description = descriptionString.ifEmpty { description.text },
                                        quantities = quantityString.ifEmpty { quantity.text }
                                            .toIntOrNull() ?: 0,
                                        discountRate = discountRateString.ifEmpty { discountRate.text }
                                            .toIntOrNull() ?: 0,
                                        originalPrice = priceString.ifEmpty { price.text }
                                            .toIntOrNull() ?: 0,
                                        images = selectedImageLinks,
                                        videos = selectedVideoLinks,
                                        airConditioner = "Air Condition" in selectedFacilities,
                                        bathTub = "Bath Tub" in selectedFacilities,
                                        shower = "Shower" in selectedFacilities,
                                        balcony = "Balcony" in selectedFacilities,
                                        hairDryer = "Hair Dryer" in selectedFacilities,
                                        kitchen = "Kitchen" in selectedFacilities,
                                        television = "Television" in selectedFacilities,
                                        slippers = "Slippers" in selectedFacilities,
                                        nonSmoking = "Smoking" in selectedFacilities,
                                        view = viewString.ifEmpty { view.text },
                                        roomSize = roomSizeString.ifEmpty { roomSize.text }
                                            .toIntOrNull() ?: 0,
                                        numberOfBed = if (selectedBedNumString == -1) selectedBedNum else selectedBedNumString,
                                        adults = if (selectedAdultNumberString == -1) selectedAdultNumber else selectedAdultNumberString,
                                        children = if (selectedChildNumberString == -1) selectedChildNumber else selectedChildNumberString,
                                    )
                                    roomViewModel.processAction(action)
                                    hasUploadedVideo = true
                                } else {
                                    hasUploadedVideo = false
                                    loadingCreate = true
                                    showDialog = true
                                    val mediaAction = UploadMediaAction(
                                        selectedFile,
                                        selectedExtension,
                                    )
                                    mediaViewModel.processAction(mediaAction)
                                }
                            }
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
                if (loadingCreate) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black.copy(0.3f))
                    ) {
                        AnimLoader(
                            rawRes = R.raw.loading_anim,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        }

        is RoomState.InvalidRoomDetail -> {

        }

        is RoomState.RoomDetailCalling -> {
        }

        else -> {
            // Handle other states
        }
    }

    when (roomState) {
        is RoomState.SuccessUpdateRoom -> {
            loadingUpdate = false
            showDialog = true
            TextDialog(
                title = "Successfully Updated",
                msg = "Updated room successful",
                type = "success"
            ) {
                showDialog = false
            }
            navController.navigate("hotel_room_manage") {
                launchSingleTop = true
            }
        }

        is RoomState.InvalidUpdateRoom -> {
            showDialog = true
            TextDialog(
                title = "Updated Failed",
                msg = "Something went wrong!!! Please try again later",
            ) {
                showDialog = false
            }
        }

        is RoomState.UpdateRoomCalling -> {
            // You can show a progress dialog or a loading indicator here
        }

        else -> {
            // Handle other states
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateHotelRoomOptSelector(

    initialBedNum: Int,
    initialAdultNumber: Int,
    initialChildNumber: Int,
    onBedNumChange: (Int) -> Unit,
    onAdultNumberChange: (Int) -> Unit,
    onChildNumberChange: (Int) -> Unit
) {
    var isBotSheetVisible by remember { mutableStateOf(false) }
    var bedNum by remember { mutableIntStateOf(initialBedNum) }
    var adultNumber by remember { mutableIntStateOf(initialAdultNumber) }
    var childNumber by remember { mutableIntStateOf(initialChildNumber) }
    val botSheetState = rememberModalBottomSheetState()

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .background(Color.Transparent)
            .border(1.dp, TrekkStayBlue, shape = RoundedCornerShape(12.dp))
            .padding(horizontal = 10.dp)
            .clickable {
                isBotSheetVisible = true
            }
    ) {
        Icon(
            ImageVector.vectorResource(R.drawable.facility_ico),
            contentDescription = null,
            tint = TrekkStayBlue,
            modifier = Modifier
                .size(30.dp)
        )
        Text(
            text = "$bedNum Beds, $adultNumber Adults, $childNumber Children",
            fontFamily = PoppinsFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 13.sp,
            color = Color.Black.copy(0.6f)
        )
        if (isBotSheetVisible) {
            ModalBottomSheet(
                onDismissRequest = {
                    isBotSheetVisible = false
                },
                sheetState = botSheetState
            ) {
                OptionCounterRow(
                    label = "Bed",
                    count = bedNum,
                    onIncrease = { bedNum += 1; onBedNumChange(bedNum) },
                    onDecrease = {
                        if (bedNum > 1) {
                            bedNum -= 1; onBedNumChange(bedNum)
                        }
                    })
                OptionCounterRow(
                    label = "Adults",
                    count = adultNumber,
                    onIncrease = { adultNumber += 1; onAdultNumberChange(adultNumber) },
                    onDecrease = {
                        if (adultNumber > 1) {
                            adultNumber -= 1; onAdultNumberChange(adultNumber)
                        }
                    })
                OptionCounterRow(
                    label = "Children",
                    count = childNumber,
                    onIncrease = { childNumber += 1;onChildNumberChange(childNumber) },
                    onDecrease = {
                        if (childNumber > 0) {
                            childNumber -= 1; onChildNumberChange(childNumber)
                        }
                    })
                Spacer(modifier = Modifier.size(30.dp))
            }
        }
    }

}

