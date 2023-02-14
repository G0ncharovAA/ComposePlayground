package com.example.composeplayground.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun stringFromId(id: Int) =
    LocalContext.current.getString(id)