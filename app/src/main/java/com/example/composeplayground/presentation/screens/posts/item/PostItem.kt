package com.example.composeplayground.presentation.screens.posts.item

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.composeplayground.R
import com.example.composeplayground.domain.entities.post.Post
import com.example.composeplayground.presentation.mockedPost

@Composable
fun PostItemComposable(
    navController: NavController,
    item: Post,
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .border(
                width = 2.dp,
                color = MaterialTheme.colors.primary,
                shape = MaterialTheme.shapes.small
            )
            .clickable {
                navController.navigate("posts/${item.id}")
            }
    ) {
        val (
            header,
            title,
            body,
            arrow,
        ) = createRefs()

        Text(
            modifier = Modifier
                .constrainAs(header) {
                    top.linkTo(parent.top, margin = 6.dp)
                    start.linkTo(parent.start, margin = 6.dp)
                },
            text = stringResource(id = R.string.title),
            fontWeight = FontWeight.ExtraLight,
            fontSize = 12.sp,
        )

        Icon(
            modifier = Modifier
                .constrainAs(arrow) {
                    top.linkTo(header.top)
                    end.linkTo(parent.end, margin = 6.dp)
                },
            imageVector = Icons.Default.ArrowForward,
            contentDescription = stringResource(id = R.string.to_item_details),
        )

        Text(
            modifier = Modifier
                .padding(bottom = 6.dp)
                .constrainAs(title) {
                    top.linkTo(header.bottom)
                    start.linkTo(header.start)
                    end.linkTo(arrow.start, margin = 6.dp)
                    width = Dimension.fillToConstraints
                },
            text = item.title,
        )

        Text(
            modifier = Modifier
                .padding(bottom = 6.dp)
                .constrainAs(body) {
                    top.linkTo(title.bottom)
                    start.linkTo(title.start)
                    end.linkTo(parent.end, margin = 6.dp)
                    width = Dimension.fillToConstraints
                },
            text = item.body,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PostItemComposable(
        navController = rememberNavController(),
        item = mockedPost,
    )
}