package com.example.composeplayground.presentation.screens.auth

import androidx.lifecycle.*
import com.example.composeplayground.domain.entities.User
import com.example.composeplayground.domain.interactors.UserInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val userInteractor: UserInteractor
) : ViewModel() {

    val users = liveData {
        _authState.postValue(AuthState.InProgress)
        try {
            val users = userInteractor.getAllUsers()
            _authState.postValue(AuthState.UsersLoaded)
            emit(users)
        } catch (throwable: Throwable) {
            _authState.postValue(AuthState.Error)
        }
    }

    private val _authState = MutableLiveData<AuthState>(AuthState.SignedOut)
    val authState: LiveData<AuthState>
        get() = _authState

    val currentUser: LiveData<User?>
        get() = userInteractor.currentUser

    fun onUserSelected(user: User) {
        userInteractor.onUserSelected(user)
        _authState.postValue(AuthState.SignedIn)
    }
}