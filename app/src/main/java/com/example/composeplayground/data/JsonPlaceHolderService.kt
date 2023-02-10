package com.example.composeplayground.data

import io.ktor.client.*
import javax.inject.Inject

class JsonPlaceHolderService @Inject constructor(
    private val ktorClient: HttpClient
) {


}