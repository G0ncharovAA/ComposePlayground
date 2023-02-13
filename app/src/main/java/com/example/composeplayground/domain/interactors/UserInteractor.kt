package com.example.composeplayground.domain.interactors

import com.example.composeplayground.data.repository.UserRepository
import com.example.composeplayground.domain.entities.User
import javax.inject.Inject

class UserInteractor @Inject constructor(
    private val userRepository: UserRepository
) {

    val currentUser = userRepository.currentUser

    fun onUserSelected(user: User) =
        userRepository.onUserSelected(user)

    suspend fun getAllUsers() = userRepository.getAllUsers()
}