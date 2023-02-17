package com.example.composeplayground.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import com.example.composeplayground.domain.entities.album.Album
import com.example.composeplayground.domain.entities.album.Photo
import com.example.composeplayground.domain.entities.post.Comment
import com.example.composeplayground.domain.entities.post.Post
import com.example.composeplayground.domain.entities.todo.ToDo
import com.example.composeplayground.domain.entities.user.Address
import com.example.composeplayground.domain.entities.user.Company
import com.example.composeplayground.domain.entities.user.Geo
import com.example.composeplayground.domain.entities.user.User
import kotlinx.serialization.SerialName

@Composable
fun <T> T.asMockedState() =
    MutableLiveData<T>(this).asFlow().collectAsState(initial = this)

@Composable
fun nullAsMockedState() =
    MutableLiveData(null).asFlow().collectAsState(initial = null)

val mockedUser = User(
    id = 0,
    name = "Alice",
    userName = "Alice",
    email = "alice@example.com",
    address = Address(
        street = "mockedStreet",
        suite = "mockedSuite",
        city = "mockedCity",
        zipcode = "92998-3874",
        geo = Geo(
            lat = 0.0,
            lng = 0.0,
        ),
    ),
    phone = "+1 111111111",
    website = "example.com",
    company = Company(
        name = "mockedCompany",
        catchPhrase = "mock is mocked!",
        bs = "bs?"
    ),
)

val mockedToDo = ToDo(
    userId = 0,
    id = 0,
    title = "mocked Title",
    completed = true,
)

val mockedPost = Post(
    userId = 0,
    id = 0,
    title = "mocked Title",
    body = "mocked body",
)

val mockedComment = Comment(
    postId = 0,
    id = 0,
    name = "Mocked Name",
    email = "mocked@email",
    body = "mocked body",
)

val mockedAlbum = Album(
    userId = 0,
    id = 0,
    title = "mocked Title",
)

val mockedPhoto = Photo(
    albumId = 0,
    id = 0,
    title = "mocked Title",
    url = "https://via.placeholder.com/600/92c952",
    thumbnailUrl = "https://via.placeholder.com/150/92c952"
)