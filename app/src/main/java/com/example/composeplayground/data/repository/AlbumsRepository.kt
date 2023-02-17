package com.example.composeplayground.data.repository

import com.example.composeplayground.data.service.JsonPlaceHolderService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AlbumsRepository @Inject constructor(
    private val service: JsonPlaceHolderService
) {

    suspend fun getAlbums(userId: Int) = service.getAlbums(userId)

    suspend fun getPhotos(albumId: Int) = service.getPhotos(albumId)
}