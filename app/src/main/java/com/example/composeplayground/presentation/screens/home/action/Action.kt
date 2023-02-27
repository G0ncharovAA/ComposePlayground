package com.example.composeplayground.presentation.screens.home.action

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.composeplayground.R

sealed class ActionItem {

    @Composable
    abstract fun GetComposable(navController: NavController)

    object ToDosActionItem : ActionItem() {
        @Composable
        override fun GetComposable(navController: NavController) {
            val backgroundColor = MaterialTheme.colors.secondary
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate("todos") {
                            launchSingleTop = true
                        }
                    }
                    .drawBehind {
                        drawRoundRect(
                            color = backgroundColor,
                            cornerRadius = CornerRadius(6.dp.toPx())
                        )
                    }
                    .padding(6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = stringResource(id = R.string.todos),
                    modifier = Modifier.padding(end = 6.dp),
                )
                Text(
                    text = stringResource(id = R.string.todos)
                )
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = stringResource(id = R.string.todos),
                    modifier = Modifier.padding(end = 6.dp),
                )
            }
        }
    }

    object PostsActionItem : ActionItem() {
        @Composable
        override fun GetComposable(navController: NavController) {
            val backgroundColor = MaterialTheme.colors.secondary
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 9.dp)
                    .clickable {
                        navController.navigate("posts") {
                            launchSingleTop = true
                        }
                    }
                    .drawBehind {
                        drawRoundRect(
                            color = backgroundColor,
                            cornerRadius = CornerRadius(6.dp.toPx())
                        )
                    }
                    .padding(6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = stringResource(id = R.string.posts),
                    modifier = Modifier.padding(end = 6.dp),
                )
                Text(
                    text = stringResource(id = R.string.posts)
                )
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = stringResource(id = R.string.posts),
                    modifier = Modifier.padding(end = 6.dp),
                )
            }
        }
    }

    object AlbumsActionItem : ActionItem() {
        @Composable
        override fun GetComposable(navController: NavController) {
            val backgroundColor = MaterialTheme.colors.secondary
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate("albums") {
                            launchSingleTop = true
                        }
                    }
                    .drawBehind {
                        drawRoundRect(
                            color = backgroundColor,
                            cornerRadius = CornerRadius(6.dp.toPx())
                        )
                    }
                    .padding(6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = Icons.Default.Place,
                    contentDescription = stringResource(id = R.string.albums),
                    modifier = Modifier.padding(end = 6.dp),
                )
                Text(
                    text = stringResource(id = R.string.albums)
                )
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = stringResource(id = R.string.albums),
                    modifier = Modifier.padding(end = 6.dp),
                )
            }
        }
    }
}

val actionItemsDefault = listOf<ActionItem>(
    ActionItem.ToDosActionItem,
    ActionItem.PostsActionItem,
    ActionItem.AlbumsActionItem,
)

@Composable
fun ActionsBlock(
    modifier: Modifier = Modifier,
    navController: NavController,
    actionItems: List<ActionItem>,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        actionItems.forEach {
            it.GetComposable(navController = navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ActionsBlockPreview() {
    ActionsBlock(
        modifier = Modifier.padding(12.dp),
        navController = rememberNavController(),
        actionItems = actionItemsDefault,
    )
}