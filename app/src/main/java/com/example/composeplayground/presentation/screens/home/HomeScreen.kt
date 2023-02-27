package com.example.composeplayground.presentation.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.composeplayground.domain.entities.user.User
import com.example.composeplayground.presentation.appbar.AppBar
import com.example.composeplayground.presentation.appbar.AppBarItem
import com.example.composeplayground.presentation.asMockedState
import com.example.composeplayground.presentation.mockedUser
import com.example.composeplayground.presentation.navigation.NavTabBar
import com.example.composeplayground.presentation.navigation.TabBarItem
import com.example.composeplayground.R
import com.example.composeplayground.presentation.screens.home.action.ActionsBlock
import com.example.composeplayground.presentation.screens.home.action.actionItemsDefault
import com.example.composeplayground.presentation.screens.home.hero.Hero

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel,
) {
    with(viewModel) {
        Home(
            navController = navController,
            currentUser = currentUser.observeAsState()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HomePreview() {
    Home(
        navController = rememberNavController(),
        currentUser = mockedUser.asMockedState(),
    )
}

@Composable
fun Home(
    navController: NavController,
    currentUser: State<User?>,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {

        AppBar(
            modifier = Modifier
                .fillMaxWidth(),
            navController = navController,
            appBarItems = listOf<AppBarItem>(
                AppBarItem.UserItem(
                    currentUser.value?.userName ?: stringResource(id = R.string.no_user_name)
                )
            ),
            caption = stringResource(id = R.string.home)
        )

        currentUser.value?.let {
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
            navController = navController,
            actionItems = actionItemsDefault
        )

        NavTabBar(
            modifier = Modifier
                .fillMaxWidth(),
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