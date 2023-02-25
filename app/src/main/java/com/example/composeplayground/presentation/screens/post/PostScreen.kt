package com.example.composeplayground.presentation.screens.post

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
import com.example.composeplayground.domain.entities.post.Comment
import com.example.composeplayground.domain.entities.post.Post
import com.example.composeplayground.domain.entities.user.User
import com.example.composeplayground.presentation.*
import com.example.composeplayground.presentation.appbar.AppBar
import com.example.composeplayground.presentation.appbar.AppBarItem
import com.example.composeplayground.presentation.screens.post.comment.Comment

@Composable
fun PostScreen(
    navController: NavController,
    postId: Int,
    viewModel: PostViewModel,
) {
    with(viewModel) {
        setPostId(postId)
        Post(
            navController = navController,
            currentUser = currentUser.observeAsState(),
            post = post.observeAsState(),
            comments = comments.observeAsState(initial = emptyList())
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PostPreview() {
    Post(
        navController = rememberNavController(),
        currentUser = mockedUser.asMockedState(),
        post = mockedPost.asMockedState(),
        comments = List(10) { mockedComment }
            .asMockedState(),
    )
}

@Composable
fun Post(
    navController: NavController,
    currentUser: State<User?>,
    post: State<Post?>,
    comments: State<List<Comment>>,
) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize(),
    ) {
        val (
            appBar,
            header,
            postTitle,
            commentsHeader,
            commentsWidget,
        ) = createRefs()

        AppBar(
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
            caption = stringResource(id = R.string.post)
        )

        Text(
            modifier = Modifier.constrainAs(header) {
                start.linkTo(parent.start, margin = 12.dp)
                top.linkTo(appBar.bottom, margin = 64.dp)
            },
            text = stringResource(id = R.string.comments_for)
        )

        post.value?.title?.let {
            Text(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .constrainAs(postTitle) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(header.bottom, margin = 6.dp)
                },
                text = it,
                fontWeight = FontWeight.Bold,
            )
        }

        Text(
            modifier = Modifier.constrainAs(commentsHeader) {
                start.linkTo(parent.start, margin = 12.dp)
                top.linkTo(postTitle.bottom, margin = 12.dp)
            },
            text = stringResource(id = R.string.comments)
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .constrainAs(commentsWidget) {
                    top.linkTo(commentsHeader.bottom, margin = 6.dp)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.fillToConstraints
                },
        ) {
            items(comments.value) { comment ->
                Comment(item = comment)
            }
        }
    }
}