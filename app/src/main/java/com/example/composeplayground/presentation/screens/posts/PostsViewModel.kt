package com.example.composeplayground.presentation.screens.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.composeplayground.domain.entities.user.User
import com.example.composeplayground.domain.interactors.PostsInteractor
import com.example.composeplayground.domain.interactors.UserInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val postsInteractor: PostsInteractor,
    private val userInteractor: UserInteractor,
) : ViewModel() {

    val currentUser: LiveData<User?>
        get() = userInteractor.currentUser.asLiveData()
}