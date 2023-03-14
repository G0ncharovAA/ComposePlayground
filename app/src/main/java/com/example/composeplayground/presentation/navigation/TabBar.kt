package com.example.composeplayground.presentation.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.composeplayground.R

sealed class TabBarItem(private val onItemClick: () -> Unit) {

    abstract val selected: Boolean

    @Composable
    abstract fun GetComposable()

    data class Home(
        override val selected: Boolean = false,
        private val onItemClick: () -> Unit,
    ) : TabBarItem(onItemClick) {
        @Composable
        override fun GetComposable() {
            Text(
                text = stringResource(id = R.string.home),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .clickable {
                        onItemClick()
                    }
                    .padding(6.dp),
                fontWeight = if (selected) {
                    FontWeight.Bold
                } else {
                    FontWeight.Normal
                },
            )
        }
    }

    data class ToDos(
        override val selected: Boolean = false,
        val quantity: Int = 0,
        private val onItemClick: () -> Unit,
    ) : TabBarItem(onItemClick) {
        @Composable
        override fun GetComposable() {
            Box {
                Text(
                    text = stringResource(id = R.string.todos),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .clickable {
                            onItemClick()
                        }
                        .padding(6.dp),
                    fontWeight = if (selected) {
                        FontWeight.Bold
                    } else {
                        FontWeight.Normal
                    },
                )
                if (quantity > 0) {
                    Text(
                        text = quantity.toString(),
                        modifier = Modifier.align(Alignment.TopEnd)
                    )
                }
            }
        }
    }

    data class Posts(
        override val selected: Boolean = false,
        private val onItemClick: () -> Unit,
    ) : TabBarItem(onItemClick) {
        @Composable
        override fun GetComposable() {
            Text(
                text = stringResource(id = R.string.posts),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .clickable {
                        onItemClick()
                    }
                    .padding(6.dp),
                fontWeight = if (selected) {
                    FontWeight.Bold
                } else {
                    FontWeight.Normal
                },
            )
        }
    }

    data class Albums(
        override val selected: Boolean = false,
        private val onItemClick: () -> Unit,
    ) : TabBarItem(onItemClick) {
        @Composable
        override fun GetComposable() {
            Text(
                text = stringResource(id = R.string.albums),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .clickable {
                        onItemClick()
                    }
                    .padding(6.dp),
                fontWeight = if (selected) {
                    FontWeight.Bold
                } else {
                    FontWeight.Normal
                },
            )
        }
    }
}

@Composable
fun NavTabBar(
    modifier: Modifier = Modifier,
    navItems: List<TabBarItem>,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        navItems.forEach {
            it.GetComposable()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NavTabBarPreview() {
    NavTabBar(
        modifier = Modifier
            .fillMaxWidth(),
        navItems = listOf<TabBarItem>(
            TabBarItem.Home(selected = true) {},
            TabBarItem.ToDos(selected = false, quantity = 5) {},
            TabBarItem.Posts {},
            TabBarItem.Albums {},
        )
    )
}