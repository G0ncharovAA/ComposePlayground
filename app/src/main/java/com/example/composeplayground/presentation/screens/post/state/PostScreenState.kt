package com.example.composeplayground.presentation.screens.post.state

import com.example.composeplayground.domain.entities.post.Comment
import com.example.composeplayground.domain.entities.post.Post
import com.example.composeplayground.domain.entities.user.User

data class PostScreenState(
    val currentUser: User? = null,
    val post: Post? = null,
    val comments: List<Comment> = emptyList(),
)
