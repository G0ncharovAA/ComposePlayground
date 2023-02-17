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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeplayground.R
import com.example.composeplayground.domain.entities.user.User
import com.example.composeplayground.domain.entities.user.concatinate
import com.example.composeplayground.presentation.mockedUser
import com.example.composeplayground.presentation.stringFromId

@Composable
fun HeroComposable(
    modifier: Modifier,
    user: User,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(
                end = 32.dp,
                bottom = 32.dp
            ),
        ) {
            Icon(
                imageVector = Icons.Default.Face,
                contentDescription = stringFromId(id = R.string.user_icon),
                modifier = Modifier.padding(end = 6.dp),
            )
            Column {
                Text(
                    text = stringFromId(id = R.string._aka, arg = user.name)
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
                Text(text = stringFromId(id = R.string.credentials))
                Text(
                    text = stringFromId(
                        id = R.string.phone_,
                        arg = user.phone
                    )
                )
                Text(
                    text = stringFromId(
                        id = R.string.website_,
                        arg = user.website
                    )
                )
                Text(
                    text = stringFromId(
                        id = R.string.email_,
                        arg = user.email
                    )
                )
            }
            Column(
                modifier = Modifier
                    .padding(start = 3.dp)
                    .weight(1f)
            ) {
                Text(text = stringFromId(id = R.string.company))
                Text(
                    text = stringFromId(
                        id = R.string.name_,
                        arg = user.company.name
                    )
                )
                Text(
                    text = stringFromId(
                        id = R.string.catch_phrase_,
                        arg = user.company.catchPhrase
                    )
                )
                Text(
                    text = stringFromId(
                        id = R.string.bs_,
                        arg = user.company.bs
                    )
                )
            }
        }
        Text(
            text = stringFromId(
                id = R.string.address_,
                arg = user.address.concatinate(),
            ),
            modifier = Modifier.align(Alignment.Start)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HeroComposable(
        modifier = Modifier,
        user = mockedUser,
    )
}