package com.example.composeplayground.presentation.screens.todos

import androidx.lifecycle.*
import com.example.composeplayground.domain.interactors.ToDoInteractor
import com.example.composeplayground.domain.interactors.UserInteractor
import com.example.composeplayground.presentation.screens.todos.state.ToDosScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToDosViewModel @Inject constructor(
    private val toDoInteractor: ToDoInteractor,
    private val userInteractor: UserInteractor,
) : ViewModel() {

    private val _viewState = MutableStateFlow<ToDosScreenState>(ToDosScreenState())
    val viewState get() = _viewState.asStateFlow()

    init {
        viewModelScope.launch {
            _viewState.emit(
                _viewState.value.copy(
                    todos = toDoInteractor.getToDos()
                )
            )
            // To make possible future extension, not using map operator
            userInteractor.currentUser.collect {
                _viewState.emit(
                    _viewState.value.copy(currentUser = it)
                )
            }
        }
    }
}