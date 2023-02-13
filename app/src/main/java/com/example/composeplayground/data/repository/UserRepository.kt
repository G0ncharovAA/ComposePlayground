package com.example.composeplayground.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.composeplayground.data.service.JsonPlaceHolderService
import com.example.composeplayground.domain.entities.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val service: JsonPlaceHolderService
) {

    private val _currentUser = MutableLiveData<User?>()
    val currentUser: LiveData<User?>
        get() = _currentUser

    fun onUserSelected(user: User) {
        _currentUser.postValue(user)
    }

    suspend fun getAllUsers() = service.getAllUsers()
}