package com.example.composeplayground.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow

@Composable
fun <T> T.asMockedState() =
    MutableLiveData<T>(this).asFlow().collectAsState(initial = this)

@Composable
fun nullAsMockedState() =
    MutableLiveData(null).asFlow().collectAsState(initial = null)

@Composable
fun stringFromId(id: Int) =
    LocalContext.current.getString(id)