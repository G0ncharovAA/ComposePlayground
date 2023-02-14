package com.example.composeplayground.domain.entities

import android.location.Location
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Geo(
    @SerialName("lat") val lat: Double,
    @SerialName("lng") val lng: Double,
)

fun Geo.toLocation() =
    Location("geo").apply {
        latitude = lat
        longitude = lng
    }