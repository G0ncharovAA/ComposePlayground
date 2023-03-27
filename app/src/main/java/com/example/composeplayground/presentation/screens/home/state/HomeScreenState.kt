package com.example.composeplayground.presentation.screens.home.state

import com.example.composeplayground.domain.entities.user.User

data class HomeScreenState(
    val currentUser: User? = null
)
