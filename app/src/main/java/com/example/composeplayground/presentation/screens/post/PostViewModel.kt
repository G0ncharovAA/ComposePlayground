package com.example.composeplayground.presentation.screens.post

import androidx.lifecycle.*
import com.example.composeplayground.domain.entities.post.Comment
import com.example.composeplayground.domain.entities.post.Post
import com.example.composeplayground.domain.entities.user.User
import com.example.composeplayground.domain.interactors.PostsInteractor
import com.example.composeplayground.domain.interactors.UserInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val postsInteractor: PostsInteractor,
    private val userInteractor: UserInteractor,
) : ViewModel() {

    private var _postId = savedStateHandle.get<Int>("postId") ?: 0

    init {
        viewModelScope.launch {
            _post.update {
                postsInteractor.getPost(_postId)
            }
            _comments.update {
                postsInteractor.getComments(_postId)
            }
        }
    }

    private val _post = MutableStateFlow<Post?>(null)
    val post: LiveData<Post?>
        get() = _post.asLiveData()

    private val _comments = MutableStateFlow<List<Comment>>(emptyList())
    val comments: LiveData<List<Comment>>
        get() = _comments.asLiveData()

    val currentUser: LiveData<User?>
        get() = userInteractor.currentUser.asLiveData()
}