package com.example.composeplayground.domain.interactors

import com.example.composeplayground.data.repository.AlbumsRepository
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

    suspend fun getAlbum(albumId: Int) =
        getAlbums().firstOrNull { it.id == albumId }

    suspend fun getPhotos(albumId: Int) = albumsRepository.getPhotos(albumId)

    suspend fun getAllPhotos() =
        getAlbums().flatMap {
            getPhotos(it.id)
        }
}