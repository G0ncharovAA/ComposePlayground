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
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit,
) {
    AsyncImage(
        modifier = modifier.clickable {
            onClick(item.id)
        }
            .width(128.dp),
        model = item.thumbnailUrl,
        contentDescription = item.title,
        )
}

@Preview(showBackground = true)
@Composable
private fun ThumbPhotoPreview() {
    ThumbPhotoItem(
        item = mockedPhoto,
        onClick = {},
    )
}