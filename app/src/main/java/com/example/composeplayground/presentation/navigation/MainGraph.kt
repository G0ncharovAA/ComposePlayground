package com.example.composeplayground.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composeplayground.presentation.screens.albums.AlbumScreen
import com.example.composeplayground.presentation.screens.albums.AlbumsViewModel
import com.example.composeplayground.presentation.screens.auth.AuthScreen
import com.example.composeplayground.presentation.screens.auth.AuthViewModel
import com.example.composeplayground.presentation.screens.home.HomeScreen
import com.example.composeplayground.presentation.screens.home.HomeViewModel
import com.example.composeplayground.presentation.screens.photo.PhotoScreen
import com.example.composeplayground.presentation.screens.photo.PhotoViewModel
import com.example.composeplayground.presentation.screens.post.PostScreen
import com.example.composeplayground.presentation.screens.post.PostViewModel
import com.example.composeplayground.presentation.screens.posts.PostsScreen
import com.example.composeplayground.presentation.screens.posts.PostsViewModel
import com.example.composeplayground.presentation.screens.todos.ToDosScreen
import com.example.composeplayground.presentation.screens.todos.ToDosViewModel

sealed class Destinations(val route: String) {
    object AuthScreen : Destinations("auth")
    object HomeScreen : Destinations("home")
    object ToDosScreen : Destinations("todos")
    object PostsScreen : Destinations("posts")
    object PostScreen : Destinations("posts/{postId}")
    object AlbumsScreen : Destinations("albums")
    object PhotoScreen : Destinations("albums/{albumId}/{photoId}")
}

@Composable
fun NavigationMain() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Destinations.AuthScreen.route,
        modifier = Modifier.fillMaxSize(),
    ) {
        composable(Destinations.AuthScreen.route) {
            AuthScreen(
                navController = navController,
                viewModel = hiltViewModel<AuthViewModel>(it),
            )
        }
        composable(Destinations.HomeScreen.route) {
            HomeScreen(
                navController = navController,
                viewModel = hiltViewModel<HomeViewModel>(it),
            )
        }
        composable(Destinations.ToDosScreen.route) {
            ToDosScreen(
                navController = navController,
                viewModel = hiltViewModel<ToDosViewModel>(it),
            )
        }
        composable(Destinations.PostsScreen.route) {
            PostsScreen(
                navController = navController,
                viewModel = hiltViewModel<PostsViewModel>(it),
            )
        }
        composable(
            route = Destinations.PostScreen.route,
            arguments = listOf(navArgument("postId") { type = NavType.IntType })
        ) {
            PostScreen(
                navController = navController,
                viewModel = hiltViewModel<PostViewModel>(it),
            )
        }
        composable(Destinations.AlbumsScreen.route) {
            AlbumScreen(
                navController = navController,
                viewModel = hiltViewModel<AlbumsViewModel>(it),
            )
        }
        composable(
            route = Destinations.PhotoScreen.route,
            arguments = listOf(
                navArgument("albumId") { type = NavType.IntType },
                navArgument("photoId") { type = NavType.IntType },
            )
        ) {
            PhotoScreen(
                navController = navController,
                viewModel = hiltViewModel<PhotoViewModel>(it),
            )
        }
    }
}