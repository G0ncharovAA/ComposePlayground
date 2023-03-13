package com.example.composeplayground.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeplayground.domain.interactors.UserInteractor
import com.example.composeplayground.presentation.screens.home.state.HomeScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userInteractor: UserInteractor
) : ViewModel() {

    private val _viewState = MutableStateFlow<HomeScreenState>(HomeScreenState())
    val viewState get() = _viewState.asStateFlow()

    init {
        viewModelScope.launch {
            // To make possible future extension, not using map operator
            userInteractor.currentUser.collect {
                _viewState.emit(
                    _viewState.value.copy(currentUser = it)
                )
            }
        }
    }
}