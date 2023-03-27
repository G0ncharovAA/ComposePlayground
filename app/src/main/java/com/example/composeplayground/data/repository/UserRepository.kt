package com.example.composeplayground.data.repository

import com.example.composeplayground.data.service.JsonPlaceHolderService
import com.example.composeplayground.domain.entities.user.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val service: JsonPlaceHolderService
) {

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?>
        get() = _currentUser

    fun onUserSelected(user: User) {
        _currentUser.update { user }
    }

    suspend fun getAllUsers() = service.getAllUsers()
}