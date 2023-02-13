package com.example.composeplayground.presentation.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
            )
        }
    }

    data class ToDos(override val selected: Boolean = false) : TabBarItem() {
        @Composable
        override fun GetComposable(navController: NavController) {
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
            )
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
            TabBarItem.ToDos(),
            TabBarItem.Posts(),
            TabBarItem.Albums(),
        )
    )
}