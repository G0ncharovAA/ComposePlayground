package com.example.composeplayground.presentation.screens.todos

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.example.composeplayground.domain.entities.user.User
import com.example.composeplayground.domain.interactors.ToDoInteractor
import com.example.composeplayground.domain.interactors.UserInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ToDosViewModel @Inject constructor(
    private val toDoInteractor: ToDoInteractor,
    private val userInteractor: UserInteractor,
) : ViewModel() {

    val todos = liveData {
        emit(toDoInteractor.getToDos())
    }

    val currentUser: LiveData<User?>
        get() = userInteractor.currentUser.asLiveData()
}