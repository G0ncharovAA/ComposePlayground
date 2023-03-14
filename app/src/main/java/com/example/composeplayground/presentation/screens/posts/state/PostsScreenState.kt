package com.example.composeplayground.presentation.screens.posts.state

import com.example.composeplayground.domain.entities.post.Post
import com.example.composeplayground.domain.entities.user.User

data class PostsScreenState(
    val currentUser: User? = null,
    val posts: List<Post> = emptyList(),
)
