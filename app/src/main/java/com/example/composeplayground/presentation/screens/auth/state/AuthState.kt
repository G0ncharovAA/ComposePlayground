package com.example.composeplayground.presentation.screens.auth.state

import com.example.composeplayground.R

sealed class AuthState {

    abstract val stringId: Int

    object SignedOut : AuthState() {
        override val stringId = R.string.signed_out
    }

    object InProgress : AuthState() {
        override val stringId = R.string.loading
    }

    object UsersLoaded : AuthState() {
        override val stringId = R.string.users_loaded
    }

    object Error : AuthState() {
        override val stringId = R.string.error
    }

    object SignedIn : AuthState() {
        override val stringId = R.string.singed_in
    }
}