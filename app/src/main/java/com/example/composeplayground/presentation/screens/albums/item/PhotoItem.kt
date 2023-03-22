package com.example.composeplayground.presentation.screens.albums.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.example.composeplayground.domain.entities.album.Photo
import com.example.composeplayground.presentation.mockedPhoto

@Preview(showBackground = true)
@Composable
private fun PhotoItemPreview() {
    PhotoItem(
        modifier = Modifier,
        item = mockedPhoto,
        onItemClick = { _, _ -> },
    )
}

@Composable
fun PhotoItem(
    item: Photo,
    modifier: Modifier = Modifier,
    onItemClick: (Int, Int) -> Unit,
) {
    AsyncImage(
        modifier = modifier
            .fillMaxSize()
            .clickable {
                onItemClick(item.albumId, item.id)
            },
        contentScale = ContentScale.FillWidth,
        model = item.thumbnailUrl,
        contentDescription = item.title,
    )
}