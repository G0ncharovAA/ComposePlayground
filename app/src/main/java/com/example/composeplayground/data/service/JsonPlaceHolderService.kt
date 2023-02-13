package com.example.composeplayground.data.service

import com.example.composeplayground.domain.entities.User
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import javax.inject.Inject

class JsonPlaceHolderService @Inject constructor(
    private val ktorClient: HttpClient
) {

    suspend fun getAllUsers(): List<User> =
        ktorClient.get("users").body()
}