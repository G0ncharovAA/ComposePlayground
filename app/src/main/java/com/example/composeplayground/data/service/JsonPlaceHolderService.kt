package com.example.composeplayground.data.service

import com.example.composeplayground.domain.entities.album.Album
import com.example.composeplayground.domain.entities.post.Comment
import com.example.composeplayground.domain.entities.post.Post
import com.example.composeplayground.domain.entities.todo.ToDo
import com.example.composeplayground.domain.entities.user.User
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JsonPlaceHolderService @Inject constructor(
    private val ktorClient: HttpClient
) {

    suspend fun getAllUsers(): List<User> =
        ktorClient.get("users").body()

    suspend fun getToDos(userId: Int): List<ToDo> =
        ktorClient.get("users/$userId/todos").body()

    suspend fun getPosts(userId: Int): List<Post> =
        ktorClient.get("users/$userId/posts").body()

    suspend fun getComments(postId: Int): List<Comment> =
        ktorClient.get("posts/$postId/comments").body()

    suspend fun getAlbums(userId: Int): List<Album> =
        ktorClient.get("users/$userId/albums").body()
}