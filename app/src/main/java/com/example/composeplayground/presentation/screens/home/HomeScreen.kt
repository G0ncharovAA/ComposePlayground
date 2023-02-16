package com.example.composeplayground.presentation.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.composeplayground.domain.entities.user.User
import com.example.composeplayground.presentation.appbar.AppBarComposable
import com.example.composeplayground.presentation.appbar.AppBarItem
import com.example.composeplayground.presentation.asMockedState
import com.example.composeplayground.presentation.mockedUser
import com.example.composeplayground.presentation.navigation.NavTabBarComposable
import com.example.composeplayground.presentation.navigation.TabBarItem
import com.example.composeplayground.presentation.stringFromId
import com.example.composeplayground.R
import com.example.composeplayground.presentation.screens.home.action.ActionsBlock
import com.example.composeplayground.presentation.screens.home.action.actionItemsDefault
import com.example.composeplayground.presentation.screens.home.hero.HeroComposable

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel,
) {
    with(viewModel) {
        HomeComposable(
            navController = navController,
            currentUser = currentUser.observeAsState()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HomeComposable(
        navController = rememberNavController(),
        currentUser = mockedUser.asMockedState(),
    )
}

@Composable
fun HomeComposable(
    navController: NavController,
    currentUser: State<User?>,
) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize(),
    ) {

        val (
            appBar,
            screenContent,
            navTabBar,
        ) = createRefs()

        AppBarComposable(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(appBar) {
                    top.linkTo(parent.top)
                },
            navController = navController,
            appBarItems = listOf<AppBarItem>(
                AppBarItem.UserItem(
                    currentUser.value?.userName ?: stringFromId(id = R.string.no_user_name)
                )
            ),
            caption = stringFromId(id = R.string.home)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .constrainAs(screenContent) {
                    top.linkTo(appBar.bottom)
                    bottom.linkTo(navTabBar.top)
                    height = Dimension.fillToConstraints
                }
        ) {
            currentUser.value?.let {
                HeroComposable(
                    modifier = Modifier
                        .padding(top = 64.dp)
                        .align(Alignment.CenterHorizontally),
                    user = it,
                )
            }
            ActionsBlock(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 12.dp
                    )
                    .padding(top = 32.dp),
                navController = navController,
                actionItems = actionItemsDefault
            )
        }

        NavTabBarComposable(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(navTabBar) {
                    bottom.linkTo(parent.bottom)
                },
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