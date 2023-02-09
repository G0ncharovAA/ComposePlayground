package com.example.composeplayground.presentation.auth

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState

@Composable
fun AuthComposable(viewModel: AuthViewModel ) {
    val authState = viewModel.authState.observeAsState()
}