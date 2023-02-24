package com.example.composeplayground.presentation.screens.todos.item

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.composeplayground.R
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.composeplayground.domain.entities.todo.ToDo
import com.example.composeplayground.presentation.mockedToDo

@Composable
fun ToDoComposable(item: ToDo) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .border(
                width = 2.dp,
                color = MaterialTheme.colors.secondary,
                shape = MaterialTheme.shapes.small
            )
    ) {
        val (
            header,
            title,
            complete,
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

        Text(
            modifier = Modifier
                .padding(bottom = 6.dp)
                .constrainAs(title) {
                    top.linkTo(header.bottom)
                    start.linkTo(header.start)
                    end.linkTo(complete.start, margin = 6.dp)
                    width = Dimension.fillToConstraints
                },
            text = item.title,
        )

        Icon(
            modifier = Modifier
                .constrainAs(complete) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end, margin = 6.dp)
                },
            imageVector = if (item.completed) {
                Icons.Default.ThumbUp
            } else {
                Icons.Default.Clear
            },
            contentDescription = stringResource(id = R.string.is_complete)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ToDoComposable(item = mockedToDo)
}