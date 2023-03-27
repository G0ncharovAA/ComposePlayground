package com.example.composeplayground.presentation.screens.auth.state

import com.example.composeplayground.domain.entities.user.User

data class AuthScreenState(
    val authState: AuthState = AuthState.SignedOut,
    val dropDownExpanded: Boolean = false,
    val users: List<User> = emptyList(),
    val currentUser: User? = null,
)
