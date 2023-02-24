package com.example.composeplayground.presentation.screens.albums

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.composeplayground.R
import com.example.composeplayground.domain.entities.album.Photo
import com.example.composeplayground.domain.entities.user.User
import com.example.composeplayground.presentation.*
import com.example.composeplayground.presentation.appbar.AppBarComposable
import com.example.composeplayground.presentation.appbar.AppBarItem
import com.example.composeplayground.presentation.navigation.NavTabBarComposable
import com.example.composeplayground.presentation.navigation.TabBarItem
import com.example.composeplayground.presentation.screens.albums.item.PhotoItemComposable

@Composable
fun AlbumScreen(
    navController: NavController,
    viewModel: AlbumsViewModel,
) {
    with(viewModel) {
        AlbumsComposable(
            navController = navController,
            currentUser = currentUser.observeAsState(),
            albums = albums.observeAsState(emptyList()),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AlbumsComposable(
        navController = rememberNavController(),
        currentUser = mockedUser.asMockedState(),
        albums = List(24) { mockedPhoto }
            .asMockedState(),
    )
}

@Composable
fun AlbumsComposable(
    navController: NavController,
    currentUser: State<User?>,
    albums: State<List<Photo>>,
) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize(),
    ) {

        val (
            appBar,
            screenContent,
            navTabBar,
            loading,
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
                    currentUser.value?.userName ?: stringResource(id = R.string.no_user_name)
                )
            ),
            caption = stringResource(id = R.string.albums)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(screenContent) {
                    top.linkTo(appBar.bottom)
                    bottom.linkTo(navTabBar.top)
                    height = Dimension.fillToConstraints
                }
        ) {
            items(albums.value) { photo ->
                PhotoItemComposable(
                    navController = navController,
                    item = photo,
                )
            }
        }

        if (albums.value.isEmpty()) {
            LinearProgressIndicator(
                modifier = Modifier.constrainAs(loading) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
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
                TabBarItem.Home(),
                TabBarItem.ToDos(),
                TabBarItem.Posts(),
                TabBarItem.Albums(selected = true),
            )
        )
    }
}