package com.trekkstay.hotel.feature.gg_map

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
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily
import com.trekkstay.hotel.ui.theme.TrekkStayBlue

@Composable
fun GGMap(
    onMapClicked: (LatLng) -> Unit,
) {
    var permissionGranted by remember { mutableStateOf(false) }
    var selectedLatLng by remember { mutableStateOf(LatLng(10.762622	,106.660172)) }
    val context = LocalContext.current
    val fusedLocationClient: FusedLocationProviderClient = remember(context) {
        LocationServices.getFusedLocationProviderClient(context)
    }
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            permissionGranted = true
        } else {
            // Permission denied, handle accordingly
        }
    }
    LaunchedEffect(Unit) {
        requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    LaunchedEffect(permissionGranted) {
        if (permissionGranted) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                fusedLocationClient.lastLocation
                    .addOnSuccessListener { location ->
                        location?.let {
                            selectedLatLng = LatLng(location.latitude, location.longitude)
                        }
                    }
            }
        }
    }



    Column(modifier = Modifier.fillMaxSize()) {
        Button(onClick = {
            onMapClicked(selectedLatLng)
        },
            colors = ButtonDefaults.buttonColors(
                containerColor = TrekkStayBlue,
                contentColor = Color.White
            ),
            contentPadding = PaddingValues(horizontal = 50.dp, vertical = 15.dp),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(5.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text("Pick",
                fontSize = 16.sp,
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.SemiBold)

        }
        if (permissionGranted) {
            MapViewContainer(onMapClicked = {latLng ->  selectedLatLng= latLng},selectedLatLng, context = context)
        }



    }
}

@Composable
private fun MapViewContainer(
    onMapClicked: (LatLng) -> Unit,
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
                    if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        googleMap.isMyLocationEnabled = true
                    }
                    googleMap.setOnMapClickListener { latLng ->
                        googleMap.clear()
                        googleMap.addMarker(MarkerOptions().position(latLng))

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

