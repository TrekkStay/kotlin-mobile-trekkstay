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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.hotel.R
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun QRScannerScreen(activity: ComponentActivity) {
    val textResult = remember { mutableStateOf("") }

    val barCodeLauncher = rememberLauncherForActivityResult(ScanContract()) { result ->
        if (result.contents == null) {
            Toast.makeText(activity, "Cancelled", Toast.LENGTH_SHORT).show()
        } else {
            textResult.value = result.contents
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

    Scaffold(
        bottomBar = {
            BottomAppBar(
                actions = {},
                floatingActionButton = {
                    FloatingActionButton(onClick = { checkCameraPermission() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.qr_scanner),
                            contentDescription = "QR Scan"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.qr_scanner),
                modifier = Modifier.size(100.dp),
                contentDescription = "QR"
            )
            Text(
                text = textResult.value,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
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
    options.setOrientationLocked(false)

    barCodeLauncher.launch(options)
}
