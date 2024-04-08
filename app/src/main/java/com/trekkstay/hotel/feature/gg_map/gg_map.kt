package com.trekkstay.hotel.feature.gg_map

import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.view.ViewGroup
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.core.content.ContextCompat
import androidx.core.app.ActivityCompat

@Composable
fun GGMap(
    onMapClicked: (LatLng) -> Unit
) {
    var permissionGranted by remember { mutableStateOf(false) }
    val context = LocalContext.current

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            permissionGranted = true
        } else {
            // Permission denied, handle accordingly
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Button(onClick = {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }) {
            Text("Request Location Permission")
        }

        if (permissionGranted) {
            MapViewContainer(onMapClicked = onMapClicked, context = context)
        }
    }
}

@Composable
private fun MapViewContainer(
    onMapClicked: (LatLng) -> Unit,
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
                    googleMap.setOnMapClickListener { latLng ->
                        // When the map is clicked, add a marker
                        googleMap.clear() // Clear existing markers
                        googleMap.addMarker(MarkerOptions().position(latLng))

                        // Pass the clicked LatLng to the callback
                        onMapClicked(latLng)
                    }
                }
                onCreate(null)
                onResume()
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}

