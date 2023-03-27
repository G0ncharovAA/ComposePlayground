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
import com.example.composeplayground.presentation.navigation.NavWrapper
import com.example.composeplayground.presentation.screens.home.action.ActionItem
import com.example.composeplayground.presentation.screens.home.action.ActionsBlock
import com.example.composeplayground.presentation.screens.home.hero.Hero
import kotlinx.collections.immutable.toImmutableList

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel,
) {
    with(viewModel.viewState.collectAsState().value) {
        HomeContent(
            navWrapper = NavWrapper { navController },
            currentUser = currentUser,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HomePreview() {
    val navController = rememberNavController()

    HomeContent(
        navWrapper = NavWrapper { navController },
        currentUser = mockedUser,
    )
}

@Composable
private fun HomeContent(
    navWrapper: NavWrapper,
    currentUser: User?,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {

        AppBar(
            modifier = Modifier
                .fillMaxWidth(),
            appBarItems = listOf<AppBarItem>(
                AppBarItem.UserItem(
                    currentUser?.userName ?: stringResource(id = R.string.no_user_name)
                )
            ).toImmutableList(),
            caption = stringResource(id = R.string.home),
            onBackClick = {
                navWrapper.goBack()
            },
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
                    navWrapper.goToDos()
                },
                ActionItem.PostsActionItem {
                    navWrapper.goPosts()
                },
                ActionItem.AlbumsActionItem {
                    navWrapper.goAlbums()
                },
            ).toImmutableList(),
        )
        NavTabBar(
            modifier = Modifier
                .fillMaxWidth(),
            navItems = listOf<TabBarItem>(
                TabBarItem.Home(selected = true) {
                    navWrapper.goHome()
                },
                TabBarItem.ToDos() {
                    navWrapper.goToDos()
                },
                TabBarItem.Posts() {
                    navWrapper.goPosts()
                },
                TabBarItem.Albums() {
                    navWrapper.goAlbums()
                },
            ).toImmutableList()
        )
    }
}