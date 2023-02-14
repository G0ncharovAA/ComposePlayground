package com.example.composeplayground.domain.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("username") val userName: String,
    @SerialName("email") val email: String,
    @SerialName("address") val address: Address,
    @SerialName("phone") val phone: String,
    @SerialName("website") val website: String,
    @SerialName("company") val company: Company,
)
