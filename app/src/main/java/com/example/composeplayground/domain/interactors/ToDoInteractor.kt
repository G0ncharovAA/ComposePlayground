package com.example.composeplayground.domain.interactors

import com.example.composeplayground.data.repository.ToDoRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ToDoInteractor @Inject constructor(
    private val toDoRepository: ToDoRepository,
    private val userInteractor: UserInteractor,
) {

    suspend fun getToDos() =
        userInteractor.doWithCurrentUser {
            toDoRepository.getToDos(it.id)
        }
}