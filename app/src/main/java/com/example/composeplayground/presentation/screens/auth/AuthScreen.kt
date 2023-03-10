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
import androidx.navigation.compose.rememberNavController
import com.example.composeplayground.R
import com.example.composeplayground.domain.entities.user.User
import com.example.composeplayground.presentation.asMockedState
import com.example.composeplayground.presentation.navigation.Destinations
import com.example.composeplayground.presentation.screens.auth.intention.AuthScreenIntention
import com.example.composeplayground.presentation.screens.auth.state.AuthScreenState
import com.example.composeplayground.presentation.screens.auth.state.AuthState

@Composable
fun AuthScreen(
    navController: NavController,
    viewModel: AuthViewModel,
) {
    with(viewModel) {
        Auth(
            // Default values of the states
            navController = navController,
            authScreenState = viewModel.viewState.collectAsState(),
            intentionsDispatcher = viewModel::dispatchIntention,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AuthPreview() {
    Auth(
        navController = rememberNavController(),
        authScreenState = AuthScreenState().asMockedState(),
        intentionsDispatcher = {},
    )
}

@Composable
private fun Auth(
    navController: NavController,
    authScreenState: State<AuthScreenState>,
    intentionsDispatcher: (AuthScreenIntention) -> Unit,
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
            text = when (authScreenState.value.authState) {
                is AuthState.UsersLoaded -> stringResource(
                    id = R.string.users_loaded_,
                    authScreenState.value.users.size,
                )
                else -> stringResource(id = authScreenState.value.authState.stringId)
            }
        )
        UsersDropDown(
            users = authScreenState.value.users,
            selectedUser = authScreenState.value.currentUser,
            expanded = authScreenState.value.dropDownExpanded,
            intentionsDispatcher = intentionsDispatcher,
        )
        if (authScreenState.value.authState == AuthState.SignedIn) {
            Button(
                onClick = {
                    navController.navigate(Destinations.HomeScreen.route) {
                        popUpTo(Destinations.AuthScreen.route) { inclusive = true }
                    }
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
    intentionsDispatcher: (AuthScreenIntention) -> Unit,
) {

    Row(
        modifier = Modifier
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
            imageVector = Icons.Filled.ArrowDropDown,
            contentDescription = LocalContext.current.getString(
                R.string.users_dropdown
            ),
            modifier = Modifier.padding(start = 6.dp)
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                intentionsDispatcher(
                    AuthScreenIntention.DropDownExpandedChange(false)
                )
            },
            modifier = Modifier
                .fillMaxHeight(
                    fraction = 1.0f / 3
                )
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