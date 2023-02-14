package com.example.composeplayground.presentation.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.composeplayground.R
import com.example.composeplayground.presentation.stringFromId

sealed class TabBarItem {

    abstract val selected: Boolean

    @Composable
    abstract fun GetComposable(navController: NavController)

    data class Home(override val selected: Boolean = false) : TabBarItem() {
        @Composable
        override fun GetComposable(navController: NavController) {
            Text(
                text = stringFromId(id = R.string.home),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .clickable {
                        navController.navigate("home") {
                            launchSingleTop = true
                        }
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
    ) : TabBarItem() {
        @Composable
        override fun GetComposable(navController: NavController) {
            Box {
                Text(
                    text = stringFromId(id = R.string.todos),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .clickable {
                            navController.navigate("todos") {
                                launchSingleTop = true
                            }
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

    data class Posts(override val selected: Boolean = false) : TabBarItem() {
        @Composable
        override fun GetComposable(navController: NavController) {
            Text(
                text = stringFromId(id = R.string.posts),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .clickable {
                        navController.navigate("posts") {
                            launchSingleTop = true
                        }
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

    data class Albums(override val selected: Boolean = false) : TabBarItem() {
        @Composable
        override fun GetComposable(navController: NavController) {
            Text(
                text = stringFromId(id = R.string.albums),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .clickable {
                        navController.navigate("albums") {
                            launchSingleTop = true
                        }
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
fun NavTabBarComposable(
    modifier: Modifier,
    navController: NavController,
    navItems: List<TabBarItem>,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        navItems.forEach {
            it.GetComposable(navController = navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NavTabBarComposable(
        modifier = Modifier
            .fillMaxWidth(),
        navController = rememberNavController(),
        navItems = listOf<TabBarItem>(
            TabBarItem.Home(selected = true),
            TabBarItem.ToDos(selected = false, quantity = 5),
            TabBarItem.Posts(),
            TabBarItem.Albums(),
        )
    )
}