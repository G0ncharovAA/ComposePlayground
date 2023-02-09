package com.example.composeplayground.presentation.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor() : ViewModel() {

    private val _authState = MutableLiveData<AuthState>(AuthState.SignedOut)
    val authState: LiveData<AuthState>
        get() = _authState
}