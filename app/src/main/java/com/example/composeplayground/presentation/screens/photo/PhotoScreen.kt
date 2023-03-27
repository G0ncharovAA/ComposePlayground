package com.example.composeplayground.presentation.screens.photo

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.composeplayground.R
import com.example.composeplayground.domain.entities.album.Photo
import com.example.composeplayground.domain.entities.user.User
import com.example.composeplayground.presentation.*
import com.example.composeplayground.presentation.appbar.AppBar
import com.example.composeplayground.presentation.appbar.AppBarItem
import com.example.composeplayground.presentation.navigation.NavWrapper
import com.example.composeplayground.presentation.screens.photo.item.ThumbPhotoItem
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
fun PhotoScreen(
    navController: NavController,
    viewModel: PhotoViewModel,
) {
    with(viewModel.viewState.collectAsState().value) {
        PhotoContent(
            navWrapper = NavWrapper { navController },
            currentUser = currentUser,
            photo = photo,
            photos = photos.toImmutableList(),
            onPhotoClick = viewModel::onPhotoClick
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PhotoPreview() {
    val navController = rememberNavController()

    PhotoContent(
        navWrapper = NavWrapper { navController },
        currentUser = mockedUser,
        photo = mockedPhoto,
        photos = List(10) { mockedPhoto }.toImmutableList(),
        onPhotoClick = {},
    )
}

@Composable
private fun PhotoContent(
    navWrapper: NavWrapper,
    currentUser: User?,
    photo: Photo?,
    photos: ImmutableList<Photo>,
    onPhotoClick: (Int) -> Unit,
) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize(),
    ) {

        val (
            appBar,
            title,
            photoWidget,
            photosWidget,
        ) = createRefs()

        AppBar(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(appBar) {
                    top.linkTo(parent.top)
                },
            appBarItems = listOf<AppBarItem>(
                AppBarItem.UserItem(
                    currentUser?.userName ?: stringResource(id = R.string.no_user_name)
                )
            ).toImmutableList(),
            caption = stringResource(id = R.string.photo),
            onBackClick = {
                navWrapper.goBack()
            },
        )
        photo?.let {
            Text(
                modifier = Modifier.constrainAs(title) {
                    start.linkTo(parent.start, margin = 12.dp)
                    end.linkTo(parent.end, margin = 12.dp)
                    top.linkTo(appBar.bottom, margin = 64.dp)
                    width = Dimension.fillToConstraints
                },
                text = it.title,
                textAlign = TextAlign.Center,
            )
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
                    .constrainAs(photoWidget) {
                        top.linkTo(title.bottom, margin = 12.dp)
                    },
                model = it.url,
                contentDescription = it.title,
            )
        }
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(photosWidget) {
                    top.linkTo(photoWidget.bottom, margin = 6.dp)
                    bottom.linkTo(parent.bottom)
                },
        ) {
            items(photos) { photo ->
                ThumbPhotoItem(
                    item = photo,
                    onClick = onPhotoClick,
                )
            }
        }
    }
}