package com.example.composeplayground.domain.interactors

import com.example.composeplayground.data.repository.PostsRepository
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

    suspend fun getPost(id: Int) =
        userInteractor.doWithCurrentUser {
            postsRepository.getPosts(it.id).firstOrNull {
                it.id == id
            }
        }

    suspend fun getComments(postId: Int) =
        postsRepository.getComments(postId)
}