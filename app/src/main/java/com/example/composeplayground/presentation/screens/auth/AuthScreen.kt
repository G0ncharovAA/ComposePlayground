package com.example.composeplayground.presentation.screens.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
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
import com.example.composeplayground.presentation.nullAsMockedState

@Composable
fun AuthScreen(
    navController: NavController,
    viewModel: AuthViewModel,
) {
    with(viewModel) {
        Auth(
            // Default values of the states
            navController = navController,
            authState = authState.observeAsState(AuthState.SignedOut),
            users = users.observeAsState(initial = emptyList()),
            currentUser = currentUser.observeAsState(),
            onUserClick = ::onUserSelected,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AuthPreview() {
    Auth(
        navController = rememberNavController(),
        authState = AuthState.SignedIn.asMockedState(),
        users = emptyList<User>().asMockedState(),
        currentUser = nullAsMockedState(),
        onUserClick = {},
    )
}

@Composable
private fun Auth(
    navController: NavController,
    authState: State<AuthState>,
    users: State<List<User>>,
    currentUser: State<User?>,
    onUserClick: (User) -> Unit,
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
            text = when (authState.value) {
                is AuthState.UsersLoaded ->
                    "${stringResource(id = authState.value.stringId)}: ${users.value.size}"
                else -> stringResource(id = authState.value.stringId)
            }
        )
        UsersDropDown(
            users = users,
            selectedUser = currentUser,
            onUserClicked = onUserClick,
        )
        if (authState.value == AuthState.SignedIn) {
            Button(
                onClick = {
                    navController.navigate("home") {
                        popUpTo("auth") { inclusive = true }
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
    users: State<List<User>>,
    selectedUser: State<User?>,
    onUserClicked: (User) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .clickable { // Anchor view
                expanded = !expanded
            }
            .padding(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text =
            selectedUser.value?.userName
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
                expanded = false
            },
            modifier = Modifier
                .fillMaxHeight(
                    fraction = 1.0f / 3
                )
        ) {
            users.value.forEach { user ->
                DropdownMenuItem(
                    onClick = {
                        onUserClicked(user)
                        expanded = false
                    }
                ) {
                    Text(text = user.userName)
                }
            }
        }
    }
}