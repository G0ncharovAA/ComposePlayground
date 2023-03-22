package com.example.composeplayground.presentation.screens.todos

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
import com.example.composeplayground.domain.entities.todo.ToDo
import com.example.composeplayground.domain.entities.user.User
import com.example.composeplayground.presentation.appbar.AppBar
import com.example.composeplayground.presentation.appbar.AppBarItem
import com.example.composeplayground.presentation.mockedToDo
import com.example.composeplayground.presentation.mockedUser
import com.example.composeplayground.presentation.navigation.NavTabBar
import com.example.composeplayground.presentation.navigation.NavWrapper
import com.example.composeplayground.presentation.navigation.TabBarItem
import com.example.composeplayground.presentation.screens.todos.item.ToDo

@Composable
fun ToDosScreen(
    navController: NavController,
    viewModel: ToDosViewModel,
) {
    with(viewModel.viewState.collectAsState().value) {
        ToDosContent(
            navWrapper = NavWrapper(navController),
            currentUser = currentUser,
            todos = todos,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ToDosPreview() {
    ToDosContent(
        navWrapper = NavWrapper(rememberNavController()),
        currentUser = mockedUser,
        todos = List(10) { mockedToDo },
    )
}

@Composable
private fun ToDosContent(
    navWrapper: NavWrapper,
    currentUser: User?,
    todos: List<ToDo>,
) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize(),
    ) {

        val (
            appBar,
            header,
            todoList,
            navTabBar,
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
            ),
            caption = stringResource(id = R.string.todos),
            onBackClick = {
                navWrapper.goBack()
            },
        )
        Text(
            modifier = Modifier
                .padding(12.dp)
                .constrainAs(header) {
                    top.linkTo(appBar.bottom)
                    start.linkTo(parent.start)
                },
            text = stringResource(id = R.string.these_are_todos),
            fontWeight = FontWeight.Bold,
        )
        LazyColumn(
            modifier = Modifier
                .padding(12.dp)
                .constrainAs(todoList) {
                    top.linkTo(header.bottom)
                    bottom.linkTo(navTabBar.top)
                    height = Dimension.fillToConstraints
                },
        ) {
            items(todos) { todo ->
                ToDo(todo)
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
                TabBarItem.ToDos(selected = true) {
                    navWrapper.goToDos()
                },
                TabBarItem.Posts() {
                    navWrapper.goPosts()
                },
                TabBarItem.Albums() {
                    navWrapper.goAlbums()
                },
            )
        )
    }
}