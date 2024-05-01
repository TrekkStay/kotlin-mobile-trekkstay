package com.trekkstay.hotel.feature.hotel.presentation.activities

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.trekkstay.hotel.feature.hotel.domain.entities.MarkerInfo

@Composable
fun MapWithMarkers(
    markerList: List<MarkerInfo>,
    onBackPress: () -> Unit
) {
    val context = LocalContext.current

    val boundsBuilder = LatLngBounds.builder()
    markerList.forEach { boundsBuilder.include(it.position) }
    val bounds = boundsBuilder.build()
    fun createCustomMarker(context: Context, price: Double): Bitmap {
        val textView = TextView(context).apply {
            text = "$${String.format("%.2f", price)}"
            textSize = 14f
            setTextColor(Color.WHITE)
            val shape = GradientDrawable()
            shape.shape = GradientDrawable.RECTANGLE
            shape.setColor(Color.parseColor("#FF238C98"))
            shape.cornerRadius = 12f
            background = shape
            setPadding(16, 8, 16, 8)
        }
        textView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
        textView.layout(0, 0, textView.measuredWidth, textView.measuredHeight)

        val bitmap = Bitmap.createBitmap(
            textView.measuredWidth + 32,
            textView.measuredHeight + 16,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        textView.draw(canvas)

        return bitmap
    }
    Box(modifier = Modifier.fillMaxSize()) {
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = {_ ->
            val mapView = MapView(context).apply {
                getMapAsync { googleMap ->
                    googleMap.apply {
                        moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 50))
                        markerList.forEach { markerInfo ->
                            val markerOptions = MarkerOptions()
                                .position(markerInfo.position)
                                .title(markerInfo.name)
                            val customMarker = createCustomMarker(context, markerInfo.price)
                            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(customMarker))
                            addMarker(markerOptions)?.showInfoWindow()
                        }
                    }
                }
            }
            mapView.onCreate(null)
            mapView.onResume()

            mapView
        }
    )
        BackButton(onBackClicked =  onBackPress)
    }

}

@Composable
fun BackButton(onBackClicked: () -> Unit) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .padding(16.dp)
            .clickable { onBackClicked() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            tint = androidx.compose.ui.graphics.Color.Black
        )
    }
}