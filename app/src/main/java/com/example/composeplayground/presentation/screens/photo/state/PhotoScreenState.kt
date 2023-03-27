package com.example.composeplayground.presentation.screens.photo.state

import com.example.composeplayground.domain.entities.album.Photo
import com.example.composeplayground.domain.entities.user.User

data class PhotoScreenState(
    val currentUser: User? = null,
    val photo: Photo? = null,
    val photos: List<Photo> = emptyList(),
)
