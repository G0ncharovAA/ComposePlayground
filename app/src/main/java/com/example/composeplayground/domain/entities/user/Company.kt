package com.example.composeplayground.domain.entities.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Company(
    @SerialName("name") val name: String,
    @SerialName("catchPhrase") val catchPhrase: String,
    @SerialName("bs") val bs: String,
)