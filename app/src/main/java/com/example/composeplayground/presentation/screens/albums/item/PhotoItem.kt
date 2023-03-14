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

@Composable
fun PhotoItem(
    onItemClick: (Int, Int) -> Unit,
    item: Photo,
) {
    AsyncImage(
        contentScale = ContentScale.FillWidth,
        model = item.thumbnailUrl,
        contentDescription = item.title,
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                onItemClick(item.albumId, item.id)
            }
    )
}

@Preview(showBackground = true)
@Composable
private fun PhotoItemPreview() {
    PhotoItem(
        onItemClick = { _, _ -> },
        item = mockedPhoto,
    )
}
