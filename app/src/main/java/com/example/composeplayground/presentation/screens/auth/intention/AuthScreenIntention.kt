package com.example.composeplayground.presentation.screens.auth.intention

import com.example.composeplayground.domain.entities.user.User

sealed class AuthScreenIntention {

    data class DropDownExpandedChange(val expanded: Boolean) : AuthScreenIntention()
    data class UserSelected(val user: User) : AuthScreenIntention()
}
