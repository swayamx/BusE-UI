package com.example.buse_ui.data

import android.webkit.WebStorage.Origin
import com.example.buse_ui.data.models.MapsData
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow

interface MapsRepository {
    suspend fun locationQuery(
        origin: String,
        destination: String,
        key: String
    ): Flow<com.example.buse_ui.data.Response<MapsData>>
}