package com.example.composeplayground.presentation.screens.photo.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.composeplayground.domain.entities.album.Photo
import com.example.composeplayground.presentation.mockedPhoto

@Composable
fun ThumbPhotoItem(
    item: Photo,
    onClick: (Int) -> Unit,
) {
    AsyncImage(
        model = item.thumbnailUrl,
        contentDescription = item.title,
        modifier = Modifier.clickable {
            onClick(item.id)
        }
            .width(128.dp)
        )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ThumbPhotoItem(
        item = mockedPhoto,
        onClick = {},
    )
}