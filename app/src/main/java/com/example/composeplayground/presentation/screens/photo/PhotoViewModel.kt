package com.example.composeplayground.presentation.screens.photo

import androidx.lifecycle.*
import com.example.composeplayground.domain.interactors.AlbumsInteractor
import com.example.composeplayground.domain.interactors.UserInteractor
import com.example.composeplayground.presentation.screens.photo.state.PhotoScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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

    private val _viewState = MutableStateFlow<PhotoScreenState>(PhotoScreenState())
    val viewState get() = _viewState.asStateFlow()

    init {
        viewModelScope.launch {
            val photos = albumsInteractor.getPhotos(_albumId)
            _viewState.emit(
                _viewState.value.copy(
                    photo = photos.firstOrNull {
                        it.id == _photoId
                    },
                    photos = photos
                )
            )
            // To make possible future extension, not using map operator
            userInteractor.currentUser.collect {
                _viewState.emit(
                    _viewState.value.copy(currentUser = it)
                )
            }
        }
    }

    fun onPhotoClick(photoId: Int) {
        viewModelScope.launch {
            val currentState = _viewState.value
            _viewState.emit(
                currentState.copy(
                    photo = currentState.photos.firstOrNull {
                        it.id == photoId
                    }
                )
            )
        }
    }
}