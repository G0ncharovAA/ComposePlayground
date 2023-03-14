package com.example.composeplayground.domain.entities.todo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ToDo(
    @SerialName("userId") val userId: Int,
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("completed") val completed: Boolean,
)
