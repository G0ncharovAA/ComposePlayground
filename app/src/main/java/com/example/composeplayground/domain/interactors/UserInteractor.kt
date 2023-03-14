package com.example.composeplayground.domain.interactors

import com.example.composeplayground.data.repository.UserRepository
import com.example.composeplayground.domain.entities.user.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserInteractor @Inject constructor(
    private val userRepository: UserRepository
) {
    val currentUser = userRepository.currentUser

    fun onUserSelected(user: User) =
        userRepository.onUserSelected(user)

    suspend fun <T> doWithCurrentUser(block: suspend (User) -> T): T =
        currentUser.value?.let {
            block(it)
        } ?: throw Throwable("No user is currently logged in")

    suspend fun getAllUsers() = userRepository.getAllUsers()
}