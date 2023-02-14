package com.example.composeplayground.data.repository

import com.example.composeplayground.data.service.JsonPlaceHolderService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostsRepository @Inject constructor(
    private val service: JsonPlaceHolderService
) {

    suspend fun getPosts(userId: Int) = service.getPosts(userId)
}