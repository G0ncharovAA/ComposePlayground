package com.example.composeplayground.presentation.screens.photo

import androidx.lifecycle.*
import com.example.composeplayground.domain.entities.album.Photo
import com.example.composeplayground.domain.entities.user.User
import com.example.composeplayground.domain.interactors.AlbumsInteractor
import com.example.composeplayground.domain.interactors.UserInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val albumsInteractor: AlbumsInteractor,
    private val userInteractor: UserInteractor,
) : ViewModel() {

    private var _albumId = savedStateHandle.get<Int>("albumId") ?: 0
    private var _photoId = savedStateHandle.get<Int>("photoId") ?: 0

    init {
        viewModelScope.launch {
            val photos = albumsInteractor.getPhotos(_albumId)
            _photo.update {
                photos.firstOrNull {
                    it.id == _photoId
                }
            }
            _photos.update {
                photos
            }
        }
    }

    private val _photo = MutableStateFlow<Photo?>(null)
    val photo: LiveData<Photo?>
        get() = _photo.asLiveData()

    private val _photos = MutableStateFlow<List<Photo>>(emptyList())
    val photos: LiveData<List<Photo>>
        get() = _photos.asLiveData()

    val currentUser
        get() = userInteractor.currentUser

    fun onPhotoClick(photoId: Int) {
        _photo.update {
            _photos.value.firstOrNull {
                it.id == photoId
            }
        }
    }
}