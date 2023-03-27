package com.example.composeplayground.presentation.screens.todos.state

import com.example.composeplayground.domain.entities.todo.ToDo
import com.example.composeplayground.domain.entities.user.User

data class ToDosScreenState(
    val currentUser: User? = null,
    val todos: List<ToDo> = emptyList(),
)
