package com.trekkstay.hotel.feature.qr_scanner

import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.hotel.R
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun QRScannerScreen(navController: NavController,activity: ComponentActivity) {
    val textResult = remember { mutableStateOf("") }

    val barCodeLauncher = rememberLauncherForActivityResult(ScanContract()) { result ->
        if (result.contents == null) {
            Toast.makeText(activity, "Cancelled", Toast.LENGTH_SHORT).show()
        } else {
            textResult.value = result.contents
            navController.navigate("hotel_reservation_detail/${textResult.value}")
        }
    }

    val requestPermissionLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            showCamera( barCodeLauncher)
        } else {
            Toast.makeText(activity, "Camera permission is required", Toast.LENGTH_SHORT).show()
        }
    }

    val checkCameraPermission = {
        if (ContextCompat.checkSelfPermission(
                activity,
                android.Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            showCamera(barCodeLauncher)
        } else {
            requestPermissionLauncher.launch(android.Manifest.permission.CAMERA)
        }
    }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.qr_scanner),
                modifier = Modifier
                    .size(100.dp)
                    .clickable { checkCameraPermission() },
                contentDescription = "QR"
            )

        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
private fun showCamera( barCodeLauncher: ManagedActivityResultLauncher<ScanOptions, ScanIntentResult>) {
    val options = ScanOptions()
    options.setDesiredBarcodeFormats(ScanOptions.QR_CODE)
    options.setPrompt("Scan a QR code")
    options.setCameraId(0)
    options.setBeepEnabled(false)
    options.setOrientationLocked(true)


    barCodeLauncher.launch(options)
}
