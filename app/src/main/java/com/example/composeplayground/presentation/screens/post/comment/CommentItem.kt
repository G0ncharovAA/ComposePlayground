package com.example.composeplayground.presentation.screens.post.comment

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.composeplayground.R
import com.example.composeplayground.domain.entities.post.Comment
import com.example.composeplayground.presentation.mockedComment

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Comment(item = mockedComment)
}

@Composable
fun Comment(
    item: Comment,
    modifier: Modifier = Modifier,
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        val (
            header,
            body,
            icon,
        ) = createRefs()
        Text(
            modifier = Modifier
                .constrainAs(header) {
                    top.linkTo(parent.top, margin = 6.dp)
                    end.linkTo(icon.start, margin = 6.dp)
                    start.linkTo(parent.start, margin = 6.dp)
                    width = Dimension.fillToConstraints
                },
            text = stringResource(
                id = R.string.comment_from_,
                item.name,
            ),
            fontWeight = FontWeight.ExtraLight,
            fontSize = 12.sp,
        )
        Icon(
            modifier = Modifier
                .constrainAs(icon) {
                    top.linkTo(header.top)
                    end.linkTo(parent.end, margin = 6.dp)
                },
            imageVector = Icons.Default.Info,
            contentDescription = stringResource(id = R.string.comments),
        )
        Text(
            modifier = Modifier
                .padding(bottom = 6.dp)
                .constrainAs(body) {
                    top.linkTo(header.bottom, margin = 6.dp)
                    start.linkTo(header.start)
                    end.linkTo(parent.end, margin = 6.dp)
                    width = Dimension.fillToConstraints
                },
            text = item.body,
        )
    }
}