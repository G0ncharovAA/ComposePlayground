package com.example.composeplayground.presentation.screens.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.composeplayground.R
import com.example.composeplayground.domain.entities.user.User
import com.example.composeplayground.presentation.mockedUser
import com.example.composeplayground.presentation.navigation.Destinations
import com.example.composeplayground.presentation.screens.auth.intention.AuthScreenIntention
import com.example.composeplayground.presentation.screens.auth.state.AuthState

@Composable
fun AuthScreen(
    navController: NavController,
    viewModel: AuthViewModel,
) {
    with(viewModel.viewState.collectAsState().value) {
        AuthContent(
            authState = authState,
            dropDownExpanded = dropDownExpanded,
            users = users,
            currentUser = currentUser,
            intentionsDispatcher = viewModel::dispatchIntention,
            onEnterClick = {
                navController.navigate(Destinations.HomeScreen.route) {
                    popUpTo(Destinations.AuthScreen.route) { inclusive = true }
                }
            },
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AuthPreview() {
    AuthContent(
        authState = AuthState.SignedOut,
        dropDownExpanded = false,
        users = emptyList(),
        currentUser = mockedUser,
        intentionsDispatcher = {},
        onEnterClick = {},
    )
}

@Composable
private fun AuthContent(
    authState: AuthState,
    dropDownExpanded: Boolean,
    users: List<User>,
    currentUser: User?,
    intentionsDispatcher: (AuthScreenIntention) -> Unit,
    onEnterClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(R.string.auth_state))
        Text(
            text = when (authState) {
                is AuthState.UsersLoaded -> stringResource(
                    id = R.string.users_loaded_,
                    users.size,
                )
                else -> stringResource(id = authState.stringId)
            }
        )
        UsersDropDown(
            users = users,
            selectedUser = currentUser,
            expanded = dropDownExpanded,
            intentionsDispatcher = intentionsDispatcher,
        )
        if (authState == AuthState.SignedIn) {
            Button(
                onClick = {
                    onEnterClick()
                },
            ) {
                Text(text = stringResource(id = R.string.enter))
            }
        }
    }
}

@Composable
private fun UsersDropDown(
    users: List<User>,
    selectedUser: User?,
    expanded: Boolean,
    modifier: Modifier = Modifier,
    intentionsDispatcher: (AuthScreenIntention) -> Unit,
) {

    Row(
        modifier = modifier
            .clickable { // Anchor view
                intentionsDispatcher(
                    AuthScreenIntention.DropDownExpandedChange(!expanded)
                )
            }
            .padding(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text =
            selectedUser?.userName
                ?: LocalContext.current.getString(R.string.select_user)
        )
        Icon(
            modifier = modifier.padding(start = 6.dp),
            imageVector = Icons.Filled.ArrowDropDown,
            contentDescription = LocalContext.current.getString(
                R.string.users_dropdown
            ),
        )
        DropdownMenu(
            modifier = modifier
                .fillMaxHeight(
                    fraction = 1.0f / 3
                ),
            expanded = expanded,
            onDismissRequest = {
                intentionsDispatcher(
                    AuthScreenIntention.DropDownExpandedChange(false)
                )
            },
        ) {
            users.forEach { user ->
                DropdownMenuItem(
                    onClick = {
                        intentionsDispatcher(
                            AuthScreenIntention.UserSelected(user)
                        )
                        intentionsDispatcher(
                            AuthScreenIntention.DropDownExpandedChange(false)
                        )
                    }
                ) {
                    Text(text = user.userName)
                }
            }
        }
    }
}