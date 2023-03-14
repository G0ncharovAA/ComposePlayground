package com.example.composeplayground.presentation.screens.posts

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.composeplayground.R
import com.example.composeplayground.domain.entities.post.Post
import com.example.composeplayground.domain.entities.user.User
import com.example.composeplayground.presentation.*
import com.example.composeplayground.presentation.appbar.AppBar
import com.example.composeplayground.presentation.appbar.AppBarItem
import com.example.composeplayground.presentation.navigation.NavTabBar
import com.example.composeplayground.presentation.navigation.NavWrapper
import com.example.composeplayground.presentation.navigation.TabBarItem
import com.example.composeplayground.presentation.screens.posts.item.PostItem

@Composable
fun PostsScreen(
    navController: NavController,
    viewModel: PostsViewModel,
) {
    with(viewModel.viewState.collectAsState().value) {
        Posts(
            navWrapper = NavWrapper(navController),
            onItemClick = { itemId ->
                navController.navigate("posts/${itemId}")
            },
            currentUser = currentUser,
            posts = posts,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Posts(
        navWrapper = NavWrapper(rememberNavController()),
        onItemClick = {},
        currentUser = mockedUser,
        posts = List(10) { mockedPost },
    )
}

@Composable
fun Posts(
    navWrapper: NavWrapper,
    onItemClick: (Int) -> Unit,
    currentUser: User?,
    posts: List<Post>,
) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize(),
    ) {

        val (
            appBar,
            header,
            postsList,
            navTabBar,
        ) = createRefs()

        AppBar(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(appBar) {
                    top.linkTo(parent.top)
                },
            onBackClick = {
                navWrapper.goBack()
            },
            appBarItems = listOf<AppBarItem>(
                AppBarItem.UserItem(
                    currentUser?.userName ?: stringResource(id = R.string.no_user_name)
                )
            ),
            caption = stringResource(id = R.string.posts)
        )

        currentUser?.let {
            Text(
                modifier = Modifier
                    .padding(12.dp)
                    .constrainAs(header) {
                        top.linkTo(appBar.bottom)
                        start.linkTo(parent.start)
                    },
                text = stringResource(
                    id = R.string.posts_from_,
                    it.name,
                ),
                fontWeight = FontWeight.Bold,
            )
        }

        LazyColumn(
            modifier = Modifier
                .padding(12.dp)
                .constrainAs(postsList) {
                    top.linkTo(header.bottom)
                    bottom.linkTo(navTabBar.top)
                    height = Dimension.fillToConstraints
                },
        ) {
            items(posts) { post ->
                PostItem(
                    onItemClick = onItemClick,
                    item = post,
                )
            }
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
                TabBarItem.Posts(selected = true) {
                    navWrapper.goPosts()
                },
                TabBarItem.Albums() {
                    navWrapper.goAlbums()
                },
            )
        )
    }
}