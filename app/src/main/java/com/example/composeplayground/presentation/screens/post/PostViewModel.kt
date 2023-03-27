package com.example.composeplayground.presentation.screens.post

import androidx.lifecycle.*
import com.example.composeplayground.domain.interactors.PostsInteractor
import com.example.composeplayground.domain.interactors.UserInteractor
import com.example.composeplayground.presentation.screens.post.state.PostScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val postsInteractor: PostsInteractor,
    private val userInteractor: UserInteractor,
) : ViewModel() {

    private var _postId = savedStateHandle.get<Int>("postId") ?: 0

    private val _viewState = MutableStateFlow<PostScreenState>(PostScreenState())
    val viewState get() = _viewState.asStateFlow()

    init {
        viewModelScope.launch {
            _viewState.emit(
                _viewState.value.copy(
                    post = postsInteractor.getPost(_postId),
                    comments = postsInteractor.getComments(_postId)
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