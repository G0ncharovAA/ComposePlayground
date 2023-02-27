package com.example.composeplayground.presentation.appbar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.composeplayground.R

sealed class AppBarItem {

    @Composable
    abstract fun GetComposable(navController: NavController)

    data class UserItem(val userName: String) : AppBarItem() {
        @Composable
        override fun GetComposable(navController: NavController) {
            Text(
                text = userName,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(6.dp),
                color = MaterialTheme.colors.onPrimary
            )
        }
    }
}

@Composable
fun AppBar(
    modifier: Modifier = Modifier,
    navController: NavController,
    appBarItems: List<AppBarItem>,
    caption: String,
) {
    Box(
        modifier = modifier
            .background(MaterialTheme.colors.primaryVariant)
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = LocalContext.current.getString(
                R.string.navigate_back
            ),
            modifier = Modifier
                .padding(start = 6.dp)
                .align(Alignment.CenterStart)
                .clickable {
                    navController.navigateUp()
                },
            tint = MaterialTheme.colors.onPrimary,
        )
        Text(
            text = caption,
            modifier = Modifier
                .align(Alignment.Center),
            color = MaterialTheme.colors.onPrimary
        )
        Row(
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            appBarItems.forEach {
                it.GetComposable(navController = navController)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AppBarPreview() {
    AppBar(
        modifier = Modifier.fillMaxWidth(),
        navController = rememberNavController(),
        appBarItems = listOf<AppBarItem>(
            AppBarItem.UserItem(
                userName = "Alice"
            )
        ),
        caption = "Home"
    )
}