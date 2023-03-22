package com.example.composeplayground.presentation.screens.home.hero

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeplayground.R
import com.example.composeplayground.domain.entities.user.User
import com.example.composeplayground.domain.entities.user.concatinate
import com.example.composeplayground.presentation.mockedUser

@Preview(showBackground = true)
@Composable
private fun HeroPreview() {
    Hero(
        modifier = Modifier,
        user = mockedUser,
    )
}

@Composable
fun Hero(
    user: User,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier.padding(
                end = 32.dp,
                bottom = 32.dp
            ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                modifier = Modifier.padding(end = 6.dp),
                imageVector = Icons.Default.Face,
                contentDescription = stringResource(id = R.string.user_icon),
            )
            Column {
                Text(
                    text = stringResource(id = R.string._aka, user.name)
                )
                Text(
                    text = user.userName,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
        Row {
            Column(
                modifier = Modifier
                    .padding(end = 3.dp)
                    .weight(1f)
            ) {
                Text(text = stringResource(id = R.string.credentials))
                Text(
                    text = stringResource(
                        id = R.string.phone_,
                        user.phone
                    )
                )
                Text(
                    text = stringResource(
                        id = R.string.website_,
                        user.website
                    )
                )
                Text(
                    text = stringResource(
                        id = R.string.email_,
                        user.email
                    )
                )
            }
            Column(
                modifier = Modifier
                    .padding(start = 3.dp)
                    .weight(1f)
            ) {
                Text(text = stringResource(id = R.string.company))
                Text(
                    text = stringResource(
                        id = R.string.name_,
                        user.company.name
                    )
                )
                Text(
                    text = stringResource(
                        id = R.string.catch_phrase_,
                        user.company.catchPhrase
                    )
                )
                Text(
                    text = stringResource(
                        id = R.string.bs_,
                        user.company.bs
                    )
                )
            }
        }
        Text(
            modifier = Modifier.align(Alignment.Start),
            text = stringResource(
                id = R.string.address_,
                user.address.concatinate(),
            ),
        )
    }
}