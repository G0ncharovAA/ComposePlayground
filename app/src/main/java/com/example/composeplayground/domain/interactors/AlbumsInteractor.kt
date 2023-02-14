package com.example.composeplayground.domain.interactors

import com.example.composeplayground.data.repository.AlbumsRepository
import com.example.composeplayground.data.repository.PostsRepository
import com.example.composeplayground.data.repository.ToDoRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AlbumsInteractor @Inject constructor(
    private val albumsRepository: AlbumsRepository,
    private val userInteractor: UserInteractor,
) {

    suspend fun getAlbums() =
        userInteractor.doWithCurrentUser {
            albumsRepository.getAlbums(it.id)
        }
}