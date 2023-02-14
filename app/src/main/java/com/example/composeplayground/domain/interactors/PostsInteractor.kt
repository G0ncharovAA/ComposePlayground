package com.example.composeplayground.domain.interactors

import com.example.composeplayground.data.repository.PostsRepository
import com.example.composeplayground.data.repository.ToDoRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostsInteractor @Inject constructor(
    private val postsRepository: PostsRepository,
    private val userInteractor: UserInteractor,
) {

    suspend fun getPosts() =
        userInteractor.doWithCurrentUser {
            postsRepository.getPosts(it.id)
        }
}