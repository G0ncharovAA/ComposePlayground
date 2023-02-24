package com.example.composeplayground.presentation.screens.posts

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
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
import com.example.composeplayground.presentation.appbar.AppBarComposable
import com.example.composeplayground.presentation.appbar.AppBarItem
import com.example.composeplayground.presentation.navigation.NavTabBarComposable
import com.example.composeplayground.presentation.navigation.TabBarItem
import com.example.composeplayground.presentation.screens.posts.item.PostItemComposable

@Composable
fun PostsScreen(
    navController: NavController,
    viewModel: PostsViewModel,
) {
    with(viewModel) {
        PostsComposable(
            navController = navController,
            currentUser = currentUser.observeAsState(),
            posts = posts.observeAsState(initial = emptyList())
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PostsComposable(
        navController = rememberNavController(),
        currentUser = mockedUser.asMockedState(),
        posts = List(10) { mockedPost }
            .asMockedState(),
    )
}

@Composable
fun PostsComposable(
    navController: NavController,
    currentUser: State<User?>,
    posts: State<List<Post>>,
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
            caption = stringResource(id = R.string.posts)
        )

        currentUser.value?.let {
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
            items(posts.value) { post ->
                PostItemComposable(
                    navController = navController,
                    item = post,
                )
            }
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
                TabBarItem.Posts(selected = true),
                TabBarItem.Albums(),
            )
        )
    }
}