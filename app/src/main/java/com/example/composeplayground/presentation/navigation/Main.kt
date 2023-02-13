package com.example.composeplayground.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composeplayground.presentation.screens.auth.AuthScreen
import com.example.composeplayground.presentation.screens.auth.AuthViewModel
import com.example.composeplayground.presentation.screens.home.HomeScreen

@Composable
fun NavigationMain() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "auth",
        modifier = Modifier.fillMaxSize(),
    ) {
        composable("auth") {
            AuthScreen(
                navController = navController,
                viewModel = hiltViewModel<AuthViewModel>(it),
            )
        }
        composable("home") {
            HomeScreen(
                navController = navController,
            )
        }
    }
}