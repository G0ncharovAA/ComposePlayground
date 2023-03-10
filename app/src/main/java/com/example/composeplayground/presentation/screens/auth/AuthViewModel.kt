package com.example.composeplayground.presentation.screens.auth

import androidx.lifecycle.*
import com.example.composeplayground.domain.interactors.UserInteractor
import com.example.composeplayground.presentation.screens.auth.intention.AuthScreenIntention
import com.example.composeplayground.presentation.screens.auth.state.AuthScreenState
import com.example.composeplayground.presentation.screens.auth.state.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val userInteractor: UserInteractor
) : ViewModel() {

    private val _viewState = MutableStateFlow<AuthScreenState>(AuthScreenState())
    val viewState get() = _viewState.asStateFlow()

    init {
        viewModelScope.launch {
            emitNewState(
                _viewState.value.copy(
                    authState = AuthState.InProgress
                )
            )
            try {
                val users = userInteractor.getAllUsers()
                emitNewState(
                    _viewState.value.copy(
                        authState = AuthState.UsersLoaded,
                        users = users,
                    )
                )
            } catch (throwable: Throwable) {
                emitNewState(
                    _viewState.value.copy(
                        authState = AuthState.Error,
                    )
                )
            }
            userInteractor.currentUser.collect() {
                emitNewState(
                    _viewState.value.copy(
                        currentUser = it,
                        authState = it?.let {
                            AuthState.SignedIn
                        } ?: _viewState.value.authState,
                    )
                )
            }
        }
    }

    fun dispatchIntention(intention: AuthScreenIntention) {
        when (intention) {
            is AuthScreenIntention.DropDownExpandedChange -> {
                viewModelScope.launch {
                    emitNewState(
                        _viewState.value.copy(
                            dropDownExpanded = intention.expanded
                        )
                    )
                }
            }
            is AuthScreenIntention.UserSelected -> {
                userInteractor.onUserSelected(intention.user)
            }
        }
    }

    private suspend fun emitNewState(state: AuthScreenState) {
        _viewState.emit(state)
    }
}