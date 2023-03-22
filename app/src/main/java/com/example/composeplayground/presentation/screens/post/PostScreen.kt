package com.example.composeplayground.presentation.screens.post

import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.composeplayground.R
import com.example.composeplayground.domain.entities.post.Comment
import com.example.composeplayground.domain.entities.post.Post
import com.example.composeplayground.domain.entities.user.User
import com.example.composeplayground.presentation.*
import com.example.composeplayground.presentation.appbar.AppBar
import com.example.composeplayground.presentation.appbar.AppBarItem
import com.example.composeplayground.presentation.navigation.NavWrapper
import com.example.composeplayground.presentation.screens.post.comment.Comment

@Composable
fun PostScreen(
    navController: NavController,
    viewModel: PostViewModel,
) {
    with(viewModel.viewState.collectAsState().value) {
        PostContent(
            navWrapper = NavWrapper(navController),
            currentUser = currentUser,
            post = post,
            comments = comments,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PostPreview() {
    PostContent(
        navWrapper = NavWrapper(rememberNavController()),
        currentUser = mockedUser,
        post = mockedPost,
        comments = List(10) { mockedComment },
    )
}

@Composable
private fun PostContent(
    navWrapper: NavWrapper,
    currentUser: User?,
    post: Post?,
    comments: List<Comment>,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {

        AppBar(
            modifier = Modifier
                .fillMaxWidth(),
            onBackClick = {
                navWrapper.goBack()
            },
            appBarItems = listOf<AppBarItem>(
                AppBarItem.UserItem(
                    currentUser?.userName ?: stringResource(id = R.string.no_user_name)
                )
            ),
            caption = stringResource(id = R.string.post)
        )
        Text(
            modifier = Modifier.padding(start = 12.dp, top = 64.dp),
            text = stringResource(id = R.string.comments_for)
        )
        post?.title?.let {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 6.dp),
                textAlign = TextAlign.Center,
                text = it,
                fontWeight = FontWeight.Bold,
            )
        }
        Text(
            modifier = Modifier.padding(start = 12.dp, top = 12.dp),
            text = stringResource(id = R.string.comments)
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
        ) {
            items(comments) { comment ->
                Comment(item = comment)
            }
        }
    }
}