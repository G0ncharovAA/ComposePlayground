package com.example.composeplayground.presentation.navigation

import androidx.navigation.NavController

class NavWrapper(private val getNavController:() -> NavController) { // Now its stable!

    fun goBack() {
        getNavController().popBackStack()
    }

    fun goHome() {
        getNavController().navigate(Destinations.HomeScreen.route) {
            launchSingleTop = true
        }
    }

    fun goToDos() {
        getNavController().navigate(Destinations.ToDosScreen.route) {
            launchSingleTop = true
        }
    }

    fun goPosts() {
        getNavController().navigate(Destinations.PostsScreen.route) {
            launchSingleTop = true
        }
    }

    fun goAlbums() {
        getNavController().navigate(Destinations.AlbumsScreen.route) {
            launchSingleTop = true
        }
    }
}