package com.example.composeplayground.presentation.navigation

import androidx.navigation.NavController

class NavWrapper(private val navController: NavController) {

    fun goBack() {
        navController.popBackStack()
    }

    fun goHome() {
        navController.navigate(Destinations.HomeScreen.route) {
            launchSingleTop = true
        }
    }

    fun goToDos() {
        navController.navigate(Destinations.ToDosScreen.route) {
            launchSingleTop = true
        }
    }

    fun goPosts() {
        navController.navigate(Destinations.PostsScreen.route) {
            launchSingleTop = true
        }
    }

    fun goAlbums() {
        navController.navigate(Destinations.AlbumsScreen.route) {
            launchSingleTop = true
        }
    }
}