package com.example.composeplayground.presentation.auth

sealed class AuthState {

    object SignedOut : AuthState()
    object InProgress : AuthState()
    object Error : AuthState()
    object SignIn : AuthState()
}