package com.example.buse_ui.PresentationStudent

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.buse_ui.data.MapsViewModel
import com.example.buse_ui.data.models.MapsData
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.example.buse_ui.BuildConfig
import com.example.buse_ui.notif.notifApp
import com.example.buse_ui.notif.notifViewModel
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging
import com.google.maps.android.compose.ComposeMapColorScheme
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapActivity(mapsViewModel: MapsViewModel, notifViewModel: notifViewModel) {

    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.Hidden,
            skipHiddenState = false
        )
    )
    val viewMo = notifApp.notifViewModel
    Column(modifier = Modifier.fillMaxSize()) {
        BottomSheetScaffold(
            scaffoldState = scaffoldState,
            sheetContent = {
                Box(
                    modifier = Modifier
                        .height(500.dp)
                        .background(Color.Red)
                )
            },
            sheetPeekHeight = 80.dp,
            sheetShape = RoundedCornerShape(21.dp)

        ) {
            MapsLaunchData(mapsViewModel = mapsViewModel, notifViewModel = viewMo)
        }
    }
}

@Composable
fun MapsLaunchData(mapsViewModel: MapsViewModel, notifViewModel: notifViewModel) {
    val origin = "20.290264,85.821147"
    val destination = "20.250434,85.800024"

    mapsViewModel.fetchLocationQueryData(origin, destination, BuildConfig.maps_api)
    val mapsData by mapsViewModel.locationQueryData.collectAsState()


    if (mapsData != null) {
        mapsData?.let {
            Log.d("myTag", it.routes.toString())
            Maps(mapsData = it, notifViewModel = notifViewModel)
        }
    } else {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun Maps(mapsData: MapsData, notifViewModel: notifViewModel) {
    val polylinePoints = remember {
        decodePolyline(mapsData.routes[0].overview_polyline.points)
    }
    val launchAnimate by notifViewModel.launchAnimate.collectAsState()

    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {

    }

    val cameraPositionState = rememberCameraPositionState {
        val startLocation = mapsData.routes[0].legs[0].start_location
        val boundsBuilder = LatLngBounds.Builder()

        // Add all polyline points to the bounds builder
        polylinePoints.forEach { point ->
            boundsBuilder.include(point)
        }

        // Build the LatLngBounds
        val bounds = boundsBuilder.build()
        position = CameraPosition.fromLatLngZoom(
            bounds.center, 12f
        )
    }

    val markerPosition = remember { mutableStateOf(polylinePoints.first()) }
    val markerState = rememberMarkerState(position = markerPosition.value)

    LaunchedEffect(polylinePoints, launchAnimate) {
        if (polylinePoints.isNotEmpty()) {
            val boundsBuilder = LatLngBounds.Builder()
            polylinePoints.forEach { point ->
                boundsBuilder.include(point)
            }
            val bounds = boundsBuilder.build()
            cameraPositionState.animate(
                update = CameraUpdateFactory.newLatLngBounds(bounds, 100) // 100 is padding
            )

            scope.launch {
                val token = Firebase.messaging.token.await()
                Log.i("FCM", token)
            }
            if (launchAnimate) {
                while (polylinePoints.isNotEmpty()) {
                    // Move the marker to the next point
                    markerState.position = polylinePoints.first()

                    // Remove the point from the polyline list
                    if (polylinePoints.size > 1) {
                        polylinePoints.removeAt(0)
                    } else {
                        polylinePoints.clear()
                    }

                    // Wait for the next movement
                    delay(1000L)
                }
            }
        }
    }


    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        uiSettings = MapUiSettings(zoomControlsEnabled = false),
        properties = MapProperties(
            mapType = MapType.NORMAL,
            isBuildingEnabled = true,
            isTrafficEnabled = true
        ),
        mapColorScheme = ComposeMapColorScheme.FOLLOW_SYSTEM
    ) {
        Polyline(
            points = polylinePoints,
            color = Color.Blue,
            width = 9f
        )

        Marker(
            state = markerState,
            icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED),
            title = "Moving Marker"
        )
    }
}

suspend fun animateMarker(
    markerState: MarkerState,
    polylinePoints: MutableList<LatLng>, // Change List to MutableList
    interval: Long = 1000L
) {
    while (polylinePoints.isNotEmpty()) {
        // Move the marker to the next point
        markerState.position = polylinePoints.first()

        // Remove the point from the polyline list
        if (polylinePoints.size > 1) {
            polylinePoints.removeAt(0)
        } else {
            polylinePoints.clear()
        }

        // Wait for the next movement
        delay(interval)
    }
}

fun decodePolyline(encoded: String): MutableList<LatLng> {
    val poly = ArrayList<LatLng>()
    var index = 0
    val len = encoded.length
    var lat = 0
    var lng = 0

    while (index < len) {
        var b: Int
        var shift = 0
        var result = 0
        do {
            b = encoded[index++].code - 63
            result = result or (b and 0x1f shl shift)
            shift += 5
        } while (b >= 0x20)
        val dlat = if (result and 1 != 0) (result shr 1).inv() else result shr 1
        lat += dlat

        shift = 0
        result = 0
        do {
            b = encoded[index++].code - 63
            result = result or (b and 0x1f shl shift)
            shift += 5
        } while (b >= 0x20)
        val dlng = if (result and 1 != 0) (result shr 1).inv() else result shr 1
        lng += dlng

        val p = LatLng(lat.toDouble() / 1E5, lng.toDouble() / 1E5)
        poly.add(p)
    }

    return poly
}
