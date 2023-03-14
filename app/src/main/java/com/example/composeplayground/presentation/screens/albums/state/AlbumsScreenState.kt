package com.example.composeplayground.presentation.screens.albums.state

import com.example.composeplayground.domain.entities.album.Album
import com.example.composeplayground.domain.entities.album.Photo
import com.example.composeplayground.domain.entities.user.User

data class AlbumsScreenState(
    val currentUser: User? = null,
    val albums: List<Photo> = emptyList(),
)
