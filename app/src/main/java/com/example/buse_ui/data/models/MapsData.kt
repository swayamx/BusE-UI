package com.example.buse_ui.data.models

data class MapsData(
    val geocoded_waypoints: List<GeocodedWaypoint>,
    val routes: List<Route>,
    val status: String
)