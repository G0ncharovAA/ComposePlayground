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
import com.example.composeplayground.R
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

sealed class ActionItem(
    private val onActionClick: () -> Unit
) {

    @Composable
    abstract fun GetComposable(modifier: Modifier)

    data class ToDosActionItem(private val onActionClick: () -> Unit) : ActionItem(onActionClick) {
        @Composable
        override fun GetComposable(modifier: Modifier) {
            val backgroundColor = MaterialTheme.colors.secondary
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .clickable {
                        onActionClick()
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
                    modifier = Modifier.padding(end = 6.dp),
                    imageVector = Icons.Default.Check,
                    contentDescription = stringResource(id = R.string.todos),
                )
                Text(
                    text = stringResource(id = R.string.todos)
                )
                Icon(
                    modifier = Modifier.padding(end = 6.dp),
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = stringResource(id = R.string.todos),
                )
            }
        }
    }

    data class PostsActionItem(private val onActionClick: () -> Unit) : ActionItem(onActionClick) {
        @Composable
        override fun GetComposable(modifier: Modifier) {
            val backgroundColor = MaterialTheme.colors.secondary
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(vertical = 9.dp)
                    .clickable {
                        onActionClick()
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
                    modifier = Modifier.padding(end = 6.dp),
                    imageVector = Icons.Default.Send,
                    contentDescription = stringResource(id = R.string.posts),
                )
                Text(
                    text = stringResource(id = R.string.posts)
                )
                Icon(
                    modifier = Modifier.padding(end = 6.dp),
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = stringResource(id = R.string.posts),
                )
            }
        }
    }

    data class AlbumsActionItem(private val onActionClick: () -> Unit) : ActionItem(onActionClick) {
        @Composable
        override fun GetComposable(modifier: Modifier) {
            val backgroundColor = MaterialTheme.colors.secondary
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .clickable {
                        onActionClick()
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
                    modifier = Modifier.padding(end = 6.dp),
                    imageVector = Icons.Default.Place,
                    contentDescription = stringResource(id = R.string.albums),
                )
                Text(
                    text = stringResource(id = R.string.albums)
                )
                Icon(
                    modifier = Modifier.padding(end = 6.dp),
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = stringResource(id = R.string.albums),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ActionsBlockPreview() {
    ActionsBlock(
        modifier = Modifier.padding(12.dp),
        actionItems = actionItemsDefault.toImmutableList(),
    )
}

@Composable
fun ActionsBlock(
    actionItems: ImmutableList<ActionItem>,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        actionItems.forEach {
            it.GetComposable(Modifier)
        }
    }
}