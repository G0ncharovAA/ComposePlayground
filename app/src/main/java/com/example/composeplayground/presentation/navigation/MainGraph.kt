package com.example.composeplayground.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composeplayground.presentation.screens.albums.AlbumScreen
import com.example.composeplayground.presentation.screens.albums.AlbumsViewModel
import com.example.composeplayground.presentation.screens.auth.AuthScreen
import com.example.composeplayground.presentation.screens.auth.AuthViewModel
import com.example.composeplayground.presentation.screens.home.HomeScreen
import com.example.composeplayground.presentation.screens.home.HomeViewModel
import com.example.composeplayground.presentation.screens.posts.PostsScreen
import com.example.composeplayground.presentation.screens.posts.PostsViewModel
import com.example.composeplayground.presentation.screens.todos.ToDosScreen
import com.example.composeplayground.presentation.screens.todos.ToDosViewModel

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
                viewModel = hiltViewModel<HomeViewModel>(it),
            )
        }
        composable("todos") {
            ToDosScreen(
                navController = navController,
                viewModel = hiltViewModel<ToDosViewModel>(it),
            )
        }
        composable("posts") {
            PostsScreen(
                navController = navController,
                viewModel = hiltViewModel<PostsViewModel>(it),
            )
        }
        composable("albums") {
            AlbumScreen(
                navController = navController,
                viewModel = hiltViewModel<AlbumsViewModel>(it),
            )
        }
    }
}