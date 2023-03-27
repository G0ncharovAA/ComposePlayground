package com.example.composeplayground.presentation.screens.albums

import androidx.lifecycle.*
import com.example.composeplayground.domain.interactors.AlbumsInteractor
import com.example.composeplayground.domain.interactors.UserInteractor
import com.example.composeplayground.presentation.screens.albums.state.AlbumsScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumsViewModel @Inject constructor(
    private val albumsInteractor: AlbumsInteractor,
    private val userInteractor: UserInteractor,
) : ViewModel() {

    private val _viewState = MutableStateFlow<AlbumsScreenState>(AlbumsScreenState())
    val viewState get() = _viewState.asStateFlow()

    init {
        viewModelScope.launch {
            _viewState.emit(
                _viewState.value.copy(
                    albums = albumsInteractor.getAllPhotos()
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
}