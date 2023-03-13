package com.example.composeplayground.presentation.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.composeplayground.presentation.appbar.AppBar
import com.example.composeplayground.presentation.appbar.AppBarItem
import com.example.composeplayground.presentation.navigation.NavTabBar
import com.example.composeplayground.presentation.navigation.TabBarItem
import com.example.composeplayground.R
import com.example.composeplayground.domain.entities.user.User
import com.example.composeplayground.presentation.mockedUser
import com.example.composeplayground.presentation.navigation.Destinations
import com.example.composeplayground.presentation.screens.home.action.ActionItem
import com.example.composeplayground.presentation.screens.home.action.ActionsBlock
import com.example.composeplayground.presentation.screens.home.hero.Hero

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel,
) {
    with(viewModel.viewState.collectAsState().value) {
        Home(
            navController = navController,
            currentUser = currentUser,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HomePreview() {
    Home(
        navController = rememberNavController(),
        currentUser = mockedUser,
    )
}

@Composable
fun Home(
    navController: NavController,
    currentUser: User?,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {

        AppBar(
            modifier = Modifier
                .fillMaxWidth(),
            onBackClick = {
                navController.popBackStack()
            },
            appBarItems = listOf<AppBarItem>(
                AppBarItem.UserItem(
                    currentUser?.userName ?: stringResource(id = R.string.no_user_name)
                )
            ),
            caption = stringResource(id = R.string.home)
        )

        currentUser?.let {
            Hero(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 64.dp)
                    .padding(horizontal = 12.dp),
                user = it,
            )
        }

        ActionsBlock(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.0f)
                .padding(top = 64.dp)
                .padding(horizontal = 12.dp),
            actionItems = listOf(
                ActionItem.ToDosActionItem {
                    navController.navigate(Destinations.ToDosScreen.route) {
                        launchSingleTop = true
                    }
                },
                ActionItem.PostsActionItem {
                    navController.navigate(Destinations.PostsScreen.route) {
                        launchSingleTop = true
                    }
                },
                ActionItem.AlbumsActionItem {
                    navController.navigate(Destinations.AlbumsScreen.route) {
                        launchSingleTop = true
                    }
                },
            )
        )

        NavTabBar(
            modifier = Modifier
                .fillMaxWidth(),
            navItems = listOf<TabBarItem>(
                TabBarItem.Home(selected = true) {
                    navController.navigate(Destinations.HomeScreen.route) {
                        launchSingleTop = true
                    }
                },
                TabBarItem.ToDos() {
                    navController.navigate(Destinations.ToDosScreen.route) {
                        launchSingleTop = true
                    }
                },
                TabBarItem.Posts() {
                    navController.navigate(Destinations.PostsScreen.route) {
                        launchSingleTop = true
                    }
                },
                TabBarItem.Albums() {
                    navController.navigate(Destinations.AlbumsScreen.route) {
                        launchSingleTop = true
                    }
                },
            )
        )
    }
}