package com.example.composeplayground.presentation.screens.todos

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.composeplayground.R
import com.example.composeplayground.domain.entities.user.User
import com.example.composeplayground.presentation.appbar.AppBarComposable
import com.example.composeplayground.presentation.appbar.AppBarItem
import com.example.composeplayground.presentation.asMockedState
import com.example.composeplayground.presentation.mockedUser
import com.example.composeplayground.presentation.navigation.NavTabBarComposable
import com.example.composeplayground.presentation.navigation.TabBarItem
import com.example.composeplayground.presentation.stringFromId

@Composable
fun ToDosScreen(
    navController: NavController,
    viewModel: ToDosViewModel,
) {
    with(viewModel) {
        ToDosComposable(
            navController = navController,
            currentUser = currentUser.observeAsState()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ToDosComposable(
        navController = rememberNavController(),
        currentUser = mockedUser.asMockedState(),
    )
}

@Composable
fun ToDosComposable(
    navController: NavController,
    currentUser: State<User?>,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        AppBarComposable(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter),
            navController = navController,
            appBarItems = listOf<AppBarItem>(
                AppBarItem.UserItem(
                    currentUser.value?.userName ?: stringFromId(id = R.string.no_user_name)
                )
            )
        )

        Text(
            text = "To do",
            modifier = Modifier.align(Alignment.Center),
        )

        NavTabBarComposable(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            navController = navController,
            navItems = listOf<TabBarItem>(
                TabBarItem.Home(),
                TabBarItem.ToDos(selected = true),
                TabBarItem.Posts(),
                TabBarItem.Albums(),
            )
        )
    }
}