package com.example.composeplayground.presentation.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.composeplayground.presentation.navigation.NavTabBarComposable
import com.example.composeplayground.presentation.navigation.TabBarItem

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel,
) {
    HomeComposable(navController = navController)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HomeComposable(navController = rememberNavController())
}

@Composable
fun HomeComposable(
    navController: NavController,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        NavTabBarComposable(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            navController = navController,
            navItems = listOf<TabBarItem>(
                TabBarItem.Home(selected = true),
                TabBarItem.ToDos(),
                TabBarItem.Posts(),
                TabBarItem.Albums(),
            )
        )
    }
}