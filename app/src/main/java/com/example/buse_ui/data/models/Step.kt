package com.example.buse_ui.data.models

data class Step(
    val distance: DistanceX,
    val duration: DurationX,
    val end_location: EndLocationX,
    val html_instructions: String,
    val maneuver: String,
    val polyline: Polyline,
    val start_location: StartLocation,
    val travel_mode: String
)