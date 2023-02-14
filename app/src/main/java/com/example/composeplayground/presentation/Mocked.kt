package com.example.composeplayground.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import com.example.composeplayground.domain.entities.Address
import com.example.composeplayground.domain.entities.Company
import com.example.composeplayground.domain.entities.Geo
import com.example.composeplayground.domain.entities.User

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