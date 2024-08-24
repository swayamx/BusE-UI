package com.example.buse_ui.data

import com.example.buse_ui.data.models.MapsData
import com.google.android.gms.maps.model.LatLng
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("json")
    suspend fun locationQuery(
        @Query("origin") origin: String,
        @Query("destination") destination: String,
        @Query("key") key: String,
    ): MapsData

    companion object {
        const val BASE_URL = "https://maps.googleapis.com/maps/api/directions/"
    }

}
