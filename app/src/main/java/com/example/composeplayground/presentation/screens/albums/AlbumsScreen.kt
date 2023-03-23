package com.example.composeplayground.presentation.screens.albums

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.example.composeplayground.presentation.appbar.AppBar
import com.example.composeplayground.presentation.appbar.AppBarItem
import com.example.composeplayground.presentation.navigation.NavTabBar
import com.example.composeplayground.presentation.navigation.NavWrapper
import com.example.composeplayground.presentation.navigation.TabBarItem
import com.example.composeplayground.presentation.screens.albums.item.PhotoItem
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
fun AlbumScreen(
    navController: NavController,
    viewModel: AlbumsViewModel,
) {
    with(viewModel.viewState.collectAsState().value) {
        AlbumsContent(
            navWrapper = NavWrapper(navController),
            currentUser = currentUser,
            albums = albums.toImmutableList(),
            onItemClick = { albumId, photoId ->
                navController.navigate("albums/${albumId}/${photoId}")
            },
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AlbumsPreview() {
    AlbumsContent(
        navWrapper = NavWrapper(rememberNavController()),
        currentUser = mockedUser,
        albums = List(24) { mockedPhoto }.toImmutableList(),
        onItemClick = { _, _ -> },
    )
}

@Composable
private fun AlbumsContent(
    navWrapper: NavWrapper,
    currentUser: User?,
    albums: ImmutableList<Photo>,
    onItemClick: (Int, Int) -> Unit,
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

        AppBar(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(appBar) {
                    top.linkTo(parent.top)
                },
            appBarItems = listOf<AppBarItem>(
                AppBarItem.UserItem(
                    currentUser?.userName ?: stringResource(id = R.string.no_user_name)
                )
            ).toImmutableList(),
            caption = stringResource(id = R.string.albums),
            onBackClick = {
                navWrapper.goBack()
            },
        )
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(screenContent) {
                    top.linkTo(appBar.bottom)
                    bottom.linkTo(navTabBar.top)
                    height = Dimension.fillToConstraints
                },
            columns = GridCells.Fixed(3),
        ) {
            items(albums) { photo ->
                PhotoItem(
                    item = photo,
                    onItemClick = { albumId, photoId ->
                        onItemClick(albumId, photoId)
                    },
                )
            }
        }
        if (albums.isEmpty()) {
            LinearProgressIndicator(
                modifier = Modifier.constrainAs(loading) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )
        }
        NavTabBar(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(navTabBar) {
                    bottom.linkTo(parent.bottom)
                },
            navItems = listOf<TabBarItem>(
                TabBarItem.Home() {
                    navWrapper.goHome()
                },
                TabBarItem.ToDos() {
                    navWrapper.goToDos()
                },
                TabBarItem.Posts() {
                    navWrapper.goPosts()
                },
                TabBarItem.Albums(selected = true) {
                    navWrapper.goAlbums()
                },
            ).toImmutableList()
        )
    }
}