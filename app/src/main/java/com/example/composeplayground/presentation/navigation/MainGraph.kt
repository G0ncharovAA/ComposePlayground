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
        composable(
            route = "posts/{postId}",
            arguments = listOf(navArgument("postId") { type = NavType.IntType })
        ) {
            PostScreen(
                navController = navController,
                postId = it.arguments?.getInt("postId") ?: 0,
                viewModel = hiltViewModel<PostViewModel>(it),
            )
        }
        composable("albums") {
            AlbumScreen(
                navController = navController,
                viewModel = hiltViewModel<AlbumsViewModel>(it),
            )
        }
        composable(
            route = "albums/{albumId}/{photoId}",
            arguments = listOf(
                navArgument("albumId") { type = NavType.IntType },
                navArgument("photoId") { type = NavType.IntType },
            )
        ) {
            PhotoScreen(
                navController =navController,
                albumId = it.arguments?.getInt("albumId") ?: 0,
                photoId = it.arguments?.getInt("photoId") ?: 0,
                viewModel = hiltViewModel<PhotoViewModel>(it))
        }
    }
}